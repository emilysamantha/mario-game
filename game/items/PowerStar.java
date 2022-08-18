package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.toUpperCase;

/**
 * Power Star magical item
 */
public class PowerStar extends Item implements Consumable, Tradable {
    private int counter;
    private final int price = 600;

    /**
     * Constructor for PowerStar
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.counter = 0;
    }

    /**
     * Performs the effects that take place when consuming a magic item
     * @param actor the Actor performing the consume action
     * @return a string description of what was executed
     */
    @Override
    public String consume(Actor actor) {

        String result = " - 10 turns remaining";

        // Heal the actor by 200 HP
        actor.heal(200);
        result += System.lineSeparator() + actor + " has been healed by 200 HP";

        // Make invincible
        actor.addCapability(Status.INVINCIBLE);

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
    public void tick(Location currentLocation) {
        counter += 1;

        // In 10 turns Power Star will fade away and be removed from the game
        if (counter % 10 == 0) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public int getPrice() {
        return price;
    }
}
