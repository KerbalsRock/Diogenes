import java.util.ArrayList;
import java.util.HashMap;

import bc.*;

public class Island {
	public ArrayList<MapLocation> list;
	public ArrayList<AStarNode> aStarList;
	HashMap<String, AStarNode> map = new HashMap<String, AStarNode>();
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
	public HashMap<String, AStarNode> convertToHashMap(){
		if(map.isEmpty()){
			for(AStarNode node : convertToAStar()){
				map.put(node.mapLoc.toString(), node);
			}
		}
		return map;
	}
	
}