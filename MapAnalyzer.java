import java.util.ArrayList;

import bc.*;
public class MapAnalyzer {
	public GameController gc;
	public PlanetAnalyzer earth;
	public PlanetAnalyzer mars;
	public OrbitPattern orbit;
	public AsteroidPattern asteroids;
	public ArrayList<MapLocation> ourStart;
	public ArrayList<MapLocation> enemyStart;
	
	public MapAnalyzer(GameController gc){
		this.gc = gc;
		findStartingLocs();
		earth = new PlanetAnalyzer(gc, gc.startingMap(Planet.Earth));
		earth.makeIslands(ourStart.get(0));
		mars = new PlanetAnalyzer(gc, gc.startingMap(Planet.Mars));
		mars.makeIslands((new MapLocation(Planet.Mars, 0, 0)));
		orbit = gc.orbitPattern();
		asteroids = gc.asteroidPattern();
		analyzeEarth();
		analyzeMars();
		analyzeOrbit();
	}
	
	public void findStartingLocs(){
		for(int i = 0; i < earth.p.getInitial_units().size(); i++){
			Unit u = earth.p.getInitial_units().get(i);
			if(u.team() == gc.team()){
				ourStart.add(u.location().mapLocation());
				//inverted from ourStart
				enemyStart.add(new MapLocation(Planet.Earth, Math.abs((int)earth.p.getWidth()-u.location().mapLocation().getX()), Math.abs((int)earth.p.getHeight()-u.location().mapLocation().getY())));
			}
		}

	}
	
	public void analyzeEarth(){		
		//TODO make this
	}
	
	public void analyzeMars(){
		//TODO make this
	}
	
	public void analyzeOrbit(){
		//TODO make this
	}
	
	
}
