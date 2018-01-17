package gameobjects;
import java.util.ArrayList;
import bc.GameController;

public class RangerManager extends AttackUnitManager {
	public ArrayList<Ranger> rangers;
	public RangerManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		rangers = new ArrayList<Ranger>();
		for(GameObject o : unitList) {
			rangers.add((Ranger)o);
		}
	}

}
