import bc.*;

public class UnitManager{
	protected GameController gc;
	public int id;
	
	public UnitManager(GameController gc) {
		this.gc = gc;
	}
	public UnitManager(GameController gc, int id) {
		this.gc = gc;
		this.id = id;
	}
	
	public void attack(int targetId) {
		if(gc.canAttack(id, targetId) && gc.isAttackReady(id)) {
			gc.attack(id, targetId);
		}
	}

}