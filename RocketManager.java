import java.util.ArrayList;
import bc.GameController;

public class RocketManager extends BuildingManager{
	public ArrayList<Rocket> rockets;
	public RocketManager(GameController gc, ArrayList<GameObject> objectList) {
		super(gc, objectList);
		rockets = new ArrayList<Rocket>();
		for(GameObject o : objectList) {
			rockets.add((Rocket)o);
		}
	}

}
