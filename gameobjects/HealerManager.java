package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;
import bc.Planet;

public class HealerManager extends AttackUnitManager{
	public ArrayList<Healer> healers;
	public HealerManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		healers = new ArrayList<Healer>();
		for(GameObject o : unitList) {
			healers.add((Healer)o);
		}
	}
	public void updateKilled(){
		for(int i = 0; i < healers.size(); i++){
			Healer u = healers.get(i);
			if(!gc.canSenseUnit(u.id)){
				healers.remove(u);
				i--;
			}
		}
	}
	public void update() {
		for(int i = 0; i < healers.size(); i++){
			Healer u = healers.get(i);
			if(!gc.canSenseUnit(u.id)){
				healers.remove(u);
				i--;
				continue;
			}
			if(gc.unit(u.id).location().isInGarrison()){
				continue;
			}
			if(!u.hasFollowedPath && gc.planet().equals(Planet.Earth) && u.pathToEnemy!=null){
				u.currentTask = 0;
				u.hasFollowedPath = true;
				continue;
			}
			//if it's following a path and an enemy enters vision, enter the chosen attack stance, kite for now
		}
		for(Healer u : healers){
			if(gc.unit(u.id).location().isInGarrison()){
				continue;
			}
			u.update();
		}
	}

	public void add(Healer u) {
		healers.add(u);
	}
	public void remove(Healer u) {
		healers.remove(u);
	}
}
