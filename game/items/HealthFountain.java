package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Class for a Health Fountain
 */
public class HealthFountain extends Fountain {
    /***
     * Constructor.
     */
    public HealthFountain() {
        super("Health Fountain", 'H', false);
    }

    @Override
    public String drink(Actor actor) {
        // Increase actor's HP by 50
        actor.heal(50);
        return actor.toString() + "'s HP is increased by 50";
    }

    @Override
    public Water getWater() {
        // If there is still enough water capacity
        if (this.waterCapacity >= this.waterPerSip) {
            // Reduce the water capacity with each refill
            this.waterCapacity -= this.waterPerSip;
            // Return the water
            return new Water(this);
        }
        // Else return nothing
        return null;
    }
}