package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("idFriend", Message.terminalKey());
  }

  /**
   * Add a friend to a given Terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    int aux = 0;
    Terminal terminal = _network.getTerminal(stringField("idFriend"));
    if (terminal == null) {
      throw new UnknownTerminalKeyException(stringField("idFriend"));
    }
    if (stringField("idFriend").equals(_receiver.getId())) {
      aux = 1;
    }
    for(String id: _receiver.getFriends()){
      if(id.equals(stringField("idFriend"))){
        aux = 1;
      }
    }
    if(aux == 0) {
      _network.addFriend(_receiver.getId(),stringField("idFriend"));
    }
  }

}
