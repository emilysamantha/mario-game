package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.actions.DestroyShellAction;
import game.actors.Enemy;

/**
 * Reptilian mini-troopers of the Koopa Troop from the Mario franchise
 */
public class Koopa extends Enemy implements PostAttack{
    /**
     * Constructor for Koopa.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.intrinsicDamage = 30;
        this.addCapability(Status.NOT_DORMANT);
    }

    /**
     * Getter for Koopa's intrinsic weapon
     * @return the Koopa's intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "punches");
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        // If the actor has a Wrench and the Koopa is in Dormant state, the actor has the option to destroy the Koopa shell
        if(this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.DESTROY_SHELL)) {
            actions.add(new DestroyShellAction(this));
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If the enemy is in Dormant state, it is not allowed to attack, follow, or wander
        if (this.hasCapability(Status.DORMANT)) {
            return new DoNothingAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String postAttack(GameMap map) {
        String result = "";

        // If target is a Not Dormant, then change into Dormant state
        if (this.hasCapability(Status.NOT_DORMANT)) {
            // Set to Dormant state
            this.removeCapability(Status.NOT_DORMANT);
            this.addCapability(Status.DORMANT);

            // Set display character to D
            this.setDisplayChar('D');

            result += System.lineSeparator() + target + " is now dormant";
        }

        return result;
    }
}
