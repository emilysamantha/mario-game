package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Class for Water
 */
public class Water extends Item implements Consumable {

    /**
     * The Fountain where the Water was obtained from
     */
    private Fountain fountainSource;

    /***
     * Constructor.
     */
    public Water(Fountain fountainSource) {
        super("Water", 'w', false);
        this.fountainSource = fountainSource;
    }

    /**
     * Method to apply the water's effects to the Actor drinking it
     * @param actor the Actor drinking the water
     * @return a description of the effects that took place
     */
    public String drink(Actor actor) {
        String result = "";
        result += actor.toString() + " drinks water from " + fountainSource.toString();
        result += System.lineSeparator() + fountainSource.drink(actor);
        return result;
    }

    @Override
    public String consume(Actor actor) {
        return this.drink(actor);
    }
}
