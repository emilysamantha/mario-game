package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

/**
 * First stage of Tree
 */
public class Sprout extends Tree {
    /**
     * Chance of Sprout to spawn a Goomba
     */
    private final double chanceToSpawnGoomba = 0.1;

    /**
     * The number of turns it takes for Sprout to grow into Sapling
     */
    private final int turnsToGrowIntoSapling = 10;

    /**
     * Constructor.
     */
    public Sprout() {
        super("Sprout",'+',0.9,10);
    }

    @Override
    public void tick(Location location) {
        // add one to counter to keep track of turns
        super.tickCounter();

        // if 10th turn, turn to Sapling or try spawn goomba
        if (super.getCounter() == turnsToGrowIntoSapling) {
            Sapling sapling = new Sapling();
            location.setGround(sapling);
        }else if (!location.containsAnActor()) {
            this.spawnGoomba(location);
        }
    }

    private void spawnGoomba(Location location) {
        if (Math.random() <= chanceToSpawnGoomba) {
            Goomba goomba = new Goomba();  // remove
            location.addActor(goomba);
        }
    }
}