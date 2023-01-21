package prr.app.lookup;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;


/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

	DoShowTerminalsWithPositiveBalance(Network receiver) {
		super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		List <Terminal> terminalList = _receiver.getNetworkManager().getNetwork().getTerminals();
		for (Terminal terminal: terminalList) {
			if (terminal.getPayments() > terminal.getDebts()) {
				_display.addLine(_receiver.getNetworkManager().getNetwork().toStringTerminal(terminal));
			}
		}
		_display.display();
	}

}
