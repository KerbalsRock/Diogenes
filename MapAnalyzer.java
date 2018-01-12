import java.util.ArrayList;

import bc.*;
public class MapAnalyzer {
	public GameController gc;
	public PlanetMap earth;
	public PlanetMap mars;
	public OrbitPattern orbit;
	public AsteroidPattern asteroids;
	public ArrayList<MapLocation> ourStart;
	public ArrayList<MapLocation> enemyStart;
	public ArrayList<ArrayList<MapLocation>> sectionsEarth;
	public ArrayList<ArrayList<MapLocation>> sectionsMars;
	
	public MapAnalyzer(GameController gc){
		this.gc = gc;
		earth = gc.startingMap(Planet.Earth);
		mars = gc.startingMap(Planet.Mars);
		orbit = gc.orbitPattern();
		asteroids = gc.asteroidPattern();
		analyzeEarth();
		analyzeMars();
		analyzeOrbit();
	}
	
	public void analyzeEarth(){
		//because VecUnit isn't iterable for some reason so for each doesn't work
		for(int i = 0; i < earth.getInitial_units().size(); i++){
			Unit u = earth.getInitial_units().get(i);
			if(u.team() == gc.team()){
				ourStart.add(u.location().mapLocation());
			}
			else{
				enemyStart.add(u.location().mapLocation());
			}
		}
		
		
	}
	
	public void analyzeMars(){
		//TODO make this
	}
	
	public void analyzeOrbit(){
		//TODO make this
	}
	
	public void makeSections(PlanetMap p){
		//TODO make this
		//probably do breadth first search from (0,0) and move all found passable locations from unexplored
		//to section 1, and all found impassable locations to impassable, then iterate through unexplored and
		//add all impassable to impassable and start a new bfs at any new passable location and reapeat
	}
}
