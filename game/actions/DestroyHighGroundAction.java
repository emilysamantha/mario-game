package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;
import game.grounds.*;

/**
 * Special Action for destroying High Ground when player has consumer power star
 */
public class DestroyHighGroundAction extends Action {
    /**
     * The location to be destroyed
     */
    private Location destination;

    /**
     * The actor destroying the High Ground
     */
    private Actor actor;

    /**
     * The direction of the action
     */
    private String direction;

    /**
     * The name of the destroyed ground
     */
    private String destroyedGround;

    /**
     * Constructor
     *
     * @param destination the Location of where the actor wants to jump tp
     * @param actor the actor that will jump and destroy the HigherGround
     * @param direction direction of the desctionation from actors current location
     */
    public DestroyHighGroundAction(Location destination, Actor actor, String direction) {
        this.destination = destination;
        this.actor = actor;
        this.direction = direction;
        this.destroyedGround = destination.getGround().toString();
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        Dirt dirt = new Dirt();
        Coin coin = new Coin(5);
        this.destination.setGround(dirt);
        this.destination.addItem(coin);
        return menuDescription(actor);
    };

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public String menuDescription(Actor actor) {
        return actor.toString() + " moves " + this.direction + " and destroys " + destroyedGround.toString();
    }
}