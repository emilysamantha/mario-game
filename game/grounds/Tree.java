package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Abstract tree class that Sprout, Sapling, Mature extend
 */
public abstract class Tree extends Ground implements HigherGround {
    private final double jumpSuccessRate;
    private final int fallDamage;
    private String name;
    private int counter;

    /**
     * Constructor.
     *
     */
    public Tree(String name,char displayChar,double jumpSuccessRate,int fallDamage) {
        super(displayChar);
        this.name=name;
        this.jumpSuccessRate=jumpSuccessRate;
        this.fallDamage=fallDamage;
        this.counter=0;
    }

    /**
     *
     * @param actor the that wants to enter
     * @return false so that no one can enter unless they jump
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     *
     * @param actor the Actor performing the actions
     * @param location the current Location of actor
     * @param direction the direction of th location from the Actor
     * @return ActionList of the possible actions that the actor can perform to this ground
     */
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

    /**
     *
     * @return returns the number of turns that the tree has lasted, used to keep track of turn
     */
    public int getCounter(){
        return this.counter;
    }

    /**
     * Adds one to the counter each turn
     */
    public void tickCounter(){
        this.counter+=1;
    }
}