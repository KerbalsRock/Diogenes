package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;

public class HealerManager extends AttackUnitManager{
	public ArrayList<Healer> healers;
	public HealerManager(GameController gc, ArrayList<GameObject> unitList, GameAnalyzer ga) {
		super(gc, unitList, ga);
		healers = new ArrayList<Healer>();
		for(GameObject o : unitList) {
			healers.add((Healer)o);
		}
	}
}
