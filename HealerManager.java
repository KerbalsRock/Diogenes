import java.util.ArrayList;
import bc.GameController;

public class HealerManager extends AttackUnitManager{
	public ArrayList<Healer> healers;
	public HealerManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		healers = new ArrayList<Healer>();
		for(GameObject o : unitList) {
			healers.add((Healer)o);
		}
	}
}
