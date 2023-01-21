package prr.core;

import java.io.Serializable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.lang.Math;
import prr.core.exception.UnrecognizedEntryException;


/**
 * Class Network implements a network.
 */
public class Network implements Serializable {

  private List <Client> _clients;
  private List <Terminal> _terminals;
  private List <Communication> _communications;
  private NetworkManager _networkManager;

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  /**
   * Constructor that initializes the Lists _clients and _terminals.
   */
  public Network() {
    _clients = new ArrayList<>();
    _terminals = new ArrayList<>();
    _communications = new ArrayList<>();
  }
  public List<Communication> getCommunications() {return _communications;}

  /**
   * Return a List with all the Clients saved in the app.
   * @return _clients
   */
  public List <Client> getClients() {
    return _clients;
  }

  /**
   * Receive a String key and returns the Client that has that attribute.
   * If the Client does not exist, it returns null.
   * @param key attribute of a client
   * @return client
   */
  public Client getClient(String key) {
    for (Client client : _clients) {
      if (client.getKey().equals(key)) {
        return client;
      }
    }
    return null;
  }
  public Communication getCommunication(int id) {
    for (Communication communication: _communications) {
      if (communication.getIdCommunication() == id) {
        return communication;
      }
    }
    return null;
  }

  /**
   * Receive a String key and returns the Client that has that attribute.
   * If the Terminal does not exist, it returns null.
   * @param key attribute of a Terminal
   * @return terminal
   */
  public Terminal getTerminal(String key) {
    for (Terminal terminal : _terminals) {
      if (terminal.getId().equals(key)) {
        return terminal;
      }
    }
    return null;
  }

  /**
   * Return the NetworkManager stored.
   * @return _networkManager
   */
  public NetworkManager getNetworkManager() {return _networkManager;}

  /**
   * Set the NetworkManager stored to the given NetworkManager.
   *
   */
  public void setNetworkManager(NetworkManager networkManager) {
    _networkManager = networkManager;
  }

  /**
   * Return a String of a Client in the format asked in the guidelines.
   * @param client object from class Client
   * @return Client in String format
   */
  public String toStringClient(Client client) {
      return ("CLIENT" + "|" + client.getKey() + "|" + client.getName() + "|" + client.getTaxNumber() + "|" +
              client.getLevel() + "|" + client.getNotificationWord() + "|" + client.getNTerminals() + "|" +
              client.getPayment() + "|" + client.getDebts());
  }

  /**
   * Return a String of a Notification in the format asked in the guidelines.
   * @param notification object from class Notification
   * @return Notification in String format
   */
  public String toStringNotifications(Notification notification) {
    return (notification.getType() + "|" + notification.getTerminalNot());
  }

  /**
   * Return a String of a Communication in the format asked in the guidelines.
   * @param communication object from class Communication
   * @return Communication in String format
   */
  public String toStringCommunication(Communication communication) {
    return (communication.getType() + "|" + communication.getIdCommunication() + "|" + communication.getIdSender()
    + "|" + communication.getIdReceiver() + "|" + communication.getUnits() + "|" + Math.round(communication.getCost()) + "|"
    + communication.getIsOnGoingString());
  }
  /**
   * Return a String of a Terminal in the format asked in the guidelines.
   * @param terminal object from class Terminal
   * @return terminal in String format
   */
  public String toStringTerminal(Terminal terminal) {
    if (terminal.getFriends().size() == 0) {
      return (terminal.getType() + "|" + terminal.getId() + "|" + terminal.getClientKey() + "|" +
              terminal.getMode() + "|" + Math.round(terminal.getPayments()) + "|" + Math.round(terminal.getDebts()));
    }
    else {
      return (terminal.getType() + "|" + terminal.getId() + "|" + terminal.getClientKey() + "|" +
              terminal.getMode() + "|" + Math.round(terminal.getPayments()) + "|" + Math.round(terminal.getDebts()) +
                                                                              "|" + terminal.getFriendsString());
    }
  }

  /**
   * Receive a String key and returns a Clients in String format that has that attribute.
   * @param key attribute from Client
   * @return Client in String format
   */
  public String showClient(String key) {
    final Client client = this.getClient(key);
    return toStringClient(client);
  }

  /**
   * Disable notifications of the given terminal.
   * @param key attribute from Terminal
   */
  public void disableNot(String key) {
    final Client client = this.getClient(key);
    if (client.areNotificationsOn()) {
      client.turnOffNotifications();
    } else {
      System.out.println("A recepção de mensagens já está inactiva");
    }
  }

  /**
   * Enable notifications of the given terminal.
   * @param key attribute from Terminal
   */
  public void enableNot(String key) {
    final Client client = this.getClient(key);
    if (!client.areNotificationsOn()) {
      client.turnOnNotifications();
    }
    else {
      System.out.println("A recepção de mensagens já está activa");
    }
  }

  /**
   * Register a Client in the List _clients.
   * @param key attribute from Client
   * @param name attribute from Client
   * @param taxId attribute from Client
   * @return Client
   */
  public Client registerClient(String key, String name, int taxId){
      Client p = new Client(key, name, taxId);
      _clients.add(p);
      return p;
  }

  /**
   * Register a Terminal in the List _terminals.
   * @param terminalType attribute from Terminal
   * @param idTerminal attribute from Terminal
   * @param keyClient attribute from Terminal
   * @return Terminal or null value (if it is given an invalid terminalType)
   */
  public Terminal registerTerminal(String terminalType, String idTerminal, String keyClient){
    if (Objects.equals(terminalType, "FANCY")) {
      final Client client = this.getClient(keyClient);
      FancyTerminal fancy = new FancyTerminal(idTerminal, client);
      _terminals.add(fancy);
      client.addTerminal(fancy);
      return fancy;
    }
    if (Objects.equals(terminalType, "BASIC"))  {
      final Client client = this.getClient(keyClient);
      BasicTerminal basic = new BasicTerminal(idTerminal, client);
      _terminals.add(basic);
      client.addTerminal(basic);
      return basic;
    }
    return null;
  }

  /**
   * Return a List with all the Terminals saved in the app.
   * @return _terminals
   */
  public List<Terminal> getTerminals() {
    return _terminals;
  }


  /**
   * Add a Terminal to the friend List of the Terminal.
   * @param idTerminal attribute from Terminal
   * @param idFriend attribute from Terminal
   */
  public void addFriend(String idTerminal, String idFriend) {
    if(!idTerminal.equals(idFriend)) {
      Terminal terminal = getTerminal(idTerminal);
      terminal.getFriends().add(idFriend);
    }
  }

  /**
   * Remove a Terminal to the friend List of the Terminal.
   * @param idTerminal attribute from Terminal
   * @param idFriend attribute from Terminal
   */
  public void removeFriend(String idTerminal, String idFriend) {
    Terminal terminal = getTerminal(idTerminal);
    terminal.getFriends().remove(idFriend);
  }

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    Parser parser = new Parser(this);
    parser.parseFile(filename);
  }

}
