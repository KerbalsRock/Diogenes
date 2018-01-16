import bc.GameController;
import bc.MapLocation;
public class Mage extends AttackUnit{
	public Mage(GameController gc) {
		super(gc);
	}
	public Mage(GameController gc, int id ) {
		super(gc, id);
	}
	
	public boolean blink(MapLocation location) {
		if(gc.canBlink(id, location) && gc.isBlinkReady(id)){
			gc.blink(id, location);
			return true;
		}
		return false;
	}

}
