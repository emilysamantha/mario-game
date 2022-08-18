package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Coin item
 */
public class Coin extends Item {
    /**
     * Coin's value
     */
    private int value;

    /***
     * Constructor.
     *  @param value the value of the Coin
     */
    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
    }

    /**
     * Getter for the Coin's value.
     * @return the Coin's value
     */
    public int getValue(){
        return this.value;
    }
}
