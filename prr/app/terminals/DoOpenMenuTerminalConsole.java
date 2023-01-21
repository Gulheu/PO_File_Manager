package prr.app.terminals;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Terminal;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("idTerminal", Message.terminalKey());
  }

  /**
   * Open Terminal Menu.
   * @throws CommandException if the terminal key does not exist.
   */
  @Override
  protected final void execute() throws CommandException {
    final Terminal terminal = _receiver.getNetworkManager().getNetwork().getTerminal(stringField("idTerminal"));
    if (terminal == null) {
      throw new UnknownTerminalKeyException(stringField("idTerminal"));
    }
    else {
      (new prr.app.terminal.Menu(_receiver, terminal)).open();
    }
  }

}
