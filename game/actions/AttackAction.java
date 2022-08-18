package game.actions;

import java.util.Random;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.*;
import game.actors.PostAttack;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Perform the Action.
	 *
	 * @param actor The actor attacking.
	 * @param map The map the actor is on.
	 * @return a description of the attack that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();
		int damage = weapon.damage();

		// If actor has consumed a Power Star, the attack will instantly kill the target
		if (actor.hasCapability(Status.INVINCIBLE)) {
			return new KillAction(target, this.direction).execute(target, map);
		}

		// Implement chance of attacking the target
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target;
		}

		// Attacking the target
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage";
		target.hurt(damage);

		// If the target is not conscious after the attack
		if (!target.isConscious()) {
			// Execute post attack specific to each actor
			if (target instanceof PostAttack) {
				result += ((PostAttack) target).postAttack(map);
			}

			ActionList dropActions = new ActionList();
			// Drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// Remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed";
		}
		return result;
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor attacking.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " attacks " + target.toString() + " at " + direction;
	}
}
