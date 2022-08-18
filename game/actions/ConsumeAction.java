package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * Special Action for consuming items
 */
public class ConsumeAction extends Action {
    /**
     * The Item that is to be consumed
     */
    private Consumable item;

    /**
     * Constructor.
     *
     * @param item the Item name to be consumed
     */
    public ConsumeAction(Consumable item) {
        this.item = item;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @exception if the item to be consumed does not implement Consumable
     * @see Consumable
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";

        // Consume the item
        try {
            result += System.lineSeparator() + ((Consumable) this.item).consume(actor);
        } catch (Exception e) {
            System.out.println("Item is not consumable");
        }

        result += System.lineSeparator() + actor.toString() + " consumes " + this.item;

        return result;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes " + item.toString();
    }
}