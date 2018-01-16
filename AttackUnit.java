import bc.GameController;

public class AttackUnit extends BasicUnit{
	
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

}
