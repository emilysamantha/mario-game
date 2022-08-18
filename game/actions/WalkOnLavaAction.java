package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Special action to walk on Lava
 */
public class WalkOnLavaAction extends Action {
    /**
     * Location to move to that contains lava
     */
    private final Location moveToLocation;

    /**
     * Direction that actor will have to move in
     */
    private final String direction;

    /**
     * Actor that is moving
     */
    private final Actor actor;

    /**
     * Number of hitpoints that the actor will be infilcted onces walked on
     */
    private final int hpDamage;

    /**
     * Constructor
     * @param actor actor that will walk on lava
     * @param moveToLocation location that the actor will move to
     * @param direction direction that the actor will move in
     */
    public WalkOnLavaAction(Actor actor, Location moveToLocation, String direction) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.actor = actor;
        this.hpDamage = 15;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //inflict damage
        actor.hurt(hpDamage);

        //move actor
        map.moveActor(actor, moveToLocation);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " jumps " + this.direction + " onto lava and loses 15 HP, oucchhie";
    }
}
