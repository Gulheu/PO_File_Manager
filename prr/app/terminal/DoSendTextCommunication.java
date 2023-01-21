package prr.app.terminal;

import prr.core.*;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {
  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("key", Message.terminalKey());
    addStringField("message", Message.textMessage());

  }

  /**
   * Command for sending a text communication to a given Terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    int i = 0;
    Terminal terminal = _network.getNetworkManager().getNetwork().getTerminal(stringField("key"));
    if (terminal == null) {
      throw new UnknownTerminalKeyException(stringField("key"));
    }
    if (terminal.getMode() == TerminalMode.OFF) {
      i = 0;

      for(Terminal terminalCall: terminal.getCallsToMake()) {
        if(terminalCall.getId().equals(_receiver.getId())) {
          i = 1;
        }
      }
      if(i == 0) {
        terminal.addTerminalMode(_receiver);
      }
      _display.addLine(Message.destinationIsOff(stringField("key")));
      _display.display();
    }
    else {
      TextCommunication communication = new TextCommunication(_receiver, terminal);
      communication.calculate_cost(_receiver, stringField("message"));
      _network.getNetworkManager().getNetwork().getCommunications().add(communication);
      _receiver.addDebts(communication.getCost());
      terminal.getReceivedCommunications().add(communication);
      _receiver.getMadeCommunications().add(communication);
      _receiver.getClientTerminal().addDebts(communication.getCost());
      _receiver.getClientTerminal().incrementTextCount();
      _receiver.getClientTerminal().resetVideoCount();
      _receiver.getClientTerminal().goldToNormal();
      _receiver.getClientTerminal().platinumToNormal();
      _receiver.getClientTerminal().platinumToGold();
    }
  }

} 
