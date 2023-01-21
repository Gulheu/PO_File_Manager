package prr.app.terminals;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.*;


/**
 * Terminal id Comparator.
 */
class TerminalIdComparator implements Comparator<Terminal> {
  @Override
  public int compare(Terminal terminal1, Terminal terminal2) {
    return terminal1.getId().compareTo(terminal2.getId());
  }

}

/**
 * Terminal Friend id Comparator.
 */
class FriendIdComparator implements Comparator<String> {
  @Override
  public int compare(String friendId1, String friendId2) {
    return friendId1.compareTo(friendId2);
  }

}

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

  DoShowAllTerminals(Network receiver) {
    super(Label.SHOW_ALL_TERMINALS, receiver);
  }

  /**
   * Print on the Display all the Terminals in String format following the guidelines.
   */
  @Override
  protected final void execute() throws CommandException {
    List <Terminal> sortedTerminalsList = _receiver.getNetworkManager().getNetwork().getTerminals();
    sortedTerminalsList.sort(new TerminalIdComparator());
    for (Terminal terminal: sortedTerminalsList) {
      terminal.getFriends().sort(new FriendIdComparator());
      _display.addLine(_receiver.getNetworkManager().getNetwork().toStringTerminal(terminal));
    }
    _display.display();
    }

}
