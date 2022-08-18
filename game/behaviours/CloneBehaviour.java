package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Sonic;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

/**
 * A class that allows the behaviour to clone itself every 2 turns given the actor has consumed a Super Mushroom
 */
public class CloneBehaviour extends Action implements Behaviour {
    private int counter;

    public CloneBehaviour() {
        counter = 0;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        counter = counter + 1;
        // check if actor has consumed super mushroom and its second time
        if (actor.hasCapability(Status.SUPER_MUSHROOM_ENEMY) && (counter % 2 == 0) && !(counter == 6)) {
            return this;

            //check if it is last time before effects disappear
        } else if (actor.hasCapability(Status.SUPER_MUSHROOM_ENEMY) && (counter == 6)) {
            actor.removeCapability(Status.SUPER_MUSHROOM_ENEMY);
            actor.setDisplayChar(toLowerCase(actor.getDisplayChar()));
            counter = 0;
            return this;
        }
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // get exists and clone itself
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (!exit.getDestination().containsAnActor()) {
                exit.getDestination().addActor(new Sonic());
                return actor + " successfully clones itself";
            }
        }
        return actor + " is unable to clone";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " clones itself.";
    }
}
