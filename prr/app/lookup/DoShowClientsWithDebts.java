package prr.app.lookup;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Client debt Comparator.
 */
class ClientDebtComparator implements Comparator<Client> {
	@Override
	public int compare(Client client1, Client client2) {
		if (client1.getDebts() == client2.getDebts()) {
			return client1.getKey().toLowerCase().compareTo(client2.getKey().toLowerCase());
		}
		else {
			return client2.getDebts() - client1.getDebts();
		}
	}

}

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

	DoShowClientsWithDebts(Network receiver) {
		super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		List <Client> clientsWithDebts = new ArrayList<>();
		List <Client> clientsList = _receiver.getClients();
		for (Client client: clientsList) {
			if (client.getDebts() > 0) {
				clientsWithDebts.add(client);
			}
		}
		clientsWithDebts.sort(new ClientDebtComparator());
		for (Client clientWithDebts: clientsWithDebts) {
			_display.addLine(_receiver.toStringClient(clientWithDebts));
		}
		_display.display();
	}

}
