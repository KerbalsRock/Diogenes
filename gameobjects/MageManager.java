package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;
import pathfinder.Path;

public class MageManager extends AttackUnitManager{
	public ArrayList<Mage> mages;
	public MageManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		mages = new ArrayList<Mage>();
		for(GameObject o : unitList) {
			mages.add((Mage)o);
		}
	}

}
