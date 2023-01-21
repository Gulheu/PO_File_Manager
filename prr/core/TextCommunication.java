package prr.core;


/**
 * Class TextCommunication.
 * Type of Communication.
 */
public class TextCommunication extends Communication {
    private int _units;
    private double _cost;

    /**
     * TextCommunication constructor.
     * Creates a TextCommunication.
     * @param sender terminal from where the message will be sent
     * @param receiver terminal that will receive the message
     */
    public TextCommunication(Terminal sender,Terminal receiver) {
        super(sender, receiver);
        super.typeText();
    }

    /**
     * Calculates the cost of the Communication.
     * @param terminal terminal from where the message will be sent
     * @param _message message that will be sent
     */
    public void calculate_cost (Terminal terminal, String _message){
        _units = _message.length();
        if (terminal.getClientTerminal().getLevel() == ClientLevel.NORMAL) {
                if (_units < 50) {
                    _cost = 10;
                }
                else if (_units >= 50 && _units < 100) {
                    _cost = 16;
                }
                else if (_units >= 100) {
                    _cost = 2 * _units;
                }

        }
        else if (terminal.getClientTerminal().getLevel() == ClientLevel.GOLD) {
            if (_units < 50) {
                _cost = 10;
            }
            else if (_units >= 50 && _units < 100) {
                _cost = 10;
            }
            else if (_units >= 100) {
                _cost = 2 * _units;
            }
        }
        else if (terminal.getClientTerminal().getLevel() == ClientLevel.PLATINUM) {
            if (_units < 50) {
                _cost = 0;
            }
            else if (_units >= 50 && _units < 100) {
                _cost = 4;
            }
            else if (_units >= 100) {
                _cost = 4;
            }
        }
        super.alterateCost(_cost);
        super.alterateUnits(_units);
    }

    @Override
    public void calculate_cost1(Terminal terminal, int _units) {

    }
}
