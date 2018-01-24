package pathfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

import analyzers.Island;
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
		boolean contains = false;
		for(int i = 0; i < island.list.size(); i++){
			if(island.list.get(i).toString().equals(endLoc.toString())){
				contains = true;
			}
		}
		if(contains = false){
			return null;
		}
		AStarNode startNode = island.convertToHashMap().get(startLoc.toString());
		AStarNode endNode = island.convertToHashMap().get(endLoc.toString());
		PriorityQueue<AStarNode> openSet = new PriorityQueue<AStarNode>(new NodeComparer());
		ArrayList<AStarNode> closedSet = new ArrayList<AStarNode>();

		openSet.add(startNode);
		while (!openSet.peek().equals(endNode)) {
			AStarNode currentNode = openSet.poll();
			closedSet.add(currentNode);

			for (AStarNode successorNode : getSuccessors(currentNode, endNode, closedSet)) {
				double estimatedG = currentNode.g + 1;
				boolean inOpenSet = openSet.contains(successorNode);

				if (inOpenSet && estimatedG >= successorNode.g) {
					continue;
				}
				if (inOpenSet && estimatedG < successorNode.g) {
					openSet.remove(successorNode);
					inOpenSet = false;
				}
				if (!inOpenSet){
					successorNode.setParent(currentNode);				
					successorNode.h = Math.max(Math.abs(successorNode.mapLoc.getX() - endNode.mapLoc.getX()),
							Math.abs(successorNode.mapLoc.getY() - endNode.mapLoc.getY()));
					successorNode.f = successorNode.h + successorNode.g;
					//System.out.println("f: "+ successorNode.f);
					openSet.add(successorNode);
					
				}
			}
		}

		ArrayList<MapLocation> locList = new ArrayList<MapLocation>();
		locList.add(endNode.mapLoc);
		for (AStarNode n : endNode.parents) {
			locList.add(n.mapLoc);
		}
		for(AStarNode a : openSet) {
			a.clear();
		}
		for(AStarNode a : closedSet) {
			a.clear();
		}
		Collections.reverse(locList);
		return new Path(locList);
	}

	private ArrayList<AStarNode> getSuccessors(AStarNode currentNode, AStarNode endNode, ArrayList<AStarNode> closedSet) {
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