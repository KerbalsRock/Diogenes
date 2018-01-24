package gameobjects;
import java.util.ArrayList;

import bc.Direction;
import bc.GameController;
import bc.Team;
import bc.UnitType;

public class FactoryManager extends BuildingManager{
	public ArrayList<Factory> factories;
	private WorkerManager workerManager;
	public FactoryManager(GameController gc, ArrayList<GameObject> objectList, WorkerManager workerManager) {
		super(gc, objectList);
		this.workerManager = workerManager;
		factories = new ArrayList<Factory>();
		for(GameObject o : objectList) {
			factories.add((Factory)o);
		}
	}
	
	
	public void update() {
		for(int i = 0; i < factories.size(); i++){
			Factory f = factories.get(i);
			if(!gc.canSenseUnit(f.id)){
				factories.remove(f);
				i--;
				continue;
			}
			if(gc.unit(f.id).health() < gc.unit(f.id).maxHealth()) {
				ArrayList<Worker> assignedWorkers = workerManager.findNearestUnitsTo(gc.unit(f.id).location().mapLocation(), 4);
				for(Worker w : assignedWorkers) {
					w.targetId = f.id;
					w.currentTask = 2;
				}
			}
			f.makeUnit(UnitType.Ranger);
			f.unload();
		}
	}
	
	public void add(Factory f) {
		factories.add(f);
	}
	public void remove(Factory f) {
		factories.remove(f);
	}

}
