package gameobjects;
import java.util.ArrayList;

import bc.*;
import pathfinder.Path;
import pathfinder.PathFinder;
public class Factory extends Building{
	public Path pathToEnemy;
	public Factory(GameController gc, int id, PathFinder p, ArrayList<MapLocation> locs) {
		super(gc, id);
		for(int i = 0; i < locs.size(); i++) {
			Path newPathToEnemy = p.generatePath(gc.unit(id).location().mapLocation(), locs.get(i));
			if(newPathToEnemy != null && (pathToEnemy == null || newPathToEnemy.locList.size() < pathToEnemy.locList.size())) {
				pathToEnemy = newPathToEnemy;
			}
		}
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
