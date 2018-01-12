import java.util.ArrayList;
import bc.*;
public class Path {
	public ArrayList<MapLocation> locList;
	
	public Path() {}
	
	public Path(ArrayList<MapLocation> list) {
		locList = list;
	}
		
	public MapLocation getStart(){
		return locList.get(0);
	}
	
	public MapLocation getEnd(){
		return locList.get(locList.size()-1);
	}
	
}
