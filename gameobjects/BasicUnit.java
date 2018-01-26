package gameobjects;
import bc.*;
import pathfinder.Path;

public class BasicUnit extends GameObject{
	public Path pathToEnemy;
	int pathIndex = 0;
	int[] tryRotate = new int[]{0, 1, -1, 2, -2};
	public BasicUnit(GameController gc) {
		super(gc);
	}
	public BasicUnit(GameController gc, int id) {
		super(gc,id);
	}
	
	public Direction rotate(Direction d, int amount){
		int index = java.util.Arrays.asList(directions).indexOf(d);
		if(index+amount < 0){
			index+=8;
		}
		return directions[(index+amount)%8];
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
	
	public boolean moveRandomly(){
		int rand = (int)Math.random()*8;
		for(int i = 0; i < directions.length; i++){
			if(moveToward(rotate(directions[rand], i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean moveToward(MapLocation loc){
		Direction d = gc.unit(id).location().mapLocation().directionTo(loc);
		if(d.equals(Direction.Center)){
			return false;
		}
		else{
			return moveToward(d);
		}
	}
	
	public boolean followPath() {
		 if(moveToward(pathToEnemy.locList.get(pathIndex))) {
			 if(pathIndex< pathToEnemy.locList.size() -1) {
				 pathIndex++;
			 }
			 return true;
		 }
		 return false;
	}

}