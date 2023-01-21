package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import prr.core.Notification;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("key", Message.key());
  }

  /**
   * Print to the display a Client in the String format following the guidelines.
   * @throws UnknownClientKeyException if it does not exist a Client with that Client key.
   */
  @Override
  protected final void execute() throws CommandException {
    final Client client = _receiver.getNetworkManager().getNetwork().getClient(stringField("key"));
    if (client == null) {
      throw new UnknownClientKeyException(stringField("key"));
    }
    _display.addLine(_receiver.getNetworkManager().getNetwork().showClient(stringField("key")));
    if(client.areNotificationsOn()) {
      if (!(client.getNotifications().size() == 0)) {
        for (Notification notification : client.getNotifications()) {
          _display.addLine(_receiver.toStringNotifications(notification));
        }
      }
      client.getNotifications().clear();
    }
    _display.display();

  }

}
