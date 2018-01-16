import bc.GameController;
public class Knight extends AttackUnit{
	//work
	public Knight(GameController gc) {
		super(gc);
	}
	public Knight(GameController gc, int id ) {
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
