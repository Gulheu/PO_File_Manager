package prr.core;

/**
 * Class VoiceCommunication.
 * Type of InteractiveCommunication.
 */
public class VoiceCommunication extends InteractiveCommunication {
    private double _cost;

    /**
     * VoiceCommunication constructor.
     * Creates a VoiceCommunication.
     * @param sender terminal from where the message will be sent
     * @param receiver terminal that will receive the Communication
     */
    public VoiceCommunication(Terminal sender,Terminal receiver) {
        super(sender, receiver);
        super.typeVoice();
    }

    @Override
    public void calculate_cost(Terminal terminal, String _message) {

    }

    /**
     * Calculates the cost of the Communication.
     * @param terminal terminal from where the Communication will be sent
     * @param _units number of minutes
     */
@Override
    public void calculate_cost1 (Terminal terminal,int _units) {
        if (terminal.getClientTerminal().getLevel() == ClientLevel.NORMAL) {
            _cost = 20 * _units;
        }
        else if (terminal.getClientTerminal().getLevel() == ClientLevel.GOLD) {
            _cost = 10 * _units;
        }
        else if (terminal.getClientTerminal().getLevel() == ClientLevel.PLATINUM) {
            _cost = 10 * _units;
        }
        for (String idFriend: terminal.getFriends()) {
            if (getIdReceiver().equals(idFriend)) {
                _cost = 0.5 * _cost;
            }
        }
        super.alterateCost(_cost);
        super.alterateUnits(_units);
    }
}
