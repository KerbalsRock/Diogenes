import bc.*;

public class Test {
	public Test(){};
	
	public void testPathfinder(Island i, MapLocation start, MapLocation end) {
		long beginMil = System.currentTimeMillis();
		PathFinder pathFinder = new PathFinder(i);
		Path path = pathFinder.generatePath(start, end);
		System.out.println(path.toString());
		System.out.println("It took " + (System.currentTimeMillis() - beginMil) + " milliseconds");
		System.out.println();
	}
}
