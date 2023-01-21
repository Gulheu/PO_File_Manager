package prr.core;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;
import prr.core.exception.UnavailableFileException;
import prr.core.exception.UnrecognizedEntryException;
import java.io.Serializable;


/**
 * Manage access to network and implement load/save operations.
 */
public class NetworkManager implements Serializable {

  private String _filename;

  /**
   * The network itself.
   */
  private Network _network = new Network();

  /**
   * Constructor that initializes an instance of the NetworkManager.
   */
  public NetworkManager() {
    _network.setNetworkManager(this);
  }

  /**
   * Return the Network stored.
   * @return _network
   */
  public Network getNetwork() {
    return _network;
  }

  /**
   * @param filename name of the file containing the serialized application's state to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *                                  an error while processing this file.
   * @throws ClassNotFoundException   if the specified class does not exist or cannot be found.
   */
  public void load(String filename) throws UnavailableFileException, ClassNotFoundException {
    try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
      _network = (Network) input.readObject();
      this._filename = filename;
    }
    catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
  }

  /**
   * Save the serialized application's state into the file associated to the current network.
   * @throws FileNotFoundException           if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException                     if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_filename == null || _filename.isBlank()) {
      throw new MissingFileAssociationException();
    }
    try (ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename)))) {
      output.writeObject(_network);
    }
  }

  /**
   * Save the serialized application's state into the specified file. The current network is associated to this file.
   * @param filename the name of the file.
   * @throws FileNotFoundException           if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException                     if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }

  /**
   * Read text input file and create domain entities.
   * @param filename name of the text input file
   * @throws ImportFileException if for some reason there is a problem with the file import.
   */
  public void importFile(String filename) throws ImportFileException {
    try {
      _network.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  }

}
