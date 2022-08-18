package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.PiranhaPlant;
import game.grounds.WarPipeManager;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Action that allows actor to teleport to other GameMap
 */

public class TeleportAction extends Action {

    /**
     * A warp pipe manager to keep track of the maps
     */
    private WarPipeManager warPipeManager = WarPipeManager.getInstance();

    /**
     * The set of game maps available in the game
     */
    private Set<GameMap> gameMaps = warPipeManager.getMapSet();

    /**
     * The location of the actor
     */
    private Location actorLocation;

    /**
     * Constructor
     *
     * @param currentLocation location of the actor
     */
    public TeleportAction(Location currentLocation) {
        this.actorLocation = currentLocation;
    }

    @Override
    public String execute(Actor actor, GameMap actorMap) {
        for (GameMap map : gameMaps) {
            // retrieve map that the actor is not part of and add to new map
            if (!(actorLocation.map() == map)) {
                //get list of locations of all the WarPipes on new location and shuffle them to get random location to teleport to
                List<Location> warPipeLocations = warPipeManager.getWarPipeLocations(map);

                // check if player is on war pipe and remove that from possible locations-->in regards to the Sonic teleporting

                int currentIndex = 0;

                for (Location warPipeLocation : warPipeLocations) {
                    if (warPipeLocation.containsAnActor()) {

                        Actor actorOnWarPipe = map.getActorAt(warPipeLocation);
                        if (!(actorOnWarPipe instanceof PiranhaPlant)) {
                            warPipeLocations.remove(currentIndex);
                        }
                    }

                    currentIndex = currentIndex + 1;
                }

                Collections.shuffle(warPipeLocations);


                // get teleport location
                Location teleportLocation = warPipeLocations.get(0);

                //remove piranha at that location
                if (teleportLocation.containsAnActor()) {
                    Actor piranha = teleportLocation.getActor();
                    teleportLocation.map().removeActor(piranha);
                }


                //add actor to new location
                actorMap.removeActor(actor);
                map.addActor(actor, teleportLocation);

            }
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        GameMap teleportMap = actorLocation.map();

        for (GameMap map : gameMaps) {
            if (!(actorLocation.map() == map)) {
                teleportMap = map;
            }
        }
        String mapName = warPipeManager.getMapName(teleportMap);

        return actor.toString() + " teleports to " + mapName;
    }
}
