package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Wall extends Ground implements HigherGround {
    private final double jumpSuccessRate = 0.8;
    private final int fallDamage = 20;
    private final String name = "Wall";

    public Wall() {
        super('#');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        // get actionList, and JumpActorAction if possible at given location
        ActionList actions = new ActionList();
        return this.getHighGroundAction(actor, location, direction, jumpSuccessRate, fallDamage, actions);
    }

    @Override
    public String toString() {
        return name;
    }
}
