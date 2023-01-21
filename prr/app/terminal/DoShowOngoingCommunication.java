package prr.app.terminal;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }

  /**
   * Command for showing the ongoing communication of the given Terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    int aux = 0;
    for (Communication madeCommunication : _receiver.getMadeCommunications()) {
      if (madeCommunication.getIsOnGoing()) {
        aux = 1;
        _display.addLine(_network.toStringCommunication(madeCommunication));
      }
    }
    for (Communication receivedCommunication : _receiver.getReceivedCommunications()) {
      if (receivedCommunication.getIsOnGoing()) {
        aux = 1;
        _display.addLine(_network.toStringCommunication(receivedCommunication));
      }
    }
    if (aux == 0) {
      _display.addLine(Message.noOngoingCommunication());
    }
    _display.display();
  }

}
