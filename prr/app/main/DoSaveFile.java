package prr.app.main;

import prr.core.NetworkManager;
import prr.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.io.IOException;


/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

  /**
   * Save a given file.
   * @throws CommandException if for some reason there is an unsuccessful command execution.
   */
  @Override
  protected final void execute() throws CommandException {
    try {
      try {
        _receiver.save();
      }
      catch (MissingFileAssociationException mfa) {
        saveAs();
      }
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Save a new file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  private void saveAs() throws IOException {
    try {
      _receiver.saveAs(Form.requestString(Message.newSaveAs()));
    }
    catch (MissingFileAssociationException mfa) {
      saveAs();
    }
  }

}
