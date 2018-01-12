import java.util.ArrayList;

import bc.*;
public class PlanetAnalyzer {
	public GameController gc;
	public PlanetMap p;
	public ArrayList<ArrayList<MapLocation>> sections;
	
	public PlanetAnalyzer(GameController gc, PlanetMap p){
		this.gc = gc;
		this.p = p;
	}
	
	public void makeSections(MapLocation initial){
		//TODO make this
		//probably do breadth first search from ourStart and move all found passable locations from unexplored
		//to section 1, and all found impassable locations to impassable, then iterate through unexplored and
		//add all impassable to impassable and start a new bfs at any new passable location and reapeat
		ArrayList<MapLocation> unexplored = new ArrayList<MapLocation>();
		for(int i = 0; i < p.getWidth(); i++){
			for(int j = 0; j < p.getHeight(); j++){
				unexplored.add(new MapLocation(p.getPlanet(), i, j));
			}
		}
		while(!unexplored.isEmpty()){
			ArrayList<MapLocation> open = new ArrayList<MapLocation>();
			ArrayList<MapLocation> closed = new ArrayList<MapLocation>();
			open.add(initial);
			while(!open.isEmpty()){
				
			}
		}
	}
}
