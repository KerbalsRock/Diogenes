package gameobjects;
import bc.*;
public class Building extends GameObject{

	public Building(GameController gc) {
		super(gc);
	}
	public Building(GameController gc, int id) {
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
	
	public boolean unload(){
		for(Direction d : directions){
			if(unload(d)){
				return true;
			}
		}
		return false;
	}
	

}
