package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * Special Action for killing other Actors.
 */
public class KillAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public KillAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform kill action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = actor + " kills " + target;

        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getInventory())
            dropActions.add(item.getDropAction(actor));
        for (Action drop : dropActions)
            drop.execute(target, map);
        // remove actor
        map.removeActor(target);
        result += System.lineSeparator() + target + " is killed.";

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
        return actor + " attacks " + target + " at " + direction;
    }
}
