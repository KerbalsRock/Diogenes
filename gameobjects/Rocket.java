package gameobjects;
import bc.GameController;
import bc.MapLocation;
import bc.Planet;
public class Rocket extends Building{

	public Rocket(GameController gc) {
		super(gc);
	}
	
	public Rocket(GameController gc, int id) {
		super(gc, id);
	}
	
	public boolean launch(MapLocation destination) {
		if(gc.canLaunchRocket(id, destination)){
			gc.launchRocket(id, destination);
			return true;
		}
		return false;
	}
	
	public boolean launchRandomly(){
		int height = (int) gc.startingMap(Planet.Mars).getHeight();
		int width = (int) gc.startingMap(Planet.Mars).getWidth();
		int x = (int) (Math.random()*width);
		int y = (int) (Math.random()*height);
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if(launch(new MapLocation(Planet.Mars, (j + x) % width, (i + y) % height))){
					return true;
				}
			}
		}
		return false;
				
	}
}