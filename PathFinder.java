import java.util.ArrayList;
import java.util.HashMap;
import bc.*;

public class PathFinder {
	
	public AStarNode bestNodeAfterSearch;
	
	private Island island;
	public Island getIsland() {
		return island;
	}public void setIsland(Island island){
		this.island = island;
	}
	
	
	
	public PathFinder(Island island){
		this.island = island;
	}
	
	public Path generatePath(MapLocation startLoc, MapLocation endLoc) {
		AStarNode tempNode = search(island.convertToHashMap().get(startLoc) , island.convertToHashMap().get(endLoc));
		ArrayList<MapLocation> locList = new ArrayList<MapLocation>();
		locList.add(tempNode.mapLoc);
		for(AStarNode n : tempNode.parents) {
			locList.add(n.mapLoc);
		}
		return new Path(locList);
	}
	
	private AStarNode search(AStarNode startNode, AStarNode endNode) {
		ArrayList<AStarNode> openSet = new ArrayList<AStarNode>();
		ArrayList<AStarNode> closedSet = new ArrayList<AStarNode>();
		
		openSet.add(startNode);		
		while(openSet.size() > 0) {
			AStarNode currentNode = poll(openSet);
			if(endNode.mapLoc.equals(currentNode.mapLoc)) {
				bestNodeAfterSearch = currentNode;
				return currentNode;
			}
			
			ArrayList<AStarNode> successorNodes = getSuccessors(currentNode, endNode, closedSet);
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
		return null;
		
	}
	private ArrayList<AStarNode> getSuccessors(AStarNode currentNode, AStarNode endNode, ArrayList<AStarNode> closedSet) {
		ArrayList<AStarNode> successors = new ArrayList<AStarNode>();
		ArrayList<MapLocation> locs = new ArrayList<MapLocation>();
		for(Direction d : Direction.values()){
			if(d != Direction.Center){
				locs.add(currentNode.mapLoc.add(d));
			}
		}
		for(int i = 0; i < locs.size(); i++) {
			AStarNode value = island.convertToHashMap().get(locs.get(i));
			if (value != null) {
			    if(!closedSet.contains(value)){
			    		value.setParent(currentNode);
			    		value.h = Math.sqrt(Math.pow(Math.abs(value.mapLoc.getX()  - endNode.mapLoc.getX()), 2)
			    				+ Math.pow(Math.abs(value.mapLoc.getY()  - endNode.mapLoc.getY()), 2));
			    		value.f = value.h + value.g;
			    		successors.add(value);
			    }
			}
		}
		closedSet.add(currentNode);
		return successors;
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