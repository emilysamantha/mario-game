package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;

/**
 * Second stage of Tree
 */
public class Sapling extends Tree {

    /**
     * Chance of Sapling to drop a Coin
     */
    private final double chanceToDropCoin = 0.1;

    /**
     * The number of turns it takes for Sapling to grow into Mature
     */
    private final int turnsToGrowIntoMature = 10;

    /**
     * The value of Coin to be dropped by Sapling
     */
    private final int coinValue = 20;

    private final String name = "Sapling";

    /**
     * Constructor.
     */
    public Sapling() {
        super("Sapling",'t',0.8,20);
    }


    @Override
    public void tick(Location location) {
        // add one to counter to keep track of turns
        super.tickCounter();

        // turn to mature on 10th turn
        if (super.getCounter() == turnsToGrowIntoMature) {
            Mature mature = new Mature();
            location.setGround(mature);

        } else {
            // try drop coin
            this.dropCoin(location);
        }
    }

    /**
     * Drops a coin at given location
     * @param location location to drop the coin
     */
    private void dropCoin(Location location) {
        if (Math.random() <= chanceToDropCoin) {
            Coin coin = new Coin(coinValue);
            location.addItem(coin);
        }
    }
}