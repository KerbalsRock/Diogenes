package gameobjects;
import java.util.ArrayList;

import bc.Direction;
import bc.GameController;
import bc.UnitType;

public class FactoryManager extends BuildingManager{
	public ArrayList<Factory> factories;
	public FactoryManager(GameController gc, ArrayList<GameObject> objectList) {
		super(gc, objectList);
		factories = new ArrayList<Factory>();
		for(GameObject o : objectList) {
			factories.add((Factory)o);
		}
	}
	
	
	public void update() {
		for(Factory f : factories) {
			if(!gc.canSenseUnit(f.id)){
				factories.remove(f);
				continue;
			}
			f.makeUnit(UnitType.Ranger);
			f.unload();
		}
	}

}
