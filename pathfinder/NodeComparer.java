package pathfinder;
import java.util.Comparator;

public class NodeComparer implements Comparator<AStarNode>{

	public int compare(AStarNode a, AStarNode b) {
		if(a.f < b.f){
			return -1;
		}
		if(a.f == b.f){
			return 0;
		}
		return 1;
	}

}
