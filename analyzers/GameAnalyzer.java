package analyzers;
import java.util.ArrayList;
import java.util.Random;

import bc.*;
import pathfinder.Path;
import pathfinder.PathFinder;

public class GameAnalyzer {
	public GameController gc;
	public PlanetAnalyzer earth;
	public PlanetAnalyzer mars;
	public OrbitPattern orbit;
	public AsteroidPattern asteroids;
	public ArrayList<MapLocation> ourStart;
	public ArrayList<MapLocation> enemyStart;
	public Path pathToEnemy;
	private int startingIslandSize;
	private Integer nodesToEnemy;
	private int startingWorkers;
	private int availableKarb;
	public double earthScore;
	public double gameScore;
	
	
	public GameAnalyzer(GameController gc){
		this.gc = gc;
		earth = new PlanetAnalyzer(gc, gc.startingMap(Planet.Earth));
		findStartingLocs();
		/*if(gc.planet().equals(Planet.Mars)){
			mars = new PlanetAnalyzer(gc, gc.startingMap(Planet.Mars));
			mars.makeIslands((new MapLocation(Planet.Mars, 0, 0)));
		}
		orbit = gc.orbitPattern();
		asteroids = gc.asteroidPattern();
		availableKarb = 0;
		/*if(gc.planet().equals(Planet.Earth)){
			analyzeEarth();
		}
		analyzeMars();
		analyzeOrbit();*/
	}
	
	private void findStartingLocs(){
		ourStart = new ArrayList<MapLocation>();
		enemyStart = new ArrayList<MapLocation>();
		for(int i = 0; i < earth.p.getInitial_units().size(); i++) {
			Unit u = earth.p.getInitial_units().get(i);
			if(u.team().equals(gc.team())) {
				ourStart.add(u.location().mapLocation());
			}else {
				enemyStart.add(u.location().mapLocation());
			}
		}	
	}
	
	private void analyzeEarth(){
		PathFinder p = new PathFinder(earth.island);
		PlanetMap pl = gc.startingMap(Planet.Earth);
		startingIslandSize = earth.island.list.size();
		for(MapLocation mapLoc : earth.island.list) {
			availableKarb += pl.initialKarboniteAt(mapLoc);
		}
		pathToEnemy = p.generatePath(ourStart.get(0), enemyStart.get(0));
		try{
			nodesToEnemy = pathToEnemy.locList.size();
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
