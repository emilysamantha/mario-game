package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.items.HealthFountain;
import game.items.PowerFountain;
import game.items.SuperMushroom;

/**
 * The main class for the Mario World game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new Lava(), new WarpPipe());

        List<String> map = Arrays.asList(
                "C.........................................##..........+.........................",
                ".....C......+............+..................#...........................C.......",
                "..............................t.............#...................................",
                "..........C..................................##......................T..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................T...................C...........#..............................",
                ".............................................C...##.............................",
                "................................................##..............................",
                ".........+..............................+#____####........C........+............",
                "....................................C..+#_____###++.............................",
                ".......................................+#______###.........................t....",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                ".............................................C.....#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "..........C.........................t.................#.........................",
                ".......................................................##.......................");

        List<String> lavaZone = Arrays.asList(
                "C.........................................##..........+.........................",
                "............+............+..................#...................................",
                "..............................t.............#...................................",
                ".............................................##......................T..........",
                "...............................................#................................",
                "............................................L...#...............................",
                ".................T...............................#..............................",
                "......................................L..........##.............................",
                "................................................##..............................",
                ".........+......................L.......+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###.........................t....",
                "....................................L...+#_____###..............................",
                "........................+........................##.............+...............",
                "........................................L..........#............................",
                "....................................................#...........................",
                "...................+........................L........#..........................",
                "....................................t.................#.........................",
                ".......................................................##.......................");

        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap lavaMap = new GameMap(groundFactory, lavaZone);
        world.addGameMap(gameMap);
        world.addGameMap(lavaMap);

        // Create Princess Peach and place in Lava Zone
        Actor princessPeach = new PrincessPeach();
        world.addPlayer(princessPeach, lavaMap.at(42, 7));

        // Create bowser and place next to Princess Peach
        Actor bowser = new Bowser();
        world.addPlayer(bowser, lavaMap.at(41, 7));


        // Create toad and place him next to player and surround by walls
        Actor toad = new Toad();
        world.addPlayer(toad, gameMap.at(42, 11));

        // Create sonics in both game maps
        Actor sonic = new Sonic();
        world.addPlayer(sonic, lavaMap.at(5, 1));

        Actor sonic2 = new Sonic();
        world.addPlayer(sonic2, lavaMap.at(5, 2));

        Actor sonic3 = new Sonic();
        world.addPlayer(sonic3, gameMap.at(5, 1));

        Actor sonic4 = new Sonic();
        world.addPlayer(sonic4, gameMap.at(5, 2));

        //add super mushrooms on the ground
        SuperMushroom superMushroom1=new SuperMushroom();
        gameMap.at(5,2).addItem(superMushroom1);

        SuperMushroom superMushroom2=new SuperMushroom();
        gameMap.at(4,9).addItem(superMushroom2);

        SuperMushroom superMushroom3=new SuperMushroom();
        gameMap.at(5,2).addItem(superMushroom3);

        SuperMushroom superMushroom=new SuperMushroom();
        gameMap.at(4,9).addItem(superMushroom3);


        // Create Mario
        Actor mario = new Player("Mario", 'm', 100);
        world.addPlayer(mario, gameMap.at(42, 10));

        // Create Health Fountain and Power Fountain and place them near
        gameMap.at(42, 14).addItem(new HealthFountain());
        gameMap.at(41, 14).addItem(new PowerFountain());

        WarPipeManager warPipeManager = WarPipeManager.getInstance();
        warPipeManager.addLocations("Game Map", gameMap);
        warPipeManager.addLocations("Lava Map", lavaMap);

        world.run();
    }
}