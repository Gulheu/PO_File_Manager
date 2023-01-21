package prr.app.main;

import prr.core.NetworkManager;
import prr.app.exception.FileOpenFailedException;
import prr.core.exception.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

  DoOpenFile(NetworkManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filename", Message.openFile());
  }

  /**
   * Open a given file.
   * @throws java.io.FileNotFoundException if the file is not found
   */
  @Override
  protected final void execute() throws CommandException {
      try {
          _receiver.load(stringField(("filename")));
      }
      catch (UnavailableFileException | ClassNotFoundException e) {
        throw new FileOpenFailedException(e);
      }
  }

}
