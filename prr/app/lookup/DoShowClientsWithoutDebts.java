package prr.app.lookup;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
import java.util.List;


/**
 * Show clients with positive balance.
 */
class DoShowClientsWithoutDebts extends Command<Network> {

	DoShowClientsWithoutDebts(Network receiver) {
		super(Label.SHOW_CLIENTS_WITHOUT_DEBTS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		List <Client> clientsWithoutDebts = new ArrayList<>();
		List <Client> clientsList = _receiver.getNetworkManager().getNetwork().getClients();
		for (Client client: clientsList) {
			if (client.getDebts() == 0) {
				clientsWithoutDebts.add(client);
			}
		}
		for (Client clientWithoutDebts: clientsWithoutDebts) {
			_display.addLine(_receiver.getNetworkManager().getNetwork().toStringClient(clientWithoutDebts));
		}
		_display.display();
	}

}
