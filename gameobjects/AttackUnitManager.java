package gameobjects;
import java.util.ArrayList;
import bc.GameController;

public class AttackUnitManager extends BasicUnitManager{
	private ArrayList<AttackUnit> attackUnits;
	 
	public AttackUnitManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		attackUnits = new ArrayList<AttackUnit>();
		for(GameObject o : unitList) {
			attackUnits.add((AttackUnit)o);
		}
	}

}
