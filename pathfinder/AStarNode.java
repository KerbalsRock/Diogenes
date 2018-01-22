package pathfinder;
import java.util.ArrayList;

import bc.*;
public class AStarNode {
	public MapLocation mapLoc;
	public double f;
	public double g;
	public double h;
	public ArrayList<AStarNode> parents = new ArrayList<AStarNode>();
	public AStarNode parent;
	public boolean hasParent;
		
	public AStarNode(MapLocation current) {
		mapLoc = current;
		hasParent = false;
		f = 999;
	}
	
	public AStarNode(MapLocation current, AStarNode parent) {
		mapLoc = current;
		hasParent = true;
		this.parent = parent;
		parents.add(parent);
		parents.addAll(parent.parents);
		g = getG();
		f = 999;
	}
	
	public void setParent(AStarNode parent) {
		hasParent = true;
		this.parent = parent;
		parents.clear();
		parents.add(parent);
		parents.addAll(parent.parents);
		getG();
	}
	
	public double getG() {
		g = parents.size() + 1;
		return g;
	}
	
	public String toString(){
		return "("+mapLoc.getX()+", "+mapLoc.getY()+")";
	}
	
	public void clear() {
		g = 0;
		h = 0;
		f = 0;
		hasParent = false;
		parent = null;
		parents.clear();
	}
	
}
