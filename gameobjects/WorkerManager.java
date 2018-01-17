package gameobjects;
import java.util.ArrayList;
import bc.*;

public class WorkerManager extends BasicUnitManager {
	public ArrayList<Worker> workerList;
	public WorkerManager(GameController gc, ArrayList<GameObject> objectList) {
		super(gc, objectList);
		workerList = new ArrayList<Worker>();
		for(GameObject o : objectList){
			workerList.add((Worker)o);
		}
	}
	
	public void update(){
		for(Worker worker : workerList){
			//determine optimal number of workers, if less than that, replicate
			//determine optimal number of factories, if less, blueprint at closest available location
			//if need rocket, blueprint at closest available location
			//default posture is gather closest karbonite
			worker.harvestClosest();
		}
	}
}
