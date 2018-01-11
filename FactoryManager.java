import bc.*;
public class FactoryManager extends BuildingManager{

	public FactoryManager(GameController gc) {
		super(gc);
	}
	
	public FactoryManager(GameController gc, int id) {
		super(gc, id);
	}
	
	public boolean makeUnit() {
		//TODO make this work
		return false;
	}
	
}
