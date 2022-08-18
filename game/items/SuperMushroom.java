package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.toUpperCase;

/**
 * Super Mushroom magical item
 */
public class SuperMushroom extends Item implements Consumable, Tradable {

    private final int price = 400;

    /**
     * Constructor for SuperMushroom
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addCapability(Status.SUPER_MUSHROOM);
    }

    /**
     * Performs the effects that take place when consuming a magic item
     * @param actor the Actor performing the consume action
     * @return a string description of what was executed
     */
    @Override
    public String consume(Actor actor) {
        // Increase their max HP
        actor.increaseMaxHp(50);
        String result = actor.toString() + "'s max HP has been increased by 50";

        // Set display character to uppercase
        actor.setDisplayChar(toUpperCase(actor.getDisplayChar()));


        actor.addCapability(Status.SUPER_MUSHROOM);
        // Remove from the actor's inventory
        actor.removeItemFromInventory(this);




        return result;
    }

    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<Action>();
        actions.add(new ConsumeAction(this));

        return actions;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
