import bc.GameController;
import bc.MapLocation;
public class RocketManager extends BuildingManager{

	public RocketManager(GameController gc) {
		super(gc);
	}
	
	public RocketManager(GameController gc, int id) {
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