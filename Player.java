// import the API.
// See xxx for the javadocs.
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.*;

public class Player {
    public static void main(String[] args) {
    
    
    // Connect to the manager, starting the game
   	GameController gc = new GameController();
   	GameAnalyzer analyzer = new GameAnalyzer(gc);
   	
   	Worker worker = new Worker(gc);
   	
   	
   	double earthScore = analyzer.earthScore;
   	double economyScore = 0;
   	double militaryScore = 0;
    while (true) {
    		gc.
    	
        gc.nextTurn();
    }
    
    }
}