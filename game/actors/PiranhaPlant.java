package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * Piranha Plant class
 */
public class PiranhaPlant extends Enemy {
    /**
     * Constructor
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 5);
        this.intrinsicDamage = 90;
        this.behaviours.remove(10); // Remove WanderBehaviour instantiated in Enemy class
    }


    /**
     * Getter for Bowser's intrinsic weapon
     * @return the Bowser's intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(intrinsicDamage, "chomps");
    }
}
