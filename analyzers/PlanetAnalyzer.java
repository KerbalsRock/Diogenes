package analyzers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import bc.*;
public class PlanetAnalyzer {
	public GameController gc;
	public PlanetMap p;
	public ArrayList<Island> islands;
	public ArrayList<MapLocation> impassable;
	
	public PlanetAnalyzer(GameController gc, PlanetMap p){
		this.gc = gc;
		this.p = p;
	}
	
	public ArrayList<MapLocation> getAdjacent(MapLocation loc){
		ArrayList<MapLocation> adjacent = new ArrayList<MapLocation>();
		for(Direction d : Direction.values()){
			if(p.onMap(loc.add(d)) && d != Direction.Center){
				adjacent.add(loc.add(d));
			}
		}
		return adjacent;
	}
	
	//undelete this
	public void makeIslands(MapLocation initial){
		int n = 0;
		//probably do breadth first search from ourStart and move all found passable locations from unexplored
		//to section 1, and all found impassable locations to impassable, then iterate through unexplored and
		//add all impassable to impassable and start a new bfs at any new passable location and repeat
		ArrayList<MapLocation> unexplored = new ArrayList<MapLocation>();
		for(int i = 0; i < p.getWidth(); i++){
			for(int j = 0; j < p.getHeight(); j++){
				unexplored.add(new MapLocation(p.getPlanet(), i, j));
				n++;
			}
		}
		//System.out.println("unexplored :"+unexplored);
		islands = new ArrayList<Island>();
		impassable = new ArrayList<MapLocation>();
		while(!unexplored.isEmpty()){
			ArrayList<MapLocation> open = new ArrayList<MapLocation>();
			ArrayList<MapLocation> closed = new ArrayList<MapLocation>();
			open.add(initial);
			unexplored.remove(initial);
			while(!open.isEmpty()){
				MapLocation loc = open.get(0);
				//System.out.println("opening :"+loc);
				open.remove(loc);
				//System.out.println("open :"+open);
				for(MapLocation newloc : getAdjacent(loc)){
					//System.out.println("newloc :"+newloc);
					boolean contains = false;
					MapLocation otherLoc = null;
					for(int i = 0; i < unexplored.size(); i++){
						if(unexplored.get(i).toString().equals(newloc.toString())){
							contains = true;
							otherLoc = unexplored.get(i);
							//System.out.println("otherloc :"+otherLoc);
						}
						n++;
					}
					if(contains){
						unexplored.remove(otherLoc);
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
			islands.add(new Island(closed));
			while(!unexplored.isEmpty() && p.isPassableTerrainAt(unexplored.get(0)) == 0){
				impassable.add(unexplored.get(0));
				unexplored.remove(0);
				n++;
			}
			if(!unexplored.isEmpty()){
				initial = unexplored.get(0);
				open.clear();
				closed = new ArrayList<MapLocation>();
			}
		}
		System.out.println(n);
	}
	
	public Island getIsland(MapLocation loc){
		for(Island i : islands){
			if(i.list.contains(loc)){
				return i;
			}
		}
		return null;
	}
}
