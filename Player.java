// import the API.
// See xxx for the javadocs.
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.*;
import gameobjects.AttackUnitManager;
import gameobjects.Factory;
import gameobjects.FactoryManager;
import gameobjects.GameObject;
import gameobjects.Healer;
import gameobjects.HealerManager;
import gameobjects.Knight;
import gameobjects.Mage;
import gameobjects.Ranger;
import gameobjects.RangerManager;
import gameobjects.Rocket;
import gameobjects.RocketManager;
import gameobjects.Worker;
import gameobjects.WorkerManager;
import pathfinder.PathFinder;

public class Player {
    public static void main(String[] args) {
    // Connect to the manager, starting the game
   	GameController gc = new GameController();
	GameAnalyzer analyzer = new GameAnalyzer(gc);
	PathFinder pathFinder = new PathFinder(analyzer.earth.island);
   	WorkerManager workerManager = new WorkerManager(gc, new ArrayList<GameObject>());
   	RangerManager rangerManager = new RangerManager(gc, new ArrayList<GameObject>());
   	HealerManager healerManager = new HealerManager(gc, new ArrayList<GameObject>());
   	FactoryManager factoryManager = new FactoryManager(gc, new ArrayList<GameObject>(), workerManager);
   	RocketManager rocketManager = new RocketManager(gc, new ArrayList<GameObject>(), workerManager);
   	//System.out.println("path to enemy :"+analyzer.pathToEnemy);
   	//double earthScore = analyzer.earthScore;
   	//double economyScore = 0;
   	//double militaryScore = 0;
   	ArrayList<Integer> processedIds = new ArrayList<Integer>();
   	gc.queueResearch(UnitType.Healer);
   	gc.queueResearch(UnitType.Ranger);
   	gc.queueResearch(UnitType.Rocket);
   	gc.queueResearch(UnitType.Healer);
   	
    while (true) {
	    	System.out.println(gc.getTimeLeftMs());
	    	VecUnit myUnits = gc.myUnits();
	    	//System.out.println("my units :"+myUnits);
	    	if(gc.planet().equals(Planet.Earth)){
	    		factoryManager.updateKilled();
	    		rocketManager.updateKilled();
	    	}
	    	workerManager.updateKilled();	    
	    	rangerManager.updateKilled();
	    	healerManager.updateKilled();
	    	
	    	for(int i = 0; i < myUnits.size(); i++){
	    		Unit u = myUnits.get(i);
	    		int id = u.id();
	    		if(!processedIds.contains(id) && !u.location().isInGarrison()){
	    			processedIds.add(id);
	    			if(u.unitType().equals(UnitType.Factory)){
	    				factoryManager.add(new Factory(gc, id, pathFinder, analyzer.enemyStart));
	    			}
	    			else if(u.unitType().equals(UnitType.Rocket)){
	    				rocketManager.add(new Rocket(gc, id));
	    			}
	    			else if(u.unitType().equals(UnitType.Worker)){
	    				workerManager.add(new Worker(gc, id));
	    			}
	    			else if(u.unitType().equals(UnitType.Ranger)){
	    				rangerManager.add(new Ranger(gc, id, findFactoryAdjacentTo(u.location().mapLocation(), factoryManager, gc).pathToEnemy));
	    			}
	    			else if(u.unitType().equals(UnitType.Healer)){
	    				healerManager.add(new Healer(gc, id, findFactoryAdjacentTo(u.location().mapLocation(), factoryManager, gc).pathToEnemy));
	    			}
	    		}
	    	}
	    	if(gc.planet().equals(Planet.Earth)){
		    	rocketManager.update();
		    	factoryManager.update();
	    	}
	    	workerManager.update();	    
	    	rangerManager.update();
	    	healerManager.update();
	    	if(gc.planet().equals(Planet.Earth)){
	    		rocketManager.updateLaunches();
	    	}
	        gc.nextTurn();
	    }
    }
    public static Factory findFactoryAdjacentTo(MapLocation loc, FactoryManager fm, GameController gc){
		for(Factory f : fm.factories) {
			if(gc.unit(f.id).location().mapLocation().isAdjacentTo(loc)) {
				return f;
			}
		}
		return null;
	    
    }
}