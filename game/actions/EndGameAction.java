package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action for ending the game with a victory message
 */
public class EndGameAction extends Action {

    /**
     * The message that will be displayed before the game is over
     */
    private String message;

    public EndGameAction(String message) {
        this.message = message;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return this.message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "End the game";
    }
}
