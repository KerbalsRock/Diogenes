package gameobjects;
import java.util.ArrayList;
import bc.*;

public class WorkerManager extends BasicUnitManager {
	public ArrayList<Worker> workerList;
	public boolean factoryNeed;
	public int optimalWorkers = 5;
	public WorkerManager(GameController gc, ArrayList<GameObject> objectList) {
		super(gc, objectList);
		workerList = new ArrayList<Worker>();
		for(GameObject o : objectList){
			workerList.add((Worker)o);
		}
	}
	
	public void update(){
		getFactoryNeed();
		int workersThisTurn = 0;
		for(int i = 0; i < workerList.size(); i++){
			Worker worker = workerList.get(i);
			if(!gc.canSenseUnit(worker.id)){
				workerList.remove(worker);
				i--;
				continue;
			}
			if(gc.unit(worker.id).location().isInGarrison()){
				continue;
			}
			if(factoryNeed && worker.currentTask == 5){
				worker.currentTask = 3;
				factoryNeed = false;
			}
			else if(workerList.size()+workersThisTurn < optimalWorkers){
				if(worker.replicate()){
					workersThisTurn++;
				}
			}
		}
		for(Worker worker : workerList){
			if(gc.unit(worker.id).location().isInGarrison()){
				continue;
			}
			worker.update();
		}
	}
	
	public void add(Worker worker) {
		workerList.add(worker);
	}
	public void remove(Worker worker) {
		workerList.remove(worker);
	}
	
	public int getOptimalWorkers(){
		//TODO make this
		return 0;
	}
	
	public void getFactoryNeed(){
		if(gc.karbonite() >= 200){
			factoryNeed = true;
		}
	}
	
	public ArrayList<Worker> findNearestUnitsTo(MapLocation loc, int numberOfUnits) {
		ArrayList<Worker> list = new ArrayList<Worker>();
		ArrayList<Integer> usedIndexes = new ArrayList<Integer>();
		if(numberOfUnits > workerList.size()) {
			numberOfUnits = workerList.size();
		}
		while(list.size()<numberOfUnits) {
			long closestDistance = 100000;
			int index = -1;
			for(int i = 0; i < workerList.size(); i++) {
				if(usedIndexes.contains(i)) {
					continue;
				}
				long currentDistance =  gc.unit(workerList.get(i).id).location().mapLocation().distanceSquaredTo(loc);
				if(currentDistance < closestDistance){
					closestDistance = currentDistance;
					index = i;
				}
			}
			list.add(workerList.get(index));
			usedIndexes.add(index);
		}
		return list;
	}
}
