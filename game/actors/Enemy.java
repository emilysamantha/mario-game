package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;
import game.actions.KillAction;
import game.behaviours.*;

import java.util.Map;
import java.util.TreeMap;
import static java.lang.Character.toLowerCase;

/**
 * Abstract class for enemy actors
 */
public abstract class Enemy extends Actor implements Hostile, CanDrink {
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour
    protected Actor target;
    protected int intrinsicDamage;

    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(3, new DrinkBehaviour());
        this.behaviours.put(10, new WanderBehaviour());
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        // If the actor has consumed a Power Star, they will be able to kill the enemy
        if(otherActor.hasCapability(Status.INVINCIBLE)) {
            actions.add(new KillAction(this, direction));
        }
        return actions;
    }

    /**
     * Adds a Behaviour to the Enemy
     * @param key the number to indicate the priority of the Behaviour
     * @param behaviour the Behaviour to add
     */
    public void addBehaviour(int key, Behaviour behaviour) {
        this.behaviours.put(key, behaviour);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Actor's current location
        Location here = map.at(map.locationOf(this).x(), map.locationOf(this).y());

        // If there is a player in the actor's surroundings, add a FollowBehaviour
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                this.behaviours.put(2, new FollowBehaviour(destination.getActor()));
            }
        }

        // Return random action from behaviours
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public String attack(Actor target, GameMap map) {
        String result = "";

        // If target has consumed a Power Star, the attack will have 0 damage
        if (target.hasCapability(Status.INVINCIBLE)) {
            result += System.lineSeparator() + this.toString() + " " + this.getWeapon().verb() + " " + target + " for 0 damage";
        }

        // If the actor has consumed a Super Mushroom, the attack will wear off its effects
        if (target.hasCapability(Status.SUPER_MUSHROOM)) {
            target.removeCapability(Status.SUPER_MUSHROOM);
            target.setDisplayChar(toLowerCase(target.getDisplayChar()));
        }

        return result;
    }

    @Override
    public String increaseIntrinsicDamage(int damage) {
        this.intrinsicDamage += damage;
        return System.lineSeparator() + this.toString() + "'s intrinsic weapon damage has been increased by " + damage;
    }
}
