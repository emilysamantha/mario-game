package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.FlyingKoopa;
import game.actors.Koopa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Final stage of Tree
 */
public class Mature extends Tree {

    /**
     * Chance of the mature to spawn a Koopa
     */
    private final double chanceToSpawnKoopa = 0.15;

    /**
     * Chance for the tree to die
     */
    private final double chanceToWither = 0.2;

    private final double chanceToSpawnKoopaType = 0.5;

    /**
     * Number of turns it takes to grow a Sprout
     */
    private final int sproutTurns = 5;

    private final String name = "Mature";

    /**
     * Constructor.
     */
    public Mature() {
        super("Mature", 'T', 0.7, 30);
    }

    @Override
    public void tick(Location location) {
        // Add one to counter to keep track of turns
        super.tickCounter();

        // If the location doesn't contain actor try spawn koopa
        if (!location.containsAnActor()) {
            this.spawnKoopa(location);
        }

        // Every 5 turns create Sprout in nearby ground
        if (super.getCounter() % sproutTurns == 0) {
            this.growSprout(location);
        }

        // Try kill Mature
        this.wither(location);
    }

    /**
     * Spawn a Koopa at given location
     *
     * @param location
     */
    private void spawnKoopa(Location location) {
        if (Math.random() <= chanceToSpawnKoopa) {
            Koopa koopa;
            if(Math.random() <= chanceToSpawnKoopaType) {
                koopa = new Koopa();
            } else {
                koopa = new FlyingKoopa();
            }
            location.addActor(koopa);
        }
    }

    /**
     * Grows a sprout in one of the surrounding fertile squares randomly
     *
     * @param location location of Mature Tree
     */
    private void growSprout(Location location) {
        List<Exit> exitList = location.getExits();
        Exit exit;

        //generate random order
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < exitList.size(); i++) {
            numberList.add(i);
        }
        Collections.shuffle(numberList);

        //spawn sprout randomly on exit if there is any fertile grounds
        for (int index : numberList) {
            exit = exitList.get(index);
            if (exit.getDestination().getGround() instanceof Dirt) {
                Sprout sprout = new Sprout();
                exit.getDestination().setGround(sprout);
                break;
            }
        }
    }

    /**
     * Turn into dirt
     *
     * @param location location of Mature Tree
     */
    private void wither(Location location) {
        if (Math.random() <= chanceToWither) {
            Dirt dirt = new Dirt();
            location.setGround(dirt);
        }
    }
}