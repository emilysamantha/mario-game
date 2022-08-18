package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillBottleAction;
import java.util.ArrayList;
import java.util.List;

public abstract class Fountain extends Item {

    /**
     * The current capacity of water in the Fountain
     */
    protected int waterCapacity;

    /**
     * The amount of water reduced each time an actor sips from the Fountain
     */
    protected final int waterPerSip = 5;

    /**
     * The maximum capacity of water in the Fountain
     */
    private final int maxWaterCapacity = 10;

    /**
     * Counter for tick method
     */
    private int counter;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Fountain(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.waterCapacity = this.maxWaterCapacity;
        this.counter = 0;
    }

    /**
     * Method to apply the effects of the Fountain's water when an actor consumes it
     * @param actor the actor drinking
     * @return description of the effects that took place
     */
    public abstract String drink(Actor actor);

    /**
     * Method to get the Water from this Fountain
     * Will only return the Water if the Fountain still has water capacity
     * @return Water from the Fountain
     */
    public abstract Water getWater();

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<Action>();
        actions.add(new RefillBottleAction(this));
        return actions;
    }

    /**
     * Getter for waterCapacity
     * @return waterCapacity of Fountain
     */
    public int getWaterCapacity() {
        return this.waterCapacity;
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.waterCapacity <= 0) {
            this.counter += 1;
            if (this.counter % 5 == 0) {
                this.waterCapacity = this.maxWaterCapacity;
            }
        }
    }
}