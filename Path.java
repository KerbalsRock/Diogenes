import java.util.ArrayList;
import bc.*;
public class Path {
	public ArrayList<MapLocation> locList;
	
	public Path(ArrayList<MapLocation> list) {
		locList = list;
	}
		
	public MapLocation getStart(){
		return locList.get(0);
	}
	
	public MapLocation getEnd(){
		return locList.get(locList.size()-1);
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < locList.size(); i++) {
			s += i+1 + ": (" + locList.get(i).getX() + " ," + locList.get(i).getY() + ")\n";
		}
		return s;
	}
	
}
