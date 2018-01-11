import bc.*;

public class UnitManager extends Manager{
	public UnitManager(GameController gc) {
		super(gc);
	}
	public UnitManager(GameController gc, int id) {
		super(gc,id);
	}
	
	public boolean move(Direction d) {
		if(gc.canMove(id, d) && gc.isMoveReady(id)) {
			gc.moveRobot(id, d);
			return true;
		}
		return false;
	}
	public boolean nextPathNode() {
		//TODO make this work
		return false;
	}

}