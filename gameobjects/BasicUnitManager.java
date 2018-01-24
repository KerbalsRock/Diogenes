package gameobjects;
import java.util.ArrayList;
import bc.GameController;
import bc.MapLocation;

public abstract class BasicUnitManager extends GameObjectManager {
	
	protected ArrayList<BasicUnit> units;
	 
	public BasicUnitManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		units = new ArrayList<BasicUnit>();
		for(GameObject o : unitList) {
			units.add((BasicUnit)o);
		}
	}
	
}
