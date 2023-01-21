package prr.app.client;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("key", Message.key());
  }

  /**
   * Disable notifications of the given terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    if(!_receiver.getClient(stringField("key")).areNotificationsOn()){
      _display.addLine(Message.clientNotificationsAlreadyDisabled());
      _display.display();
    }
    else {
      _receiver.getNetworkManager().getNetwork().disableNot(stringField("key"));
    }
  }

}
