package analyzers;
import java.util.ArrayList;

import bc.*;
import pathfinder.PathFinder;

public class GameAnalyzer {
	public GameController gc;
	public PlanetAnalyzer earth;
	public PlanetAnalyzer mars;
	public OrbitPattern orbit;
	public AsteroidPattern asteroids;
	public ArrayList<MapLocation> ourStart;
	public ArrayList<MapLocation> enemyStart;
	private int startingIslandSize;
	private Integer nodesToEnemy;
	private int startingWorkers;
	private int availableKarb;
	public double earthScore;
	public double gameScore;
	
	
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
		for(MapLocation mapLoc : earth.islands.get(0).list) {
			availableKarb += pl.initialKarboniteAt(mapLoc);
		}
		try{
			nodesToEnemy = p.generatePath(ourStart.get(0), enemyStart.get(0)).locList.size();
			earthScore = Math.sqrt(Math.pow(nodesToEnemy, 1)*Math.pow(availableKarb/startingIslandSize, 1)*Math.pow(startingWorkers, 1));
		}
		catch(Exception e){
			nodesToEnemy = null;
			earthScore = Math.sqrt(Math.pow(availableKarb/startingIslandSize, 1)*Math.pow(startingWorkers, 1));
		}
	}
	
	public void update(){
		gameScore = Math.sqrt(Math.pow(gc.round(), 1)*Math.pow(gc.karbonite(), 1));
		//TODO finish this
	}
	
	private void analyzeMars(){
		//TODO make this
	}
	
	private void analyzeOrbit(){
		//TODO make this
	}
	
	
}
