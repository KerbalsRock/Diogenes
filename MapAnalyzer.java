import bc.*;
public class MapAnalyzer {
	public GameMap game;
	public PlanetMap earth;
	public PlanetMap mars;
	public OrbitPattern orbit;
	public AsteroidPattern asteroids;
	
	public MapAnalyzer(GameMap game){
		this.game = game;
		earth = game.getEarth_map();
		mars = game.getMars_map();
		orbit = game.getOrbit();
		asteroids = game.getAsteroids();
		analyzeEarth();
		analyzeMars();
		analyzeOrbit();
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
