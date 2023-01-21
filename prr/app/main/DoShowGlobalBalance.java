package prr.app.main;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;


/**
 * Show global balance.
 */
class DoShowGlobalBalance extends Command<Network> {

  DoShowGlobalBalance(Network receiver) {
    super(Label.SHOW_GLOBAL_BALANCE, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    int payments = 0;
    int debts = 0;
    List<Client> clientsList = _receiver.getNetworkManager().getNetwork().getClients();
    for (Client client: clientsList) {
      payments += client.getPayment();
      debts += client.getDebts();
    }
    _display.addLine(Message.globalPaymentsAndDebts(payments, debts));
    _display.display();
  }

}
