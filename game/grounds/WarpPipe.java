package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpActorAction;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;
import game.actors.Sonic;

/**
 * The following class is a Ground class that will allow the player to teleport from one GameMap to another
 */
public class WarpPipe extends Ground {

    private int counter;

    public WarpPipe() {
        super('C');
        counter = 0;
    }

    /**
     *
     * @param actor the Actor to check
     * @return false unless piranha so no enemies can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof  PiranhaPlant || actor instanceof Sonic)
        {
            return true;
        }
        return false;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // get actionList, and JumpActorAction if possible at given location
        ActionList actions = new ActionList();

        //check that actor is player and not enemy
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY))
        {
            // check that the player is currently not standing on the WarPipe and there is no pirhana on it
            if (!(direction == "") && !location.containsAnActor())
            {
                // if not standing on it allow player to jump on it
                actions.add(new JumpActorAction(location,direction,1,0));
            }
            else if (direction == "")
            {
                // if player is on WarPipe allow actor to teleport to other GameMap
                actions.add(new TeleportAction(location));
            }
        }

        return actions;
    }

    @Override
    public String toString() {
        return "WarPipe";
    }

    /**
     * This method adds a piranha to the WarPipe after one turn
     * @param location The location of the Ground
     */

    @Override
    public void tick(Location location) {
        if (counter == 0 ){
            PiranhaPlant piranha= new PiranhaPlant();
            if (!location.containsAnActor())
            {
                location.addActor(piranha);
            }
            counter++;
        }
    }


}
