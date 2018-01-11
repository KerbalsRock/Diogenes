import bc.GameController;
public class HealerManager extends AttackUnitManager{

	public HealerManager(GameController gc) {
		super(gc);
	}
	public HealerManager(GameController gc, int id ) {
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
