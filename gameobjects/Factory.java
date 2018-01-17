package gameobjects;
import bc.*;
public class Factory extends Building{

	public Factory(GameController gc) {
		super(gc);
	}
	
	public Factory(GameController gc, int id) {
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
