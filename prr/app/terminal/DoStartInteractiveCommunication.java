package prr.app.terminal;

import prr.core.*;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Objects;


/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("key", Message.terminalKey());
    addOptionField("type", Message.commType(), "VOICE", "VIDEO");
  }

  /**
   * Command for starting communication to a given Terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    int i = 0;
    Terminal terminal = _network.getTerminal(stringField("key"));
    if (terminal == null) {
      throw new UnknownTerminalKeyException(stringField("key"));
    }
    else if(Objects.equals(terminal.getId(), _receiver.getId())) {
      _display.addLine(Message.destinationIsBusy(terminal.getId()));
      _display.display();
    }
    else if (terminal.getMode() == TerminalMode.OFF) {
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
    else if (terminal.getMode() == TerminalMode.BUSY) {
      i = 0;
      for(Terminal terminalCall: terminal.getCallsToMake()) {
        if(terminalCall.getId().equals(_receiver.getId())) {
          i = 1;
        }
      }
      if(i == 0) {
        terminal.addTerminalMode(_receiver);
      }
      _display.addLine(Message.destinationIsBusy(stringField("key")));
      _display.display();
    }
    else if (terminal.getMode() == TerminalMode.SILENCE) {
      i = 0;
      for(Terminal terminalCall: terminal.getCallsToMake()) {
        if(terminalCall.getId().equals(_receiver.getId())) {
          i = 1;
        }
      }
      if(i == 0) {
        terminal.addTerminalMode(_receiver);
      }
      _display.addLine(Message.destinationIsSilent(stringField("key")));
      _display.display();
    }
    /**
     * Starting Voice communication.
     */
    else if (Objects.equals(optionField("type"), "VOICE")) {
      Communication communication = new VoiceCommunication(_receiver, terminal);
      _receiver.setOnBusy();
      terminal.setOnBusy();
      communication.switchOnGoing();
      _receiver.getMadeCommunications().add(communication);
      terminal.getReceivedCommunications().add(communication);
      _network.getCommunications().add(communication);
      _receiver.switchOnGoing();
      _receiver.getClientTerminal().resetTextCount();
      _receiver.getClientTerminal().resetVideoCount();
    }
    else if (Objects.equals(optionField("type"), "VIDEO")) {
      if (Objects.equals(_receiver.getType(), "BASIC")) {
        _display.addLine(Message.unsupportedAtOrigin(_receiver.getId(), stringField("type")));
        _display.display();
      }
      else if (Objects.equals(terminal.getType(), "BASIC")) {
        _display.addLine(Message.unsupportedAtDestination(terminal.getId(), stringField("type")));
        _display.display();
      }
      /**
       * Starting Video communication.
       */
      else {
        Communication communication = new VideoCommunication(_receiver, terminal);
        _receiver.setOnBusy();
        terminal.setOnBusy();
        communication.switchOnGoing();
        _receiver.getMadeCommunications().add(communication);
        terminal.getReceivedCommunications().add(communication);
        _network.getCommunications().add(communication);
        _receiver.switchOnGoing();
        _receiver.getClientTerminal().incrementVideoCount();
        _receiver.getClientTerminal().resetTextCount();
        _receiver.getClientTerminal().goldToPlatinum();
      }
    }
  }

}
