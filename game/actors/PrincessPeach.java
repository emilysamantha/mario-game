package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.TalkAction;
import java.util.HashMap;
import java.util.Map;

/**
 * Princess Peach class
 * Placed next to Bowser on the map
 * Cannot move, attack, or be attacked
 * Player who has defeated Bowser and obtained a key can interact with Princess Peach to end the game
 */
public class PrincessPeach extends Ally {
    public PrincessPeach() {
        super("Princess Peach", 'P');
        this.addCapability(Status.ENDS_GAME);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // If the other actor has obtained a key, they can interact with Princess Peach to end the game with a victory message
        if (otherActor.hasCapability(Status.HAS_KEY)) {
            Map<Integer, String> responses = new HashMap<Integer, String>();
            responses.put(1, "Princess Peach says: Thank you for saving me from Bowser!");
            responses.put(1, "Princess Peach says: Finally someone has come and saved me!");
            actions.add(new TalkAction(responses, this));
        }

        return actions;
    }
}
