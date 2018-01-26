package gameobjects;
import bc.Direction;
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
		
		public void update(){
			
			//attack manager assigns targets, then updates. check for attacking if able before and after moves.
			if(!gc.canSenseUnit(targetId)){
				getClosestEnemy();
			}
			if(!gc.canSenseUnit(targetId) || gc.unit(targetId).location().isInGarrison()){
				currentTask = 0;
			}
			switch(currentTask){
				case 0:
					if(hasFollowedPath){followPath();}//follow de way
				case 1:
					//hold position, literally nothing goes here
				case 2:
					getClosestEnemy();
					if(!gc.canSenseUnit(targetId)){
						return;
					}
					Direction toEnemy = gc.unit(id).location().mapLocation().directionTo(gc.unit(targetId).location().mapLocation());
					long enemyDistance = gc.unit(targetId).location().mapLocation().distanceSquaredTo(gc.unit(id).location().mapLocation());
					if(enemyDistance > 50) {
						move(toEnemy);
					}if(enemyDistance < 34) {
						move(rotate(toEnemy, 4));
					}
					attack(targetId);
				case 3:
					if(!load(targetId, id)){
						moveToward(gc.unit(id).location().mapLocation());
					}//get in rocket if necessary
			}
			attack(targetId);
		}
}
