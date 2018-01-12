import java.util.ArrayList;

import bc.*;
public class AStarNode {
	public MapLocation current;
	public double f;
	public double g;
	public double h;
	public ArrayList<AStarNode> parents;
	public boolean hasParent;
	
	public AStarNode(MapLocation current) {
		this.current = current;
		hasParent = false;
	}
	
	public AStarNode(MapLocation current, AStarNode parent) {
		this.current = current;
		hasParent = true;
		parents.add(parent);
		parents.addAll(parent.parents);
	}
	
}
