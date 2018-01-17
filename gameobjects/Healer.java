package gameobjects;
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

}
