package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.items.Fire;
import game.items.Key;

/**
 * Bowser class
 */
public class Bowser extends Enemy implements PostAttack {
    public Bowser() {
        super("Bowser", 'B', 20);
        this.behaviours.remove(10); // Remove WanderBehaviour instantiated in Enemy class
    }

    /**
     * Getter for Bowser's intrinsic weapon
     * @return the Bowser's intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    @Override
    public String attack(Actor actor, GameMap map) {
        String result = super.attack(actor, map);

        // Drop a fire to the location
        Location here = map.locationOf(this);
        here.addItem(new Fire());

        return result;
    }

    @Override
    public String postAttack(GameMap map) {
        String result = "";

        Location here = map.locationOf(this);
        here.addItem(new Key());
        result += System.lineSeparator() + this.toString() + " is now unconscious and drops a Key at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y();

        return result;
    }
}
