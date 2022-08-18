package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.GetAction;
import game.actions.TalkAction;
import game.actions.TradeAction;
import game.items.Bottle;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;
import java.util.HashMap;
import java.util.Map;

/**
 * Toad, a friendly actor
 */
public class Toad extends Ally {

    public Toad() {
        super("Toad", 'O');
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            Map<Integer, String> responses = new HashMap<Integer, String>();

            // If actor does not have Wrench in inventory, add response
            if (!otherActor.hasCapability(Status.DESTROY_SHELL)) {
                responses.put(1, "Toad says: You might need a wrench to smash Koopa's hard shells.");
            }

            // If actor has not consumed Power Star
            if (otherActor.hasCapability(Status.INVINCIBLE)) {
                responses.put(2, "Toad says: You better get back to finding the Powers Stars.");
            }

            responses.put(3, "Toad says: The Princess is depending on you! Your are our only hope.");
            responses.put(4, "Toad says: Being imprisoned in these walls can drive a fungus crazy :(");

            actions.add(new TalkAction(responses, this));

            // The actor is allowed to trade items with the Toad
            actions.add(new TradeAction(new Wrench()));
            actions.add(new TradeAction(new SuperMushroom()));
            actions.add(new TradeAction(new PowerStar()));

            // Actor can obtain Bottle from Toad
            actions.add(new GetAction(Bottle.getInstance()));
        }
        return actions;
    }

}
