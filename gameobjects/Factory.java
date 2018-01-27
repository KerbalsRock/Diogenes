package gameobjects;
import bc.*;
import pathfinder.Path;
import pathfinder.PathFinder;
public class Factory extends Building{
	public Path pathToEnemy;
	public Factory(GameController gc, int id, PathFinder p, MapLocation loc) {
		super(gc, id);
		pathToEnemy = p.generatePath(gc.unit(id).location().mapLocation(), loc);
	}
	
	public Factory(GameController gc, int id, Path p) {
		super(gc, id);
		pathToEnemy = p;
	}
	
	public boolean makeUnit(UnitType type) {
		if(gc.canProduceRobot(id, type)){
			gc.produceRobot(id, type);
			return true;
		}
		return false;
	}
	
}
