import bc.*;
public class FactoryManager extends BuildingManager{

	public FactoryManager(GameController gc) {
		super(gc);
	}
	
	public FactoryManager(GameController gc, int id) {
		super(gc, id);
	}
	
	public boolean makeUnit(UnitType type) {
		if(gc.canProduceRobot(id, type)){
			gc.produceRobot(id, type);
			return true;
		}
		return false;
	}
	
}
