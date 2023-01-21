package prr.app.lookup;

import prr.app.exception.UnknownClientKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Client;
import prr.core.Communication;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

	DoShowCommunicationsToClient(Network receiver) {
		super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
		addStringField("key", Message.clientKey());
	}

	@Override
	protected final void execute() throws CommandException {
		Client client = _receiver.getNetworkManager().getNetwork().getClient(stringField("key"));
		if (client == null) {
			throw new UnknownClientKeyException(stringField("key"));
		}
		if (client.getTerminals().size() == 0) {
			throw new UnknownTerminalKeyException(stringField("key"));
		}
		for (String terminalId: client.getTerminals()) {
			for(Communication communication: _receiver.getTerminal(terminalId).getReceivedCommunications()) {
				_display.addLine(_receiver.toStringCommunication(communication));
			}
		}
		_display.display();
	}

}
