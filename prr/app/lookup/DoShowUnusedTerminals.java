package prr.app.lookup;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;


/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

	DoShowUnusedTerminals(Network receiver) {

		super(Label.SHOW_UNUSED_TERMINALS, receiver);
	}

	/**
	 * Print to the display all the terminals, in the String format, that were not used yet.
	 */
	@Override
	protected final void execute() throws CommandException {
		List <Terminal> terminalList = _receiver.getNetworkManager().getNetwork().getTerminals();
		for (Terminal terminal: terminalList) {
			if (terminal.getMadeCommunications().size() == 0 && terminal.getReceivedCommunications().size() == 0) {
				_display.addLine(_receiver.getNetworkManager().getNetwork().toStringTerminal(terminal));
			}
		}
		_display.display();
	}

}
