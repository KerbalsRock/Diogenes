import java.util.ArrayList;

import bc.*;
public class PathFinder {
	
	private int maxSteps;
	public AStarNode bestNodeAfterSearch;
	
	protected Island i;
	public Island getIsland() {
		return i;
	}public void setIsland(Island island){
		i = island;
		maxSteps = i.list.size();
	}
	
	
	
	public PathFinder(Island i){
		this.i = i;
		maxSteps = i.list.size();
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
	            double tentativeG = currentNode.g() + 1;
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
}