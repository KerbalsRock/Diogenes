import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import bc.*;
public class PlanetAnalyzer {
	public GameController gc;
	public PlanetMap p;
	public ArrayList<ArrayList<MapLocation>> sections;
	public ArrayList<MapLocation> impassable;
	
	public PlanetAnalyzer(GameController gc, PlanetMap p){
		this.gc = gc;
		this.p = p;
	}
	
	public static ArrayList<MapLocation> getAdjacent(MapLocation loc){
		List<MapLocation> list = (List) Arrays.asList(loc.translate(-1, 1), loc.translate(0, 1), loc.translate(1, 1), loc.translate(1, 0), loc.translate(1, -1), loc.translate(0, -1), loc.translate(-1, -1), loc.translate(-1, 0));
		ArrayList<MapLocation> adjacent = new ArrayList<MapLocation>();
		adjacent.addAll(list);
		return adjacent;
	}
	
	public void makeSections(MapLocation initial){
		//probably do breadth first search from ourStart and move all found passable locations from unexplored
		//to section 1, and all found impassable locations to impassable, then iterate through unexplored and
		//add all impassable to impassable and start a new bfs at any new passable location and repeat
		while(p.isPassableTerrainAt(initial) == 0){
			Random r = new Random();
			initial = new MapLocation(p.getPlanet(), r.nextInt((int)p.getWidth()), r.nextInt((int)p.getHeight()));
		}
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
			unexplored.remove(initial);
			while(!open.isEmpty()){
				for(MapLocation loc : open){
					open.remove(loc);
					for(MapLocation newloc : getAdjacent(loc)){
						if(unexplored.contains(newloc)){
							unexplored.remove(newloc);
							if(p.isPassableTerrainAt(newloc) == 1){
								open.add(newloc);
							}
							else{
								impassable.add(newloc);
							}
						}
					}
					closed.add(loc);
				}
			}
			sections.add(closed);
			for(MapLocation loc : unexplored){
				unexplored.remove(loc);
				if(p.isPassableTerrainAt(loc) == 1){
					initial = loc;
					open.clear();
					closed.clear();
					break;
				}
				else{
					impassable.add(loc);
				}
			}
		}
	}
}
