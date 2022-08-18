package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import java.util.*;

/**
 * Special action for talking to an Actor
 */
public class TalkAction extends Action {

    /**
     * Hashmap with all the possible responses
     */
    private Map<Integer, String> responses;

    private final Actor target;

    /**
     * Constructor
     */
    public TalkAction(Map<Integer, String> responses, Actor target) {
        this.responses = responses;
        this.target = target;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        // Return randomly selected response
        ArrayList<Integer> indexList = new ArrayList<Integer>();
        Iterator hmIterator = responses.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            indexList.add((int) mapElement.getKey());
        }
        Collections.shuffle(indexList);

        return responses.get(indexList.get(0));
    }


    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public String menuDescription(Actor actor) {
        return actor + " speaks with " + target.toString();
    };

    /**
     * Returns the key used in the menu to trigger this Action.
     * <p>
     * There's no central management system for this, so you need to be careful not to use the same one twice.
     * See https://en.wikipedia.org/wiki/Connascence
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    public String hotkey() {
        return null;
    }

    @Override
    public Action getNextAction() {
        if (target.hasCapability(Status.ENDS_GAME)) {
            return new EndGameAction("Victory!!");
        }
        return null;
    }
}
