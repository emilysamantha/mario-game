package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.grounds.WarpPipe;

/**
 * A class that allows the behaviour to teleport
 */

public class TeleportBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation= map.locationOf(actor);
        if (actorLocation.getGround() instanceof WarpPipe)
        {
            return new TeleportAction(actorLocation);
        }
        return null;
    }
}
