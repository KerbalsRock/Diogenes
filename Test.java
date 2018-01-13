import bc.*;

public class Test {
	public Test(){};
	
	public void testPathfinder(Island i, MapLocation start, MapLocation end) {
		PathFinder pathFinder = new PathFinder(i);
		Path path = pathFinder.generatePath(start, end);
		System.out.println(path.toString());
	}
}
