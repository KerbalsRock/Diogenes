import java.util.ArrayList;

import bc.*;

public class Test {
	public Test(){};
	
	public void testPathfinder(Island i, MapLocation startLoc, MapLocation endLoc) {
		PathFinder pathFinder = new PathFinder(i);
		Path path;
		int average = 0;
		int iterations = 20;
		for(int j = 0; j < iterations; j++){
			long beginMil = System.currentTimeMillis();
			path = pathFinder.generatePath(startLoc, endLoc);
			long endMil = System.currentTimeMillis();
			System.out.println(path.toString());
			long mil = endMil-beginMil;
			System.out.println("It took " + mil + " milliseconds");
			average += mil;
		}
		long beginMil = System.currentTimeMillis();
		System.out.println("It took an average of " + average/iterations + " milliseconds");
		long endMil = System.currentTimeMillis();
		System.out.println("It took " + (endMil-beginMil) + " milliseconds to print a line");
		System.out.println();
	}
}
