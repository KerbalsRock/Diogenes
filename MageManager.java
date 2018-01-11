import bc.GameController;
public class MageManager extends AttackUnitManager{
	public MageManager(GameController gc) {
		super(gc);
	}
	public MageManager(GameController gc, int id ) {
		super(gc, id);
	}
	
	public boolean blink() {
		//TODO make this work
		return false;
	}

}
