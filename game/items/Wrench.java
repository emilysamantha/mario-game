package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.items.Tradable;

/**
 * Wrench weapon item
 */
public class  Wrench extends WeaponItem implements Tradable {

    private final int price = 200;

    /**
     * Constructor for Wrench.
     */
    public Wrench() {
        super("Wrench", 'W', 50, "hits", 80);
        this.addCapability(Status.DESTROY_SHELL);
    }

    @Override
    public int getPrice() {
        return price;
    }
}
