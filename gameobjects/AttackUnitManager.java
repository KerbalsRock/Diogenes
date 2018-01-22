package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;

public class AttackUnitManager extends BasicUnitManager{
	private ArrayList<AttackUnit> attackUnits;
	private GameAnalyzer ga;
	 
	public AttackUnitManager(GameController gc, ArrayList<GameObject> unitList, GameAnalyzer ga) {
		super(gc, unitList);
		this.ga = ga;
		attackUnits = new ArrayList<AttackUnit>();
		for(GameObject o : unitList) {
			attackUnits.add((AttackUnit)o);
		}
	}
	
	public void update(){
		for(AttackUnit u : attackUnits){
			if(!gc.canSenseUnit(u.id)){
				attackUnits.remove(u);
				continue;
			}
			//assign tasks and targets, default to follow path>if in range of enemy, kite
			if(!u.hasFollowedPath){
				u.pathToEnemy = ga.pathToEnemy;
				u.currentTask = 1;
				u.hasFollowedPath = true;
				continue;
			}
			//if it's following a path and an enemy enters vision, enter the chosen attack stance, kite for now
			if(u.currentTask == 1 && u.getClosestEnemy() != 0){
				u.currentTask = 3;
			}
		}
		for(AttackUnit u : attackUnits){
			u.update();
		}
	}
}
