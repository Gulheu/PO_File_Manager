package prr.app.lookup;

import prr.core.Client;
import prr.core.Communication;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

	DoShowCommunicationsFromClient(Network receiver) {
		super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
		addStringField("key", Message.clientKey());
	}

	@Override
	protected final void execute() throws CommandException {
		Client client = _receiver.getClient(stringField("key"));
		for (String terminalId: client.getTerminals()) {
			for(Communication communication: _receiver.getTerminal(terminalId).getMadeCommunications()) {
				_display.addLine(_receiver.toStringCommunication(communication));
			}
		}
		_display.display();
	}

}
