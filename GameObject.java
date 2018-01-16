import bc.*;
public class GameObject {
	protected GameController gc;
	public int id;
	
	public GameObject(GameController gc) {
		this.gc = gc;
	}
	public GameObject(GameController gc, int id) {
		this.gc = gc;
		this.id = id;
	}
}
