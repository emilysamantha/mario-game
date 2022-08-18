package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for Items that can be consumed by an actor
 */
public interface Consumable {
    /**
     * Performs the effects that take place when consuming a magic item
     * @param actor the Actor performing the consume action
     * @return a string description of what was executed
     */
    String consume(Actor actor);
}
