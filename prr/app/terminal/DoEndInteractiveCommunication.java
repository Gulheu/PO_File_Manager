package prr.app.terminal;

import prr.core.*;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
    addIntegerField("duration", Message.duration());
  }

  /**
   * Command for ending communication of the given terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    for (Communication communication: _receiver.getMadeCommunications()) {
      if(communication.getIsOnGoing()) {
        Terminal terminal = _network.getTerminal(communication.getIdReceiver());
        _receiver.setInitialMode();
        _receiver.switchOnGoing();
        terminal.setInitialMode();
        communication.switchOnGoing();
        communication.calculate_cost1(_receiver,integerField("duration"));
        _receiver.addDebts(communication.getCost());
        _receiver.getClientTerminal().addDebts(communication.getCost());
        _receiver.getClientTerminal().goldToNormal();
        _receiver.getClientTerminal().platinumToNormal();
        _display.addLine(Message.communicationCost(Math.round(communication.getCost())));
        _display.display();
      }
    }
  }

}
