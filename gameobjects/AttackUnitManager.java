package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;
import bc.Planet;
import bc.Team;
import bc.UnitType;

public class AttackUnitManager extends BasicUnitManager{
	private ArrayList<AttackUnit> attackUnits;
	protected GameAnalyzer ga;
	 
	public AttackUnitManager(GameController gc, ArrayList<GameObject> unitList, GameAnalyzer ga) {
		super(gc, unitList);
		this.ga = ga;
		attackUnits = new ArrayList<AttackUnit>();
		for(GameObject o : unitList) {
			attackUnits.add((AttackUnit)o);
		}
	}
	
	public void update(){
		for(int i = 0; i < attackUnits.size(); i++){
			AttackUnit u = attackUnits.get(i);
			if(!gc.canSenseUnit(u.id)){
				attackUnits.remove(u);
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
				if(gc.unit(u.id).unitType().equals(UnitType.Ranger)){
					u.currentTask = 3;
				}
				else{
					u.currentTask = 2;
				}
			}
		}
		for(AttackUnit u : attackUnits){
			if(gc.unit(u.id).location().isInGarrison()){
				continue;
			}
			u.update();
		}
	}
	
	public void add(AttackUnit u) {
		attackUnits.add(u);
	}
	public void remove(AttackUnit u) {
		attackUnits.remove(u);
	}
}
