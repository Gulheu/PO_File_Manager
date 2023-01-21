package prr.app.terminals;

import prr.app.exception.DuplicateTerminalKeyException;
import prr.core.Client;
import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("idTerminal", Message.terminalKey());
    addOptionField("type", Message.terminalType(),"FANCY","BASIC");
    addStringField("keyClient", Message.clientKey());
  }

  /**
   * Register a Terminal in the List _terminals.
   * @throws DuplicateTerminalKeyException if already exists a Terminal with the same Terminal id.
   * @throws UnknownClientKeyException if it does not exist a Client with that Client key.
   * @throws InvalidTerminalKeyException if the size of the Terminal id is not 6.
   */
  @Override
  protected final void execute() throws CommandException {
    final Terminal terminal = this._receiver.getNetworkManager().getNetwork().getTerminal(stringField("idTerminal"));
    final Client client = this._receiver.getNetworkManager().getNetwork().getClient(stringField("keyClient"));
    if (terminal != null) {
      throw new DuplicateTerminalKeyException(stringField("idTerminal"));
    }
    if (client == null) {
      throw new UnknownClientKeyException(stringField("keyClient"));
    }
    if (stringField("idTerminal").length() != 6) {
      throw new InvalidTerminalKeyException(stringField("idTerminal"));
    }
    try {
      Integer.parseInt(stringField("idTerminal"));
    }
    catch (Exception e) {
      throw new InvalidTerminalKeyException(stringField("idTerminal"));
    }

    _receiver.getNetworkManager().getNetwork().registerTerminal(stringField("type"),
            stringField("idTerminal"),stringField("keyClient") );
  }

}
