package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.WalkOnLavaAction;

/**
 * Class representing Lava
 */
public class Lava extends Ground {

    public Lava() {
        super('L');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // get actionList, and JumpActorAction if possible at given location
        ActionList actions = new ActionList();
        // If the actor is not an Enemy, they may walk on the Lava
        if (direction != "" && actor.hasCapability(Status.HOSTILE_TO_ENEMY))
        {
            // return WalkOnLavaction
            actions.add(new WalkOnLavaAction(actor,location,direction)) ;
        }
        return actions;
    }
}