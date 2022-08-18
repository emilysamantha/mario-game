package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Koopa;
import game.items.SuperMushroom;

/**
 * Special Action for destroying shells of Koopa
 */
public class DestroyShellAction extends Action {
    private Koopa target;
    public DestroyShellAction(Koopa target) {
        this.target = target;
    }

    /**
     * Perform destroy shell action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor + " destroys " + target.toString() + "'s shell";

        // Remove the target
        Location location = map.locationOf(target);
        map.removeActor(target);
        result += System.lineSeparator() + target.toString() + " is killed";

        // Drop a magic mushroom
        location.addItem(new SuperMushroom());
        result += System.lineSeparator() + "Super Mushroom is dropped";

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
        return actor.toString() + " destroys " + target.toString() + " shell";
    }
}