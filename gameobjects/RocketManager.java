package gameobjects;
import java.util.ArrayList;
import bc.GameController;
import bc.Planet;
import bc.VecUnit;

public class RocketManager extends BuildingManager{
	public ArrayList<Rocket> rockets;
	private WorkerManager workerManager;
	public RocketManager(GameController gc, ArrayList<GameObject> objectList, WorkerManager workerManager) {
		super(gc, objectList);
		this.workerManager = workerManager;
		rockets = new ArrayList<Rocket>();
		for(GameObject o : objectList) {
			rockets.add((Rocket)o);
		}
	}
	
	public void updateKilled() {
		for(int i = 0; i < rockets.size(); i++){
			Rocket r = rockets.get(i);
			if(!gc.canSenseUnit(r.id)){
				rockets.remove(r);
				i--;
			}
		}
	}
	
	public void update() {
		for(int i = 0; i < rockets.size(); i++){
			Rocket r = rockets.get(i);
			if(!gc.canSenseUnit(r.id)){
				rockets.remove(r);
				i--;
				continue;
			}
			if(!gc.unit(r.id).location().isOnPlanet(Planet.Earth)){
				continue;
			}
			if(gc.unit(r.id).health() < gc.unit(r.id).maxHealth()) {
				ArrayList<Worker> assignedWorkers = workerManager.findNearestUnitsTo(gc.unit(r.id).location().mapLocation(), 4);
				for(Worker w : assignedWorkers) {
					w.targetId = r.id;
					w.currentTask = 2;
				}
			}
			VecUnit adjacentUnits = gc.senseNearbyUnitsByTeam(gc.unit(r.id).location().mapLocation(), 2, gc.team());
			for(int j = 0; j < adjacentUnits.size(); j++){
				r.load(adjacentUnits.get(i).id());
			}
		}
	}

	public void updateLaunches(){
		for(int i = 0; i < rockets.size(); i++){
			Rocket r = rockets.get(i);
			if(!gc.unit(r.id).location().isOnPlanet(Planet.Earth)){
				continue;
			}
			if(gc.unit(r.id).structureGarrison().size() == gc.unit(r.id).structureMaxCapacity()  || gc.round() > 710){
				r.launchRandomly();
			}
		}
	}
	
	public void add(Rocket f) {
		rockets.add(f);
	}
	public void remove(Rocket f) {
		rockets.remove(f);
	}

}
