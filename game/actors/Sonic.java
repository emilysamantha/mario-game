package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.TeleportAction;
import game.behaviours.*;

import java.util.List;

import static java.lang.Character.toLowerCase;
import static javax.swing.UIManager.put;

/**
 * Sonic class
 * Sonic is an enemy that can blink to any location on the GameMap that the actor can enter
 * Sonic is also able to teleport to other GameMaps
 * Sonic is also able to consume Super Mushrooms if he blinks to a Location that contains a Super Mushroom,
 * which enables him to clone himself for every second turn until its effects disappear on the 6th turn
 */
public class Sonic extends Enemy {

    public Sonic() {
        //add behaviours
        super("Sonic", 's', 100);
        super.behaviours.put(3, new ConsumeSMBehaviour());
        super.behaviours.put(4, new DrinkBehaviour());
        super.behaviours.put(5,new CloneBehaviour());
        super.behaviours.put(9, new TeleportBehaviour());
        super.behaviours.put(10, new BlinkBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location here = map.at(map.locationOf(this).x(), map.locationOf(this).y());

        // If there is a player in the actor's surroundings, add a FollowBehaviour
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                this.behaviours.put(2, new FollowBehaviour(destination.getActor()));
            }
        }
        //check if sonic just teleported, so it doesn't get stuck in teleport loop
        if (lastAction instanceof TeleportAction){
            this.behaviours.remove(9);
        }

        // Return random action from behaviours
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}



