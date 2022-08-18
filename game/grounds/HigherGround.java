package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.actions.DestroyHighGroundAction;
import game.actions.JumpActorAction;

public interface HigherGround {

    default ActionList getHighGroundAction(Actor actor, Location location, String direction, double jumpSuccessRate, int fallDamage, ActionList actions) {

        // if actor is not located on tree, isn't invincible and isn't an enemy then return JumpActorAction
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && !actor.hasCapability(Status.INVINCIBLE) && direction != "") {

            // if player has consumed SuperMushroom update jumpSuccessRate to 100%
            if (actor.hasCapability(Status.SUPER_MUSHROOM) || actor.hasCapability(Status.CAN_FLY)) {
                jumpSuccessRate = 1;
            }

            actions.add(new JumpActorAction(location, direction, jumpSuccessRate, fallDamage));
        } else if (actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.INVINCIBLE) && direction != "") {
            actions.add(new DestroyHighGroundAction(location, actor, direction));
        }
        return actions;
    }
}
