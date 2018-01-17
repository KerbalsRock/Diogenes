package gameobjects;
import java.util.ArrayList;
import bc.*;

public abstract class GameObjectManager {
	private ArrayList<GameObject> objects;
	protected GameController gc;
	public GameObjectManager(GameController gc, ArrayList<GameObject> objectList) {
		this.gc = gc;
		objects = objectList;
	}
	public void add(GameObject o) {
		objects.add(o);
	}
	public void remove(GameObject o) {
		objects.remove(o);
	}
	
	public void update() {
		
	}
	
}

