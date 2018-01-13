import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import bc.*;

public class PathFinder2 {
	
	public AStarNode bestNodeAfterSearch;
	
	private Island island;
	public Island getIsland() {
		return island;
	}public void setIsland(Island island){
		this.island = island;
	}
	
	
	
	public PathFinder2(Island island){
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
		PriorityQueue<AStarNode> openSet = new PriorityQueue<AStarNode>(new NodeComparer());
		ArrayList<AStarNode> closedSet = new ArrayList<AStarNode>();
		
		openSet.add(startNode);		
		while(!openSet.peek().equals(endNode)) {
			AStarNode currentNode = openSet.poll();
			closedSet.add(currentNode);
			
			for(AStarNode successorNode : getSuccessors(currentNode, endNode)) {
				double cost = currentNode.g + 1;
	            boolean inOpenSet = openSet.contains(successorNode);
	            
	            if(inOpenSet && cost < successorNode.getG()){
	            	openSet.remove(successorNode);
	            }
	            if(!inOpenSet && !closedSet.contains(successorNode)){
	            	successorNode.g = cost;
	            	openSet.add(successorNode);
	            	successorNode.h = Math.max(Math.abs(successorNode.mapLoc.getX()-endNode.mapLoc.getX()), 
		    				Math.abs(successorNode.mapLoc.getY()-endNode.mapLoc.getY()));
		    		successorNode.f = successorNode.h + successorNode.g;
	            	successorNode.setParent(currentNode);
	            }
	        }
        }
		return null;		
	}
	private ArrayList<AStarNode> getSuccessors(AStarNode currentNode, AStarNode endNode) {
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
				successors.add(value);
			}
		}
		return successors;
	}
}