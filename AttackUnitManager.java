import bc.GameController;

public class AttackUnitManager extends UnitManager{
	
	public AttackUnitManager(GameController gc) {
		super(gc);
	}
	public AttackUnitManager(GameController gc, int id) {
		super(gc, id);
	}
	
	public void attack(int targetId) {
		if(gc.canAttack(id, targetId) && gc.isAttackReady(id)) {
			gc.attack(id, targetId);
		}
	}

}
