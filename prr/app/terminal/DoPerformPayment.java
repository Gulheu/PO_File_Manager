package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Client;
import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("idCommunication", Message.commKey());
  }

  /**
   * Perform payment of the given Terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    Communication communication = _network.getCommunication(integerField("idCommunication"));
    if (communication == null || communication.getIsOnGoing() || communication.getIsPaid()) {
      _display.addLine(Message.invalidCommunication());
      _display.display();
    }
    else if(!communication.getIdSender().equals(_receiver.getId())) {
      _display.addLine(Message.invalidCommunication());
      _display.display();
    }
    else {
      Terminal terminal = _network.getTerminal(communication.getIdSender());
      if (terminal == null) {
        throw new UnknownTerminalKeyException(communication.getIdSender());
      }
      Client client = terminal.getClientTerminal();
      client.addClientPayments(communication.getCost());
      _receiver.addTerminalPayments(communication.getCost());
      client.normalToGold();
      communication.switchIsPaid();
    }
  }

}
