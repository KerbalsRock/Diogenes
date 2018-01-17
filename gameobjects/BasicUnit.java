package gameobjects;
import bc.*;

public class BasicUnit extends GameObject{
	public BasicUnit(GameController gc) {
		super(gc);
	}
	public BasicUnit(GameController gc, int id) {
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