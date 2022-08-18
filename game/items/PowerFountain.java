package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.CanDrink;

/**
 * Class for a Power Fountain
 */
public class PowerFountain extends Fountain{
    /***
     * Constructor.
     */
    public PowerFountain() {
        super("Power Fountain", 'A', false);
    }

    @Override
    public String drink(Actor actor) {
        String result = "";

        // Increase intrinsic attack damage by 15
        try {
            result += ((CanDrink) actor).increaseIntrinsicDamage(15);
        } catch (Exception e) {
            System.out.println("The actor cannot drink");
        }

        return result;
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
