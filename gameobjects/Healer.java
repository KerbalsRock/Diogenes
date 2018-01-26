package gameobjects;
import bc.Direction;
import bc.GameController;
import bc.MapLocation;
import bc.Unit;
import bc.VecUnit;
public class Healer extends AttackUnit{

	public Healer(GameController gc) {
		super(gc);
	}
	public Healer(GameController gc, int id ) {
		super(gc, id);
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
			followPath();
		case 1:
			findNearestDamaged();
			if(!gc.canSenseUnit(targetId)) {
				return;
			}
			Direction toTarget = gc.unit(targetId).location().mapLocation().directionTo(gc.unit(id).location().mapLocation());
			long targetDistance = gc.unit(targetId).location().mapLocation().distanceSquaredTo(gc.unit(id).location().mapLocation());
			if(targetDistance > 30) {
				move(toTarget);
			}else if(targetDistance < 10) {
				move(rotate(toTarget,4));
			}
			heal(targetId);
		case 2:
			overcharge(targetId);
		}
	}
	private int findNearestDamaged() {
		Unit self = gc.unit(id);
		MapLocation myLocation = self.location().mapLocation();
		VecUnit nearbyAllies = gc.senseNearbyUnitsByTeam(myLocation, self.visionRange(), gc.team());
		int closestDistance = 1000000;
		for(int i = 0; i < nearbyAllies.size(); i++){
			if(nearbyAllies.get(i).health()==nearbyAllies.get(i).maxHealth()) {
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
