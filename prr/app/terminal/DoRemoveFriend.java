package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("idFriend", Message.terminalKey());
  }

  /**
   * Remove friend from the given terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    Terminal terminal = _network.getTerminal(stringField("idFriend"));
    if (terminal == null) {
      throw new UnknownTerminalKeyException(stringField("idFriend"));
    }
    _network.removeFriend(_receiver.getId(),stringField("idFriend"));
  }

}
