package gameobjects;
import bc.Direction;
import bc.GameController;
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
			if(!gc.canSenseUnit(targetId)) {
				currentTask = 3;
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

}
