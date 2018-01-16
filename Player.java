// import the API.
// See xxx for the javadocs.
import java.util.ArrayList;
import bc.*;

public class Player {
    public static void main(String[] args) {
    ArrayList<MapLocation> list1 = new ArrayList<MapLocation>();
    list1.add(new MapLocation(Planet.Earth, 0, 0));
    list1.add(new MapLocation(Planet.Earth, 1, 0));
    list1.add(new MapLocation(Planet.Earth, 2, 0));
    list1.add(new MapLocation(Planet.Earth, 3, 0));
    list1.add(new MapLocation(Planet.Earth, 4, 0));

    list1.add(new MapLocation(Planet.Earth, 0, 1));
    //list1.add(new MapLocation(Planet.Earth, 1, 1));
    //list1.add(new MapLocation(Planet.Earth, 2, 1));
    //list1.add(new MapLocation(Planet.Earth, 3, 1));
    list1.add(new MapLocation(Planet.Earth, 4, 1));
    
    list1.add(new MapLocation(Planet.Earth, 0, 2));
    //list1.add(new MapLocation(Planet.Earth, 1, 2));
    //list1.add(new MapLocation(Planet.Earth, 2, 2));
    //list1.add(new MapLocation(Planet.Earth, 3, 2));
    list1.add(new MapLocation(Planet.Earth, 4, 2));
    
    list1.add(new MapLocation(Planet.Earth, 0, 3));
    //list1.add(new MapLocation(Planet.Earth, 1, 3));
    //list1.add(new MapLocation(Planet.Earth, 2, 3));
    //list1.add(new MapLocation(Planet.Earth, 3, 3));
    list1.add(new MapLocation(Planet.Earth, 4, 3));
    
    list1.add(new MapLocation(Planet.Earth, 0, 4));
    list1.add(new MapLocation(Planet.Earth, 1, 4));
    list1.add(new MapLocation(Planet.Earth, 2, 4));
    list1.add(new MapLocation(Planet.Earth, 3, 4));
    list1.add(new MapLocation(Planet.Earth, 4, 4));
    
    Island island1 = new Island(list1);
    Test tester = new Test();
    
    
    // Connect to the manager, starting the game
   	GameController gc = new GameController();
   	tester.testPathfinder(island1, list1.get((int)Math.random()*5), list1.get(list1.size()-1-(int)(Math.random()*5)));
    while (true) {
 
        // Submit the actions we've done, and wait for our next turn.
        gc.nextTurn();
    }
    
    }
}