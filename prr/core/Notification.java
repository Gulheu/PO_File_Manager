package prr.core;

import java.io.Serializable;

/**
 * Abstract Class Notification.
 */
public class Notification implements Serializable {

    /**
     * Serial number for serialization.
     */
    private static final long serialVersionUID = 202208091753L;

    private NotificationType _type;
    private Terminal _terminal;
    /**
     * Notification Constructor.
     * @param terminal attribute from Notification
     */
    public Notification(Terminal terminal) {
        _terminal = terminal;
    }

    /**
     * Defines the type of Notification.
     * @param terminalModeOld object that holds the old terminal mode.
     * @param terminalModeNow object that holds the new terminal mode.
     */
    public void tester(TerminalMode terminalModeOld,TerminalMode terminalModeNow ) {
        if (terminalModeOld == TerminalMode.OFF) {
            if(terminalModeNow == TerminalMode.IDLE) {
                _type = NotificationType.O2I;
            }
            if(terminalModeNow == TerminalMode.SILENCE) {
                _type = NotificationType.O2S;
            }
        }
        if(terminalModeOld == TerminalMode.SILENCE) {
            if(terminalModeNow == TerminalMode.IDLE) {
                _type = NotificationType.S2I;
            }
        }
        if(terminalModeOld == TerminalMode.BUSY) {
            if(terminalModeNow == TerminalMode.IDLE) {
                _type = NotificationType.B2I;
            }
        }
    }
    public NotificationType getType() {return _type;}
    public String getTerminalNot() {return _terminal.getId();}

}
