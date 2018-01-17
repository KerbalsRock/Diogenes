import java.util.ArrayList;
import bc.GameController;

public class FactoryManager extends BuildingManager{
	public ArrayList<Factory> factories;
	public FactoryManager(GameController gc, ArrayList<GameObject> objectList) {
		super(gc, objectList);
		factories = new ArrayList<Factory>();
		for(GameObject o : objectList) {
			factories.add((Factory)o);
		}
	}

}
