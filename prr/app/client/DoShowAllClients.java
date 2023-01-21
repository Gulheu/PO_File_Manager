package prr.app.client;

import java.util.Comparator;
import java.util.List;
import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;


/**
 * Client key Comparator.
 */
class ClientKeyComparator implements Comparator<Client> {
  @Override
  public int compare(Client client1, Client client2) {
      return client1.getKey().toLowerCase().compareTo(client2.getKey().toLowerCase());
    }

}

/**
 * Show all clients.
 */

class DoShowAllClients extends Command<Network> {
  DoShowAllClients(Network receiver) {
    super(Label.SHOW_ALL_CLIENTS, receiver);
  }

  /**
   * Print on the Display all the Clients in String format following the guidelines;
   */
  @Override
  protected final void execute() {
    List <Client> sortedClientsList = _receiver.getNetworkManager().getNetwork().getClients();
    sortedClientsList.sort(new ClientKeyComparator());
    for (Client client: sortedClientsList) {
      _display.addLine(_receiver.getNetworkManager().getNetwork().toStringClient(client));
    }
    _display.display();
  }

}
