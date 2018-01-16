import java.util.ArrayList;
import bc.GameController;

public abstract class BasicUnitManager extends GameObjectManager {
	
	ArrayList<BasicUnit> units;
	 
	public BasicUnitManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		for(GameObject o : unitList) {
			units.add((BasicUnit)o);
		}
	}

}
