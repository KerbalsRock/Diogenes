import java.util.ArrayList;

import bc.*;
public class PathFinder {
	
	public AStarNode bestNodeAfterSearch;
	
	public Island i;
	
	public PathFinder(Island i){
		this.i = i;
	}
	
	public Path generatePath(MapLocation startNode, MapLocation endNode) {
		//TODO make this work
		return null;
	}
	
	protected AStarNode search(AStarNode startNode, AStarNode endNode) {
		ArrayList<AStarNode> openSet = new ArrayList<AStarNode>();
		ArrayList<AStarNode> closedSet = new ArrayList<AStarNode>();
		int steps = 0;
		
		while(openSet.size() > 0) {
			AStarNode currentNode = poll(openSet);
			if(endNode.mapLoc.equals(currentNode.mapLoc)) {
				bestNodeAfterSearch = currentNode;
				return currentNode;
			}
			
			ArrayList<AStarNode> successorNodes = getSuccessors(currentNode);
	        for(AStarNode successorNode : successorNodes) {
	            boolean inOpenSet;
	            if(closedSet.contains(successorNode)) {
	                continue;
	            }
	            /* Special rule for nodes that are generated within other nodes:
	             * We need to ensure that we use the node and
	             * its g value from the openSet if its already discovered
	             */
	            if(openSet.contains(successorNode)) {
	                inOpenSet = true;
	            } else {
	                inOpenSet = false;
	            }
	            //compute tentativeG
	            double tentativeG = currentNode.getG() + 1;
	            //node was already discovered and this path is worse than the last one
	            if(inOpenSet && tentativeG >= successorNode.getG()) {
	                continue;
	            }
	            if(inOpenSet) {
	                openSet.remove(successorNode);
	                successorNode.setParent(currentNode);
	                openSet.add(successorNode);
	            } else {
	                successorNode.setParent(currentNode);
	                openSet.add(successorNode);
	            }
	        }
        }
		
	}
	private ArrayList<AStarNode> getSuccessors(AStarNode currentNode, AStarNode endNode) {
		ArrayList<AStarNode> successors = new ArrayList<AStarNode>();
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				if(loc.getX()+i < 0 || loc.getX()+i >= p.getWidth() || loc.getY()+i < 0 || loc.getY()+i >= p.getHeight()){
					continue;
				}
				else if(!(i == 0 && j == 0)){
					adjacent.add(loc.translate(i, j));
				}
			}
		}
		return adjacent;
	}
	private AStarNode poll(ArrayList<AStarNode> openSet) {
		double highestF = -1;
		int highestFIndex = -1;
		for(int i = 0; i < openSet.size(); i++) {
			if(openSet.get(i).f > highestF) {
				highestF = openSet.get(i).f;
				highestFIndex = i;
			}
		}
		return openSet.get(highestFIndex);
	}
}