package prr.app.client;

import prr.app.exception.DuplicateClientKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("key", Message.key());
    addStringField("name", Message.name());
    addIntegerField("taxId", Message.taxId());
  }

  /**
   * Register a Client in the List _clients.
   * @throws DuplicateClientKeyException if already exists a Client with the same Client key.
   */
  @Override
  protected final void execute() throws CommandException {
    final Client client = this._receiver.getNetworkManager().getNetwork().getClient(stringField("key"));
    if (client != null)  {
      throw new DuplicateClientKeyException(stringField("key"));
    }
    _receiver.getNetworkManager().getNetwork().registerClient(stringField("key"),
            stringField("name"), integerField("taxId"));
  }

}
