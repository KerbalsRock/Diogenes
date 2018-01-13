import java.util.ArrayList;
import java.util.HashMap;

import bc.*;

public class Island {
	public ArrayList<MapLocation> list;
	public ArrayList<AStarNode> aStarList;
	HashMap<MapLocation, AStarNode> map = new HashMap<MapLocation, AStarNode>();
	public Island (ArrayList<MapLocation> list){
		this.list = list;
		aStarList = new ArrayList<AStarNode>();
	}
	public ArrayList<AStarNode> convertToAStar(){
		if(aStarList.size() == 0){
			for(MapLocation loc : list){
				aStarList.add(new AStarNode(loc));
			}
		}
		return aStarList;
	}
	public HashMap<MapLocation, AStarNode> convertToHashMap(){
		 for(AStarNode node : convertToAStar()){
			 map.put(node.mapLoc, node);
		 }
		 return map;
	}
	
}