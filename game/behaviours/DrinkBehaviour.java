package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.items.Fountain;

/**
 * A class that checks for a Fountain in the surroundings and
 * returns a ConsumeAction to drink the water if a Fountain is found
 */
public class DrinkBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.at(map.locationOf(actor).x(), map.locationOf(actor).y());
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            for (Item item : destination.getItems()) {
                if (item instanceof Fountain && ((Fountain) item).getWater() != null) {
                    return new ConsumeAction(((Fountain) item).getWater());
                }
            }
        }
        return null;
    }
}
