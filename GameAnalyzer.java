import java.util.ArrayList;

import bc.*;
public class GameAnalyzer {
	public GameController gc;
	public PlanetAnalyzer earth;
	public PlanetAnalyzer mars;
	public OrbitPattern orbit;
	public AsteroidPattern asteroids;
	public ArrayList<MapLocation> ourStart;
	public ArrayList<MapLocation> enemyStart;
	private int startingIslandSize;
	private int nodesToEnemy;
	private int startingWorkers;
	private int availableKarb;
	public double earthScore;
	
	
	public GameAnalyzer(GameController gc){
		this.gc = gc;
		findStartingLocs();
		earth = new PlanetAnalyzer(gc, gc.startingMap(Planet.Earth));
		earth.makeIslands(ourStart.get(0));
		mars = new PlanetAnalyzer(gc, gc.startingMap(Planet.Mars));
		mars.makeIslands((new MapLocation(Planet.Mars, 0, 0)));
		orbit = gc.orbitPattern();
		asteroids = gc.asteroidPattern();
		availableKarb = 0;
		analyzeEarth();
		analyzeMars();
		analyzeOrbit();
	}
	
	private void findStartingLocs(){
		for(int i = 0; i < earth.p.getInitial_units().size(); i++){
			Unit u = earth.p.getInitial_units().get(i);
			if(u.team() == gc.team()){
				ourStart.add(u.location().mapLocation());
				//inverted from ourStart
				enemyStart.add(new MapLocation(Planet.Earth, Math.abs((int)earth.p.getWidth()-u.location().mapLocation().getX()), Math.abs((int)earth.p.getHeight()-u.location().mapLocation().getY())));
			}
		}

	}
	
	private void analyzeEarth(){
		PathFinder p = new PathFinder(earth.islands.get(0));
		PlanetMap pl = new PlanetMap();
		startingIslandSize = earth.islands.get(0).list.size();
		nodesToEnemy = p.generatePath(ourStart.get(0), enemyStart.get(0)).locList.size();
		for(MapLocation mapLoc : earth.islands.get(0).list) {
			availableKarb += pl.initialKarboniteAt(mapLoc);
		}
		
		earthScore = Math.pow(nodesToEnemy, 1)*Math.pow(availableKarb/startingIslandSize, 1)*Math.pow(startingWorkers, 1);
	}
	
	private void analyzeMars(){
		//TODO make this
	}
	
	private void analyzeOrbit(){
		//TODO make this
	}
	
	
}
