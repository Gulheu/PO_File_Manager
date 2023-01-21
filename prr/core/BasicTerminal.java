package prr.core;


/**
 *  Class BasicTerminal.
 *  Type of terminal.
 */
public class BasicTerminal extends Terminal{

    /**
     * Creates BasicTerminal
     * @param id attribute from Terminal
     * @param client attribute from Terminal
     */
    public BasicTerminal(String id, Client client) {
        super(id, client);
        super.typeBasic();
    }

}
