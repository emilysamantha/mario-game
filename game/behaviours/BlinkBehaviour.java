package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.grounds.WarPipeManager;
import game.grounds.WarpPipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A class that allows the behaviour to instantly move to a different location
 */
public class BlinkBehaviour extends Action implements Behaviour {

    //get locations of all WarpPipes
    private WarPipeManager warPipeManager = WarPipeManager.getInstance();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return this;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<Location> locationList = new ArrayList<>();
        // iterate through all the locations to determine what locations that Sonic can blink to me
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                Location location = map.at(x, y);

                // check if the actor can enter and if it already has an actor there
                if (location.canActorEnter(actor) && !location.containsAnActor()) {
                    locationList.add(location);
                }
            }
        }


        Collections.shuffle(locationList);
        Location blinkLocation = locationList.get(0);

        // try to move actor to location
        try {
            map.moveActor(actor, blinkLocation);
        }
        catch (Exception e){
            System.out.println(actor.toString() + "cannot blink to location :(");
            return actor.toString() + "cannot blink to location :(";
        }

        return menuDescription((actor));
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " blinks to new location";
    }
}
