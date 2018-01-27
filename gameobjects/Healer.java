package gameobjects;
import bc.Direction;
import bc.GameController;
import bc.MapLocation;
import bc.Unit;
import bc.VecUnit;
import pathfinder.Path;
public class Healer extends AttackUnit{

	public Healer(GameController gc) {
		super(gc);
		currentTask = 0;
	}
	public Healer(GameController gc, int id ) {
		super(gc, id);
		currentTask = 0;
	}
	
	public Healer(GameController gc, int id, Path p) {
		super(gc, id, p);
		currentTask = 0;
	}
	
	public boolean heal(int targetId) {
		if(gc.canHeal(id, targetId) && gc.isHealReady(id)){
			gc.heal(id, targetId);
			return true;
		}
		return false;
	}
	
	public boolean overcharge(int targetId) {
		if(gc.canOvercharge(id, targetId) && gc.isOverchargeReady(id)){
			gc.overcharge(id, targetId);
			return true;
		}
		return false;
	}
	
	public void update() {
		switch(currentTask) {
		case 0:
			findNearestDamaged();
			if(gc.canSenseUnit(targetId)) {
				currentTask = 1;
				return;
			}
			followPath();
		case 1:
			findNearestDamaged();
			if(!gc.canSenseUnit(targetId)) {
				currentTask = 0;
				return;
			}
			Direction toTarget = gc.unit(id).location().mapLocation().directionTo(gc.unit(targetId).location().mapLocation());
			long targetDistance = gc.unit(id).location().mapLocation().distanceSquaredTo(gc.unit(targetId).location().mapLocation());
			if(targetDistance > 30) {
				moveToward(toTarget);
			}else if(targetDistance < 10) {
				moveToward(rotate(toTarget,4));
			}
			heal(targetId);
			followPath();
		case 2:
			overcharge(targetId);
		}
	}
	private int findNearestDamaged() {
		targetId = -1;
		Unit self = gc.unit(id);
		MapLocation myLocation = self.location().mapLocation();
		VecUnit nearbyAllies = gc.senseNearbyUnitsByTeam(myLocation, self.visionRange(), gc.team());
		int closestDistance = 1000000;
		for(int i = 0; i < nearbyAllies.size(); i++){
			if(nearbyAllies.get(i).health()>=nearbyAllies.get(i).maxHealth()) {
				continue;
			}
			int allyDistance = (int) nearbyAllies.get(i).location().mapLocation().distanceSquaredTo(myLocation);
			if(allyDistance < closestDistance){
				closestDistance = allyDistance;
				targetId = nearbyAllies.get(i).id();
			}
		}
		return targetId;
	}
}
