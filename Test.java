import java.util.ArrayList;

import bc.*;

public class Test {
	public Test(){};
	
	public void testPathfinder(Island i) {
		PathFinder pathFinder = new PathFinder(i);
		Path path;
		int average = 0;
		int iterations = 2;
		for(int j = 0; j < iterations; j++){
			long beginMil = System.currentTimeMillis();
			path = pathFinder.generatePath(i.list.get((int)(Math.random()*5)),i.list.get(20 + (int)(Math.random()*5)));
			long endMil = System.currentTimeMillis();
			System.out.println(path.toString());
			long mil = endMil-beginMil;
			System.out.println("It took " + mil + " milliseconds");
			average += mil;
		}
		System.out.println("It took an average of " + average/iterations + " milliseconds");
		System.out.println();
	}
}
