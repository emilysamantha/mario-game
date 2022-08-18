package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.items.Bottle;
import game.items.Coin;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Hostile, CanDrink {

	private final Menu menu = new Menu();
	private final Display display = new Display();
	private int counter = 0;
	private int wallet = 0;
	private final int invincibleTurns = 10;
	private int intrinsicDamage = 5;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Count invincible effect
		if (this.hasCapability(Status.INVINCIBLE)) {
			counter += 1;

			// Print the number of remaining turns for the invincible effect
			display.println(this.name + " consumes Power Star - " + (invincibleTurns - counter) + " turns remaining");
			display.println(this.name + " is INVINCIBLE!");

			// If it has been 10 turns, the invincible effect will fade
			if (counter % invincibleTurns == 0) {
				this.removeCapability(Status.INVINCIBLE);
			}
		}

		// If the Player has a Coin in inventory, convert to wallet
		for (Item item : super.getInventory()) {
			if (item instanceof Coin) {
				this.wallet += ((Coin) item).getValue();
				this.removeItemFromInventory(item);
				break;
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * @return the wallet value of a Player
	 */
	public int getWallet() {
		return this.wallet;
	}

	/**
	 * Set wallet value of Player
	 * @param value new value of wallet
	 */
	public void setWallet(int value) {
		this.wallet = value;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(intrinsicDamage, "punches");
	}

	@Override
	public String increaseIntrinsicDamage(int damage) {
		this.intrinsicDamage += damage;
		return this.toString() + "'s intrinsic weapon damage has been increased by " + damage;
	}

	@Override
	public String attack(Actor target, GameMap map) {
		return "";
	}
}
