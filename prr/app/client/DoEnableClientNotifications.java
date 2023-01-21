package prr.app.client;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("key", Message.key());
  }

  /**
   * Enable notifications of the given terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getClient(stringField("key")).areNotificationsOn()){
      _display.addLine(Message.clientNotificationsAlreadyEnabled());
      _display.display();
    }
    else {
      _receiver.getNetworkManager().getNetwork().enableNot(stringField("key"));
    }
  }

}
