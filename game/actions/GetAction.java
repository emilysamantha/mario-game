package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special action to get an item and add it to an Actor's inventory
 */
public class GetAction extends Action {

    /**
     * The item that will be added to the actor's inventory
     */
    private Item item;

    public GetAction(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (item != null) {
            actor.addItemToInventory(item);
            return actor.toString() + " gets " + item.toString();
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " gets " + item.toString();
    }
}
