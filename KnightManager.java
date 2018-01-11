import bc.GameController;
public class KnightManager extends AttackUnitManager{
	//work
	public KnightManager(GameController gc) {
		super(gc);
	}
	public KnightManager(GameController gc, int id ) {
		super(gc, id);
	}
	
	public boolean javelin(int targetId) {
		if(gc.canJavelin(id, targetId) && gc.isJavelinReady(id)){
			gc.javelin(id, targetId);
			return true;
		}
		return false;
	}
}
