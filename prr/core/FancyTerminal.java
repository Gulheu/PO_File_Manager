package prr.core;


/**
 * Class FancyTerminal.
 * Type of Terminal.
 */
public class FancyTerminal extends Terminal{

    /**
     * Creates a FancyTerminal.
     * @param id attribute from Terminal
     * @param client attribute from Terminal
     */
    public FancyTerminal(String id, Client client) {
        super(id, client);
        super.typeFancy();
    }

}
