import bc.*;
public class BuildingManager extends Manager{

	public BuildingManager(GameController gc) {
		super(gc);
	}
	public BuildingManager(GameController gc, int id) {
		super(gc, id);
	}
	
	public boolean load(int unitId) {
		if(gc.canLoad(id, unitId)){
			gc.load(id, unitId);
			return true;
		}
		return false;
	}
	
	public boolean unload(Direction direction) {
		if(gc.canUnload(id, direction)){
			gc.unload(id, direction);
			return true;
		}
		return false;
	}
	

}
