
/*import java.util.ArrayList;
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
		
		closedSet.add(startNode);
		openSet.addAll(getSuccessors(startNode, endNode, closedSet, openSet));
		while(openSet.size() > 0) {
			AStarNode currentNode = poll(openSet);
			if(endNode.mapLoc.equals(currentNode.mapLoc)) {
				bestNodeAfterSearch = currentNode;
				return currentNode;
			}
			ArrayList<AStarNode> successorNodes = getSuccessors(currentNode, endNode, closedSet, openSet);
	        for(AStarNode successorNode : successorNodes) {
	            boolean inOpenSet;
	            //don't need to check for closed set twice
	            /* Special rule for nodes that are generated within other nodes:
	             * We need to ensure that we use the node and
	             * its g value from the openSet if its already discovered
	             
	            inOpenSet = openSet.contains(successorNode);
	            //compute tentativeG
	            double tentativeG = currentNode.getG() + 1;
	            //node was already discovered and this path is worse than the last one
	            if(inOpenSet && tentativeG >= successorNode.getG()) {
	                continue;
	            }
	            //don't need to take it out and put it back in
	            //WOOOO
	            successorNode.setParent(currentNode);
	            if(!inOpenSet){
	                openSet.add(successorNode);
	            }
	        }
	        
        }
		return null;
		
	}
	private ArrayList<AStarNode> getSuccessors(AStarNode currentNode, AStarNode endNode, ArrayList<AStarNode> closedSet, ArrayList<AStarNode> openSet) {
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
			    		if(!value.hasParent) {
			    			value.setParent(currentNode);
			    		}
			    		value.h = Math.max(Math.abs(value.mapLoc.getX()-endNode.mapLoc.getX()), 
			    				Math.abs(value.mapLoc.getY()-endNode.mapLoc.getY()));
			    		value.f = value.h + value.g;
			    		successors.add(value);
			    }
			}
		}
		closedSet.add(currentNode);
		openSet.remove(currentNode);
		return successors;
	}

	private AStarNode poll(ArrayList<AStarNode> openSet) {
		double lowestF = 1000;
		int lowestFIndex = -1;
		for(int i = 0; i < openSet.size(); i++) {
			if(openSet.get(i).f < lowestF) {
				lowestF = openSet.get(i).f;
				lowestFIndex = i;
			}
		}
		return openSet.get(lowestFIndex);
	}
}*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import bc.*;

public class PathFinder {

	public AStarNode bestNodeAfterSearch;

	private Island island;

	public Island getIsland() {
		return island;
	}

	public void setIsland(Island island) {
		this.island = island;
	}

	public PathFinder(Island island) {
		this.island = island;
	}

	public Path generatePath(MapLocation startLoc, MapLocation endLoc) {
		AStarNode tempNode = search(island.convertToHashMap().get(startLoc.toString()),
				island.convertToHashMap().get(endLoc.toString()));
		ArrayList<MapLocation> locList = new ArrayList<MapLocation>();
		locList.add(tempNode.mapLoc);
		for (AStarNode n : tempNode.parents) {
			locList.add(n.mapLoc);
		}
		return new Path(locList);
	}

	private AStarNode search(AStarNode startNode, AStarNode endNode) {
		System.out.println("start node: " + startNode);
		System.out.println("end node: " + endNode);
		PriorityQueue<AStarNode> openSet = new PriorityQueue<AStarNode>(new NodeComparer());
		ArrayList<AStarNode> closedSet = new ArrayList<AStarNode>();
		int nodesExpanded = 0;
		openSet.add(startNode);
		while (!openSet.peek().equals(endNode)) {
			AStarNode currentNode = openSet.poll();
			closedSet.add(currentNode);

			for (AStarNode successorNode : getSuccessors(currentNode, endNode, closedSet)) {
				double estimatedG = currentNode.g + 1;
				boolean inOpenSet = openSet.contains(successorNode);

				if (inOpenSet && estimatedG > successorNode.g) {
					continue;
				}
				if (inOpenSet && estimatedG < successorNode.g) {
					openSet.remove(successorNode);
					inOpenSet = false;
				}
				if (!inOpenSet && !closedSet.contains(successorNode)) {
					successorNode.setParent(currentNode);				
					successorNode.h = Math.max(Math.abs(successorNode.mapLoc.getX() - endNode.mapLoc.getX()),
							Math.abs(successorNode.mapLoc.getY() - endNode.mapLoc.getY()));
					successorNode.f = successorNode.h + successorNode.g;
					openSet.add(successorNode);
					
				}
			}
			System.out.println("open set: " + openSet);
			System.out.println("closed set: " + closedSet);
			System.out.println("nodes expanded: " + nodesExpanded);
			nodesExpanded++;
		}
		return endNode;
	}

	private ArrayList<AStarNode> getSuccessors(AStarNode currentNode, AStarNode endNode,
			ArrayList<AStarNode> closedSet) {
		ArrayList<AStarNode> successors = new ArrayList<AStarNode>();
		ArrayList<MapLocation> locs = new ArrayList<MapLocation>();
		for (Direction d : Direction.values()) {
			if (d != Direction.Center) {
				locs.add(currentNode.mapLoc.add(d));
			}
		}
		for (int i = 0; i < locs.size(); i++) {
			AStarNode value = island.convertToHashMap().get(locs.get(i).toString());
			if (value != null && !closedSet.contains(value)) {
				successors.add(value);
			}
		}
		return successors;
	}
}