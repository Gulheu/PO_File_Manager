package prr.app.lookup;

import prr.core.Communication;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.util.List;

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

	DoShowAllCommunications(Network receiver) {
		super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
	}

	/**
	 * Command for showing all communications from the given Terminal.
	 */
	@Override
	protected final void execute() throws CommandException {
		List <Communication> communications = _receiver.getCommunications();
		for (Communication communication: communications) {
			_display.addLine(_receiver.getNetworkManager().getNetwork().toStringCommunication(communication));
		}
		_display.display();
	}

}
