package gameobjects;
import bc.*;
import pathfinder.Path;

public class BasicUnit extends GameObject{
	public Path pathToEnemy;
	int pathIndex = 0;
	Direction[] directions = new Direction[]{Direction.East, Direction.Northeast, Direction.North, Direction.Northwest, Direction.West, Direction.Southwest, Direction.South, Direction.Southeast};
	int[] tryRotate = new int[]{0, 1, -1, 2, -2};
	public BasicUnit(GameController gc) {
		super(gc);
	}
	public BasicUnit(GameController gc, int id) {
		super(gc,id);
	}
	
	public Direction rotate(Direction d, int amount){
		return directions[(java.util.Arrays.asList(directions).indexOf(d))%8];
	}
	
	public boolean move(Direction d) {
		if(gc.canMove(id, d) && gc.isMoveReady(id)) {
			gc.moveRobot(id, d);
			return true;
		}
		return false;
	}
	
	public boolean load(int structId, int unitId){
		if(gc.canLoad(structId, unitId)){
			gc.load(structId, unitId);
			return true;
		}
		return false;
	}
	
	public boolean moveToward(Direction d){
		for(int i : tryRotate){
			if(move(rotate(d, i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean moveToward(MapLocation loc){
		return moveToward(gc.unit(id).location().mapLocation().directionTo(loc));
	}
	
	public boolean followPath() {
		return moveToward(pathToEnemy.locList.get(pathIndex));
	}

}