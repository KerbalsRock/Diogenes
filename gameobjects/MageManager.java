package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;

public class MageManager extends AttackUnitManager{
	public ArrayList<Mage> mages;
	public MageManager(GameController gc, ArrayList<GameObject> unitList, GameAnalyzer ga) {
		super(gc, unitList, ga);
		mages = new ArrayList<Mage>();
		for(GameObject o : unitList) {
			mages.add((Mage)o);
		}
	}

}
