import java.util.ArrayList;
import bc.GameController;

public abstract class BasicUnitManager extends GameObjectManager {
	
	ArrayList<GameObject> units;
	 
	public BasicUnitManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		units = unitList;
	}

}
