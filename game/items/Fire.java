package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Fire extends WeaponItem {
    private int counter;

    /***
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', 20, "burns", 100);
        this.counter = 0;
    }

    @Override
    public void tick(Location currentLocation) {
        counter += 1;

        // In 10 turns Power Star will fade away and be removed from the game
        if (counter % 3 == 0) {
            currentLocation.removeItem(this);
        }

        if (currentLocation.containsAnActor()) {
            Actor target = currentLocation.getActor();
            target.hurt(this.damage());
            System.out.println("Fire burns " + target.toString() + " at (" + currentLocation.x() + ", " + currentLocation.y() + ")");
        }
    }
}
