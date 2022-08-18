package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.*;

/**
 * A manager that keeps track of the locations of all the WarPipes in a certain Gamemap
 * so that the can be used to teleport from one location to another
 */

public class WarPipeManager {
    /**
     * Hashmaps to map the gamemap to the list of locations
     */
    private Map<GameMap, List<Location>> warpPipeMap;

    /**
     * Hashmaps to map the gameMap to the name of the gameMap
     */
    private Map<GameMap,String> mapNames;

    /**
     * Private constructor since only one instance of class is made to manage all warpipes
     */

    private static WarPipeManager instance;

    private WarPipeManager(){
        warpPipeMap =  new HashMap<GameMap, List<Location>>();
        mapNames =  new HashMap<GameMap,String>();}

    public static WarPipeManager getInstance() {// GetInstance method
        if (instance == null) {
            instance = new WarPipeManager();
        }
        return instance;
    }

    /**
     * Method to add a GameMap to the Hashmap, which will then map the Game to a list of all the Locations with Warpipres and name of the map to the actual
     * GameMap
     * @param mapName name of the GameMap that is to be added
     * @param map GameMap that is to be added to HashMap
     */

   public void addLocations(String mapName, GameMap map) {
       List<Location> warPipeLocations = new ArrayList<>();

       //iterate through all the locations of the GameMap to see if the ground is WarPipe- if yes add to list

       for (int x : map.getXRange()) {
           for (int y : map.getYRange()) {
               Location location=map.at(x,y);

               if(location.getGround()  instanceof WarpPipe)
               {
                   warPipeLocations.add(location);
               }

           }
       }
       // save list of location and name of GameMap in Hashmap
       this.warpPipeMap.put(map,warPipeLocations);
       this.mapNames.put(map, mapName);
   }

    /**
     *
     * @param map the GameMap that you would like to get the list of all the WarPipeLocations of
     * @return List of all the locations of WarPipres
     */
   public List<Location> getWarPipeLocations(GameMap map){
        return warpPipeMap.get(map);
   }

    /**
     *
     * @param map the GameMap of the name you would like to get
     * @return the name of the GameMap
     */
   public String getMapName(GameMap map){
        return mapNames.get(map);
   }

    /**
     *
     * @return list of all the saved GameMaps
     */

   public Set<GameMap> getMapSet(){
        return warpPipeMap.keySet();
   }

}
