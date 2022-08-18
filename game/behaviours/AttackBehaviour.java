package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class that figures out an AttackAction that will attack the Player
 * if Player is in the surrounding location
 */
public class AttackBehaviour extends Action implements Behaviour {

    private final Random random = new Random();

    /**
     * Returns an AttackAction to attack a Player in the surrounding location.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<Action>();

        // Actor's current location
        Location here = map.at(map.locationOf(actor).x(), map.locationOf(actor).y());

        // If there is a player in the actor's surroundings, add an AttackAction
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                actions.add(new AttackAction(destination.getActor(), exit.getName()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Raagrh...";
    }
}
