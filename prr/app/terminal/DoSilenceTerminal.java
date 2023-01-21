package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TerminalMode;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {

    super(Label.MUTE_TERMINAL, context, terminal);
  }

  /**
   * Silence the terminal.
   */
  @Override
  protected final void execute() throws CommandException {
    if (_receiver.getMode() == TerminalMode.SILENCE) {
      _display.addLine(Message.alreadySilent());
      _display.display();
    }
    else {
    _receiver.setOnSilent();
    }
  }

}
