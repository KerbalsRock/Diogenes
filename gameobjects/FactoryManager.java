package gameobjects;
import java.util.ArrayList;

import bc.Direction;
import bc.GameController;
import bc.Team;
import bc.UnitType;

public class FactoryManager extends BuildingManager{
	public ArrayList<Factory> factories;
	boolean produceRangerLast;
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
			if(gc.team().equals(Team.Blue)){
				if(produceRangerLast){
					f.makeUnit(UnitType.Knight);
					produceRangerLast = false;
				}
				else{
					f.makeUnit(UnitType.Ranger);
					produceRangerLast = true;
				}
			}
			else{
				f.makeUnit(UnitType.Knight);
			}
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
