package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Bottle item
 */
public class Bottle extends Item implements Consumable {

    /**
     * Singleton instance of Bottle
     */
    private static Bottle INSTANCE;

    private Stack<Water> waterSlot = new Stack<Water>();

    /***
     * Constructor.
     */
    private Bottle() {
        super("Bottle", 'b', false);
    }

    /**
     * Singleton method to get an instance of Bottle
     * @return Bottle object
     */
    public static Bottle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Bottle();
        }
        return INSTANCE;
    }

    /**
     * Method to add water from a fountain to the Bottle
     * @param water water to fill to Bottle
     * @return boolean whether the water was successfully added to the Bottle
     */
    public Boolean addWater(Water water) {
        if (water != null) {
            waterSlot.push(water);
            return true;
        }
        return false;
    }

    @Override
    public String consume(Actor actor) {
        return waterSlot.pop().drink(actor);
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<Action>();
        if (!waterSlot.isEmpty()) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
