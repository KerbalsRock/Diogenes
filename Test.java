import bc.*;

public class Test {
	public Test(){};
	
	public void testPathfinder(Island i) {
		long beginMil = System.currentTimeMillis();
		PathFinder pathFinder = new PathFinder(i);
		Path path = pathFinder.generatePath(i.list.get((int)Math.random()*5),i.list.get(20 + (int)Math.random()*5));
		System.out.println(path.toString());
		System.out.println("It took " + (System.currentTimeMillis() - beginMil) + " milliseconds");
		System.out.println();
	}
}
