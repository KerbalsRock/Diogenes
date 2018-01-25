package gameobjects;
import java.util.ArrayList;
import analyzers.GameAnalyzer;
import bc.*;

public class RangerManager extends AttackUnitManager {
	public ArrayList<Ranger> rangers;
	public RangerManager(GameController gc, ArrayList<GameObject> unitList, GameAnalyzer ga) {
		super(gc, unitList, ga);
		rangers = new ArrayList<Ranger>();
		for(GameObject o : unitList) {
			rangers.add((Ranger)o);
		}
	}
	
	public void update() {
		for(int i = 0; i < rangers.size(); i++){
			Ranger u = rangers.get(i);
			if(!gc.canSenseUnit(u.id)){
				rangers.remove(u);
				i--;
				continue;
			}
			if(gc.unit(u.id).location().isInGarrison()){
				continue;
			}
			//assign tasks and targets, default to follow path>if in range of enemy, kite
			if(!u.hasFollowedPath && gc.planet().equals(Planet.Earth)){
				u.pathToEnemy = ga.pathToEnemy;
				u.currentTask = 0;
				u.hasFollowedPath = true;
				continue;
			}
			//if it's following a path and an enemy enters vision, enter the chosen attack stance, kite for now
			if(u.currentTask == 0 && u.getClosestEnemy() != 0){
				u.currentTask = 2;
			}
		}
		for(Ranger u : rangers){
			if(gc.unit(u.id).location().isInGarrison()){
				continue;
			}
			u.update();
		}
	}

	public void add(Ranger u) {
		rangers.add(u);
	}
	public void remove(Ranger u) {
		rangers.remove(u);
	}


}
