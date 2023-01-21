package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Abstract Class Terminal.
 */
abstract public class Terminal implements Serializable {

  /**
   * Serial number for serialization.
   */
  private static final long serialVersionUID = 202208091753L;

  private String _id;
  private double _debt;
  private double _payments;
  private TerminalMode _mode;
  private Client _client;
  private List <String> _friends;
  private List <Communication> _madeCommunications;
  private List <Communication> _receivedCommunications;
  private String _type;
  private boolean _onGoing;
  private TerminalMode _initialMode;
  private List <Terminal> _callsToMake;

  /**
   * Terminal Constructor.
   * @param id attribute from Terminal
   * @param client attribute from Terminal
   */
  public Terminal(String id, Client client) {
    _id = id;
    _client = client;
    _debt = 0;
    _payments = 0;
    _friends = new ArrayList<>();
    _madeCommunications = new ArrayList<>();
    _receivedCommunications = new ArrayList<>();
    _mode = TerminalMode.IDLE;
    _onGoing = false;
    _callsToMake = new ArrayList<>();
  }

  public String getId() {return _id;}
  public Client getClientTerminal() {return _client;}
  public String getClientKey() {return _client.getKey();}
  public double getDebts() {return _debt;}
  public double getPayments() {return _payments;}
  public List<String> getFriends() {return _friends;}
  public List <Communication> getMadeCommunications() {return _madeCommunications;}
  public List <Communication> getReceivedCommunications() {return _receivedCommunications;}
  public TerminalMode getMode() {return _mode;}
  public void typeFancy() {_type = "FANCY";}
  public void typeBasic() {_type = "BASIC";}
  public String getType() {return _type;}

  /**
   * Change a Terminal list of Friends to the given String format.
   * @return array
   */
  public String getFriendsString() {
    String array = "";
    int e;
    if (_friends.size()==1) {
      array += _friends.get(0);
      return array;
    }
    else {
      array += _friends.get(0);
      for (e = 1; e < _friends.size(); e++){
        array += "," ;
        array += _friends.get(e);
      }
    }
    return array;
  }

  /**
   * Set a Terminal to a specific Mode.
   */
  public void setOnSilent() {
    _initialMode = _mode;
    _mode = TerminalMode.SILENCE;
    for (Terminal terminalCall : _callsToMake) {
      sendNotification(terminalCall,_initialMode,_mode);
    }
    _callsToMake.clear();
  }
  public void turnOff() {
    _mode = TerminalMode.OFF;
  }
  public void setOnIdle() {
    _initialMode = _mode;
    _mode = TerminalMode.IDLE;
    for (Terminal terminalCall : _callsToMake) {
      sendNotification(terminalCall,_initialMode,_mode);
    }
    _callsToMake.clear();
  }
  public void setOnBusy() {
    _initialMode = _mode;
    _mode = TerminalMode.BUSY;
  }
  public void setInitialMode() {
    _mode = _initialMode;
  }

  /**
   * Add debts and payments to the Client balance.
   * @param cost value to added to the balance
   */
  public void addDebts(double cost) {
    _debt += cost;
  }
  public void addTerminalPayments(double cost) {
    _debt -= cost;
    _payments += cost;
  }

  /**
   * Checks if this terminal can end the current interactive communication.
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *                                                  it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {return _mode == TerminalMode.BUSY && _onGoing;}

  /**
   * Checks if this terminal can start a new communication.
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {return _mode == TerminalMode.IDLE || _mode == TerminalMode.SILENCE;}

  /**
   * Switch the state of a Communication
   */
  public void switchOnGoing() {_onGoing = !_onGoing;}

  public List <Terminal> getCallsToMake() {return _callsToMake;}

  /**
   * Add a new Terminal to the list of Terminals that must send a Notification.
   * @param terminal given Terminal
   */
  public void addTerminalMode(Terminal terminal) {_callsToMake.add(terminal);}

  /**
   * Send a Notification to a Client.
   * @param terminal given Terminal
   * @param terminalModeOld previous Terminal Mode
   * @param terminalModeNew actual Terminal Mode
   */
  public void sendNotification(Terminal terminal, TerminalMode terminalModeOld, TerminalMode terminalModeNew) {
    int i = 0;
    Notification notification = new Notification(this);
    notification.tester(terminalModeOld, terminalModeNew);
    for (Notification notification1: terminal.getClientTerminal().getNotifications()) {
      if (Objects.equals(notification1.getTerminalNot(), notification.getTerminalNot()) &&
                                     notification1.getType() == notification.getType()) {
        i = 1;
      }
    }
    if (i == 0) {
      terminal.getClientTerminal().getNotifications().add(notification);
    }
  }

}
