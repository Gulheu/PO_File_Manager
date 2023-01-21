package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class Client.
 */
public class Client implements Serializable {

    /**
     * Serial number for serialization.
     */
    private static final long serialVersionUID = 202208091753L;

    private String _key;
    private String _name;
    private int _taxId;
    private boolean _notificationsOn;
    private ClientLevel _level;
    private List <String> _terminals;
    private int _payments;
    private int _debt;
    private String _notificationWord;
    private int _nTerminals;
    private List <Notification> _notifications;
    private int _textCount = 0;
    private int _videoCount = 0;

    /**
     * Client constructor.
     * @param key attribute from Client
     * @param name attribute from Client
     * @param taxId attribute from Client
     */
    public Client(String key, String name, int taxId) {
        _key = key;
        _name = name;
        _taxId = taxId;
        _level = ClientLevel.NORMAL;
        _notificationsOn = true;
        _notificationWord = "YES";
        _payments = 0;
        _debt = 0;
        _terminals = new ArrayList<>();
        _notifications = new ArrayList<>();
        _nTerminals = 0;
    }

    /**
     * Turn on and off the Notifications of a Client Terminal.
     */
    public void turnOffNotifications() {
        _notificationsOn = false;
        _notificationWord = "NO";
    }
    public void turnOnNotifications() {
        _notificationsOn = true;
        _notificationWord = "YES";
    }

    /**
     * Increment the counters of Communications in one unit.
     */
    public void incrementTextCount() {_textCount += 1;}
    public void incrementVideoCount() {_videoCount += 1;}

    /**
     * Reset the counters of Communications.
     */
    public void resetTextCount() {_textCount = 0;}
    public void resetVideoCount() {_videoCount = 0;}

    public String getKey() {return _key;}
    public String getName() {return _name;}
    public int getTaxNumber() {return _taxId;}
    public ClientLevel getLevel() {return _level;}
    public boolean areNotificationsOn() {return _notificationsOn;}
    public String getNotificationWord() {return _notificationWord;}
    public List <Notification> getNotifications() {return _notifications;}
    public int getPayment() {return _payments;}
    public int getDebts() {return _debt;}
    public int getBalance() {return _payments - _debt;}
    public int getTextCount() {return _textCount;}
    public int getVideoCount() {return _videoCount;}
    public List <String> getTerminals() {return _terminals;}

    /**
     * Add a new Terminal to the Client list of Terminals.
     * @param terminal Terminal to be added
     */
    public void addTerminal(Terminal terminal) {_terminals.add(terminal.getId());}

    /**
     * Return the number of Terminals associated with a Client.
     * @return _nTerminals
     */
    public int getNTerminals() {
        _nTerminals = _terminals.size();
        return _nTerminals;
    }

    /**
     * Add debts and payments to the Client balance.
     * @param cost value to added to the balance
     */
    public void addDebts(double cost) {_debt += cost;}
    public void addClientPayments(double cost) {
        _debt -= cost;
        _payments += cost;
    }

    /**
     * Evolution of the Client Level.
     */
    public void normalToGold() {
        if (_level == ClientLevel.NORMAL && getBalance() > 500) {
            _level = ClientLevel.GOLD;
        }
    }
    public void goldToNormal() {
        if (_level == ClientLevel.GOLD && getBalance() < 0) {
            _level = ClientLevel.NORMAL;
        }
    }
    public void goldToPlatinum() {
        if (_level == ClientLevel.GOLD && getVideoCount() == 6 && getBalance() >= 0) {
            _level = ClientLevel.PLATINUM;
            resetVideoCount();
        }
    }
    public void platinumToNormal() {
        if (_level == ClientLevel.PLATINUM && getBalance() < 0) {
            _level = ClientLevel.NORMAL;
        }
    }
    public void platinumToGold() {
        if (_level == ClientLevel.PLATINUM && getTextCount() == 3 && getBalance() >= 0) {
            _level = ClientLevel.GOLD;
            resetTextCount();
        }
    }

}
