package game.actors;

import game.Status;

/**
 * Flying Koopa class
 */
public class FlyingKoopa extends Koopa {
    public FlyingKoopa() {
        super();
        this.resetMaxHp(150);
        this.addCapability(Status.CAN_FLY);
    }
}
