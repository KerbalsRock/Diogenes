package gameobjects;
import java.util.ArrayList;

import analyzers.GameAnalyzer;
import bc.GameController;

public class KnightManager extends AttackUnitManager{
	public ArrayList<Knight> knights;
	public KnightManager(GameController gc, ArrayList<GameObject> unitList) {
		super(gc, unitList);
		knights = new ArrayList<Knight>();
		for(GameObject o : unitList) {
			knights.add((Knight)o);
		}
		
	}

}
