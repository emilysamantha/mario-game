package game.actors;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for actors that need to carry out special post attack actions (after they are unconscious)
 */
public interface PostAttack {
    String postAttack(GameMap map);
}
