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
			//target id is closest enemy, at least for now.
			if(!gc.canSenseUnit(targetId)){
				getClosestEnemy();
			}
			//attack manager assigns targets, then updates. check for attacking if able before and after moves.
			attack(targetId);
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
					if(!gc.canSenseUnit(targetId)){
						return;
					}
					moveToward(gc.unit(targetId).location().mapLocation());//charge enemy
				case 3:
					if(!gc.canSenseUnit(targetId)){
						return;
					}
					Direction toEnemy = gc.unit(targetId).location().mapLocation().directionTo(gc.unit(id).location().mapLocation());//kite
					kite();
				case 4:
					if(!load(targetId, id)){
						moveToward(gc.unit(id).location().mapLocation());
					}//get in rocket if necessary
			}
			attack(targetId);
		}
		
		public void kite() {
			Direction toEnemy = gc.unit(targetId).location().mapLocation().directionTo(gc.unit(id).location().mapLocation());//kite
			if(gc.unit(id).attackHeat() > 10){
				moveToward(rotate(toEnemy, 4));
			}
			else{
				moveToward(toEnemy);
			}
		}
}
