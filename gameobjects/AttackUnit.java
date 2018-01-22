package gameobjects;
import bc.Direction;
import bc.GameController;
import bc.MapLocation;
import bc.Unit;
import bc.VecUnit;

public class AttackUnit extends BasicUnit{
	public boolean hasFollowedPath = false;
	
	public AttackUnit(GameController gc) {
		super(gc);
	}
	public AttackUnit(GameController gc, int id) {
		super(gc, id);
	}
	
	public boolean attack(int targetId) {
		if(gc.canAttack(id, targetId) && gc.isAttackReady(id)) {
			gc.attack(id, targetId);
			return true;
		}
		return false;
	}
	
	public void update(){
		//target id is closest enemy, at least for now.
		//attack manager assigns targets, then updates. check for attacking if able before and after moves.
		attack(targetId);
		switch(currentTask){
			case 0: followPath();//follow de way
			case 1: //hold position, literally nothing goes here
			case 2: moveToward(gc.unit(targetId).location().mapLocation());//charge enemy
			case 3: Direction toEnemy = gc.unit(targetId).location().mapLocation().directionTo(gc.unit(id).location().mapLocation());//kite
					if(gc.unit(id).attackHeat() > 10){
						moveToward(rotate(toEnemy, 4));
					}
					else{
						moveToward(toEnemy);
					}
			case 4: if(!load(targetId, id)){moveToward(gc.unit(id).location().mapLocation());}//get in rocket if necessary
		}
		attack(targetId);
	}
	
	public int getClosestEnemy(){
		Unit self = gc.unit(id);
		MapLocation myLocation = self.location().mapLocation();
		VecUnit nearbyEnemies = gc.senseNearbyUnitsByTeam(myLocation, self.visionRange(), enemyTeam());
		int closestDistance = 1000000;
		for(int i = 0; i < nearbyEnemies.size(); i++){
			int enemyDistance = (int) nearbyEnemies.get(i).location().mapLocation().distanceSquaredTo(myLocation);
			if(enemyDistance < closestDistance){
				closestDistance = enemyDistance;
				targetId = nearbyEnemies.get(i).id();
			}
		}
		return targetId;
	}

}
