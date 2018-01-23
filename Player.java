// import the API.
// See xxx for the javadocs.
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.*;
import gameobjects.AttackUnitManager;
import gameobjects.Factory;
import gameobjects.FactoryManager;
import gameobjects.Knight;
import gameobjects.Mage;
import gameobjects.Ranger;
import gameobjects.Worker;
import gameobjects.WorkerManager;

public class Player {
    public static void main(String[] args) {
    System.out.println("can I edit?");
    
    // Connect to the manager, starting the game
   	GameController gc = new GameController();
   	GameAnalyzer analyzer = new GameAnalyzer(gc);
   	WorkerManager workerManager = new WorkerManager(gc, null);
   	AttackUnitManager attackManager = new AttackUnitManager(gc, null, analyzer);
   	FactoryManager factoryManager = new FactoryManager(gc, null);
   	
   	double earthScore = analyzer.earthScore;
   	double economyScore = 0;
   	double militaryScore = 0;
   	ArrayList<Integer> processedIds = new ArrayList<Integer>();
    while (true) {
	    	VecUnit myUnits = gc.myUnits();
	    	for(int i = 0; i < myUnits.size(); i++){
	    		Unit u = myUnits.get(i);
	    		int id = u.id();
	    		if(!processedIds.contains(id)){
	    			processedIds.add(id);
	    			if(u.unitType().equals(UnitType.Factory)){
	    				factoryManager.add(new Factory(gc, id));
	    			}
	    			else if(u.unitType().equals(UnitType.Worker)){
	    				workerManager.add(new Worker(gc, id));
	    			}
	    			else if(u.unitType().equals(UnitType.Ranger)){
	    				attackManager.add(new Ranger(gc, id));
	    			}
	    			else if(u.unitType().equals(UnitType.Knight)){
	    				attackManager.add(new Knight(gc, id));
	    			}
	    			else if(u.unitType().equals(UnitType.Mage)){
	    				attackManager.add(new Mage(gc, id));
	    			}
	    		}
	    	}
	    	workerManager.update();
	    	attackManager.update();
	    	factoryManager.update();
        gc.nextTurn();
    }
    
    }
}