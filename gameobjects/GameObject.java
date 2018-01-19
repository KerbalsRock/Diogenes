package gameobjects;
import bc.*;
public class GameObject {
	protected GameController gc;
	public int id;
	public int currentTask;
	public int targetId;
	
	public GameObject(GameController gc) {
		this.gc = gc;
	}
	public GameObject(GameController gc, int id) {
		this.gc = gc;
		this.id = id;
	}
}
