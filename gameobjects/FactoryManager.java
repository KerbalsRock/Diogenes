package gameobjects;
import java.util.ArrayList;

import bc.Direction;
import bc.GameController;
import bc.Team;
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
		for(int i = 0; i < factories.size(); i++){
			Factory f = factories.get(i);
			if(!gc.canSenseUnit(f.id)){
				factories.remove(f);
				i--;
				continue;
			}
			f.makeUnit(UnitType.Ranger);
			f.unload();
		}
	}
	
	public void add(Factory f) {
		factories.add(f);
	}
	public void remove(Factory f) {
		factories.remove(f);
	}

}
