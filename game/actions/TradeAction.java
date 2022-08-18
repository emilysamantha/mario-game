package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Tradable;

public class TradeAction extends Action {
    /**
     * The Item to be traded
     */
    private Tradable item;

    /**
     * Constructor.
     *
     * @param item the Item name to be traded
     */
    public TradeAction(Tradable item) {
        this.item = item;
    }

    /**
     * Perform the trade action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @exception if the actor executing the action is not a Player or the item to be traded does not implement Tradable
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // If the actor has enough coins to buy the item
        try {
            ((Player) actor).getWallet();
            ((Tradable) item).getPrice();
        } catch (Exception e) {
            System.out.println("Actor is not a Player or item does not implement Tradable");
        } finally {
            if (((Player) actor).getWallet() >= ((Tradable) item).getPrice()) {
                // Add the item to the actor's inventory
                actor.addItemToInventory((Item)item);

                // Set the actor's wallet after trading
                ((Player) actor).setWallet(((Player) actor).getWallet() - ((Tradable) item).getPrice());

                return actor + " traded " + item.toString() + " for $" + ((Tradable) item).getPrice();
            }
            // The actor is not able to perform the trade
            return "You don't have enough coins!";
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.item.toString() + " (" + ((Tradable) item).getPrice() + ")";
    }
}