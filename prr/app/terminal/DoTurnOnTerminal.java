package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

  DoTurnOnTerminal(Network context, Terminal terminal) {

    super(Label.POWER_ON, context, terminal);
  }

  /**
   * Turn on the given terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getMode() == TerminalMode.IDLE) {
      _display.addLine(Message.alreadyOn());
      _display.display();
    }
    else {
      _receiver.setOnIdle();
    }
  }

}
