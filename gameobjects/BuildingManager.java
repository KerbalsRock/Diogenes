package gameobjects;
import java.util.ArrayList;
import bc.GameController;

public class BuildingManager extends GameObjectManager{
	private ArrayList<Building> buildings;
	public BuildingManager(GameController gc, ArrayList<GameObject> objectList) {
		super(gc, objectList);
		buildings = new ArrayList<Building>();
		for(GameObject o : objectList) {
			buildings.add((Building)o);
		}
	}

}
