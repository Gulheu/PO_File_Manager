package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("key", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    Client client = _receiver.getNetworkManager().getNetwork().getClient(stringField("key"));
    _display.addLine(Message.clientPaymentsAndDebts(stringField("key"), client.getPayment(), client.getDebts()));
    _display.display();
  }

}
