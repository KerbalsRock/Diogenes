package gameobjects;
import bc.GameController;
import bc.MapLocation;
public class Ranger extends AttackUnit{
		public Ranger(GameController gc) {
			super(gc);
		}
		public Ranger(GameController gc, int id ) {
			super(gc, id);
		}

		public boolean snipe(MapLocation location) {
			if(gc.canBeginSnipe(id, location) && gc.isBeginSnipeReady(id)){
				gc.beginSnipe(id, location);
				return true;
			}
			return false;
		}
}
