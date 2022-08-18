package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Enemy;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy {

    /**
     * Chance of Koopa committing suicide
     */
    private final double chanceOfSuicide = 0.1;

    /**
     * Constructor for Goomba.
     */
    public Goomba() {
        super("Goomba", 'g', 20);
        this.intrinsicDamage = 10;
    }

    /**
     * Getter for Goomba's intrinsic weapon
     * @return the Goomba's intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "kicks");
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
        // At each turn, Goomba has 10% chance of suicide (removed from the map)
        if (Math.random() <= chanceOfSuicide) {
            map.removeActor(this);
            return new DoNothingAction();
        }

        return super.playTurn(actions, lastAction, map, display);
    }
}
