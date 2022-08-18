package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Special class for jumping to higher ground
 */
public class JumpActorAction extends Action {
    /**
     * Location that actor wants to jump on
     */
    private Location moveToLocation;

    /**
     * One of the 8-d navigation
     */
    private String direction;

    /**
     * Or the command key
     */
    private String hotKey;

    /**
     * The jump success rate of the actor
     */
    private double jumpSuccessRate;

    /**
     * The damage that will be applied to the actor if they jump unsuccessfully
     */
    private int fallDamage;

    /**
     * Constructor to create an Action that will move the Actor to a Location in a given Direction.  A currently-unused
     * menu hotkey will be assigned.
     *
     * @param moveToLocation  Location to move to
     * @param direction       String describing the direction to move in, e.g. "north"
     * @param jumpSuccessRate the chance in decimals that the actor can successfully jump onto the Location
     * @param fallDamage      amount of hp that is deducted by actor is the fail is unsuccessfull
     */
    public JumpActorAction(Location moveToLocation, String direction, double jumpSuccessRate, int fallDamage) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.hotKey = null;
        this.jumpSuccessRate = jumpSuccessRate;
        this.fallDamage = fallDamage;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    public String execute(Actor actor, GameMap map) {
        if (Math.random() <= jumpSuccessRate) {
            map.moveActor(actor, moveToLocation);
            return actor.toString() + " successfully jumps to " + direction + " onto " + moveToLocation.getGround().toString() + " (" + this.moveToLocation.x() + ", " + this.moveToLocation.y() + ")";
        } else {
            actor.hurt(fallDamage);
            return "Jump was unsuccessful, " + actor.toString() + " receives " + fallDamage + " damage";
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    public String menuDescription(Actor actor) {
        return actor.toString() + " jumps " + direction + " onto " + moveToLocation.getGround().toString()  + " (" + this.moveToLocation.x() + ", " + this.moveToLocation.y() + ")";
    };

    /**
     * Returns the key used in the menu to trigger this Action.
     * <p>
     * There's no central management system for this, so you need to be careful not to use the same one twice.
     * See https://en.wikipedia.org/wiki/Connascence
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    public String hotkey() {
        return hotKey;
    }
}



