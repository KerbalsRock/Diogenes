package gameobjects;
import bc.*;
import pathfinder.Path;

public class BasicUnit extends GameObject{
	Path pathToEnemy;
	int pathIndex = 0;
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
	public MapLocation nextPathNode() {
		pathIndex++;
		return pathToEnemy.locList.get(pathIndex-1);
	}

}