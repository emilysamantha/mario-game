package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    INVINCIBLE, // use this to indicate that the actor is currently invincible
    SUPER_MUSHROOM, // use this status to tell that the player has consumed a Super Mushroom
    SUPER_MUSHROOM_ENEMY, //use this when enemy consumes Super Mushroom
    NOT_DORMANT, // use this to tell that the Koopa has not been defeated
    DORMANT, // use this status to tell that the Koopa has been defeated and is in Dormant state
    DESTROY_SHELL, // use this to tell that an actor can destroy a Koopa's shell
    HAS_KEY, // use this to tell that an actor has obtained a key and is able to interact with Princess Peach
    FIRE_ATTACK, // use this to tell that an attack will drop a fire
    CAN_FLY, // use this to tell that an actor is able to fly over trees and walls
    ENDS_GAME // use this to tell that interacting with this actor will end the game

}
