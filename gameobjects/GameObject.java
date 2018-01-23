package gameobjects;
import java.util.ArrayList;

import bc.*;
public class GameObject {
	protected GameController gc;
	public int id;
	public int currentTask;
	public int targetId;
	Direction[] directions = new Direction[]{Direction.East, Direction.Northeast, Direction.North, Direction.Northwest, Direction.West, Direction.Southwest, Direction.South, Direction.Southeast};
	
	public GameObject(GameController gc) {
		this.gc = gc;
	}
	public GameObject(GameController gc, int id) {
		this.gc = gc;
		this.id = id;
	}
	public Team enemyTeam(){
		if(gc.team() == Team.Blue){
			return Team.Red;
		}
		return Team.Blue;
	}
	
	public ArrayList<MapLocation> getAdjacent(MapLocation loc){
		ArrayList<MapLocation> adjacent = new ArrayList<MapLocation>();
		for(Direction d : Direction.values()){
			if(gc.startingMap(gc.planet()).onMap(loc.add(d)) && d != Direction.Center){
				adjacent.add(loc.add(d));
			}
		}
		return adjacent;
	}
}
