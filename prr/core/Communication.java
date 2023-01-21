package prr.core;

import java.io.Serializable;


/**
 * Abstract Class Terminal.
 */
abstract public class Communication implements Serializable {

    /**
     * Serial number for serialization.
     */
    private static final long serialVersionUID = 202208091753L;

    private int _idCommunication = 1;
    private static int _nextIdCommunication = 1;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOnGoing;
    private String _type;
    private Terminal _receiver;
    private Terminal _sender;
    private int _units;

    /**
     * Communication constructor.
     * @param sender attribute from Communication
     * @param receiver attribute from Communication
     */
    public Communication(Terminal sender,Terminal receiver) {
        _idCommunication = _nextIdCommunication;
        _nextIdCommunication++;
        _receiver = receiver;
        _sender = sender;
        _isPaid = false;
        _isOnGoing = false;
        _cost = 0;
        _units = 0;
    }

    public int getIdCommunication() {return _idCommunication;}
    public boolean getIsPaid() {return _isPaid;}
    public void switchIsPaid() {_isPaid = !_isPaid;}
    public double getCost() {return _cost;}
    public boolean getIsOnGoing() {return _isOnGoing;}
    public int getUnits() {return _units;}
    public String getIdSender() {return _sender.getId();}
    public String getIdReceiver() {return _receiver.getId();}
    public String getType() {return _type;}
    public void switchOnGoing() {_isOnGoing = !_isOnGoing;}
    public void typeText() {_type = "TEXT";}
    public void alterateCost(double cost) {_cost = cost;}
    public void alterateUnits(int units) {_units = units;}
    public void typeVoice() {_type = "VOICE";}
    public void typeVideo() {_type = "VIDEO";}
    public String getIsOnGoingString() {
        if (_isOnGoing) {
            return "ONGOING";
        }
        else {
           return "FINISHED" ;
        }
    }
    public abstract void calculate_cost(Terminal terminal, String _message);
    public abstract void calculate_cost1(Terminal terminal, int _units);

}
