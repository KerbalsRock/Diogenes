import bc.GameController;
import bc.MapLocation;
public class RangerManager extends AttackUnitManager{
		public RangerManager(GameController gc) {
			super(gc);
		}
		public RangerManager(GameController gc, int id ) {
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
