// import the API.
// See xxx for the javadocs.
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.*;
import gameobjects.AttackUnitManager;
import gameobjects.Factory;
import gameobjects.FactoryManager;
import gameobjects.GameObject;
import gameobjects.Knight;
import gameobjects.Mage;
import gameobjects.Ranger;
import gameobjects.RangerManager;
import gameobjects.Worker;
import gameobjects.WorkerManager;

public class Player {
    public static void main(String[] args) {
    // Connect to the manager, starting the game
   	GameController gc = new GameController();
   	while(gc.planet().equals(Planet.Mars)){
   		gc.nextTurn();
   	}
	GameAnalyzer analyzer = new GameAnalyzer(gc);
   	WorkerManager workerManager = new WorkerManager(gc, new ArrayList<GameObject>());
   	RangerManager rangerManager = new RangerManager(gc, new ArrayList<GameObject>(), analyzer);
   	FactoryManager factoryManager = new FactoryManager(gc, new ArrayList<GameObject>(), workerManager);
   	System.out.println("path to enemy :"+analyzer.pathToEnemy);
   	double earthScore = analyzer.earthScore;
   	double economyScore = 0;
   	double militaryScore = 0;
   	ArrayList<Integer> processedIds = new ArrayList<Integer>();

    while (true) {
    	if(gc.planet().equals(Planet.Mars)){
    		gc.nextTurn();
    	}
    	System.out.println(gc.getTimeLeftMs());
    	VecUnit myUnits = gc.myUnits();
    	//System.out.println("my units :"+myUnits);
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
    				rangerManager.add(new Ranger(gc, id));
    			}
    		}
    	}
    	factoryManager.update();
    	workerManager.update();
    	rangerManager.update();
        gc.nextTurn();
    }
    
    }
}