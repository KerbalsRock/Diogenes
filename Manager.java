import bc.*;
public class Manager {
	protected GameController gc;
	public int id;
	
	public Manager(GameController gc) {
		this.gc = gc;
	}
	public Manager(GameController gc, int id) {
		this.gc = gc;
		this.id = id;
	}
}
