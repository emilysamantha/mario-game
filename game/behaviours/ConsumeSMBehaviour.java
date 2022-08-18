package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.items.SuperMushroom;

import static java.lang.Character.toUpperCase;

/**
 * A class that allows to consume Super Mushroom
 */
public class ConsumeSMBehaviour extends Action implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {

        //check if Location contains actor
        Location here = map.locationOf(actor);

        for (Item item:here.getItems())
        {
            if (item instanceof SuperMushroom){
                return this;
            }
        }
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);

        // Increase their max HP
        actor.increaseMaxHp(50);
        String result = actor.toString() + "'s max HP has been increased by 50";

        // Set display character to uppercase
        actor.setDisplayChar(toUpperCase(actor.getDisplayChar()));

        // Set the actor status based on the item consumed

        actor.addCapability(Status.SUPER_MUSHROOM_ENEMY);

        Item superMushroom= null;
        for (Item item: here.getItems())
        {
            if (item instanceof SuperMushroom){
                superMushroom=item;
            }
        }
        map.at(here.x(),here.y()).removeItem( superMushroom);

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes  Super Mushroom";
    }
}



