import bc.GameController;
import bc.MapLocation;
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
}