package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Fountain;

/**
 * Special action to refill a Bottle with water
 */
public class RefillBottleAction extends Action {
    /**
     * The Fountain to refill the Bottle from
     */
    private Fountain fountain;

    /**
     * Constructor
     * @param fountain the Fountain to refill the Bottle from
     */
    public RefillBottleAction(Fountain fountain) {
        this.fountain = fountain;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item: actor.getInventory()) {
            if (item instanceof Bottle) {
                int waterCapacity = fountain.getWaterCapacity();
                Boolean filled = ((Bottle) item).addWater(fountain.getWater());
                // If the fountain has enough water and the bottle is filled
                if (filled) {
                    return actor.toString() + " filled bottle with water from the " + fountain.toString() + " (" + waterCapacity + "/10)";
                }
                return "The " + fountain.toString() + " does not have enough water";
            }
        }
        // If there is no Bottle in the Player's inventory
        return actor.toString() + " does not have a Bottle to fill";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " fills bottle from the " + fountain.toString();
    }
}
