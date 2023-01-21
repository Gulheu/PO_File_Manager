package prr.core;


/**
 * Class InteractiveCommunication.
 * Type of Communication.
 */
abstract public class InteractiveCommunication extends Communication {

    /**
     * Interactive Communication constructor.
     * @param sender attribute from Communication
     * @param receiver attribute from Communication
     */
    public InteractiveCommunication(Terminal sender, Terminal receiver) {
        super(sender, receiver);
    }

}
