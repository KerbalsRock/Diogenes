package gameobjects;
import bc.*;
public class Worker extends BasicUnit{
	int currentTask = 5;

	public Worker(GameController gc) {
		super(gc);
	}
	public Worker(GameController gc, int id ) {
		super(gc, id);
	}
	
	public boolean blueprint(UnitType type, Direction direction) {
		if(gc.canBlueprint(id, type, direction)){
			gc.blueprint(id, type, direction);
			currentTask = 2;
			targetId = getClosestFactoryBlueprint();
			return true;
		}
		return false;
	}
	
	public boolean blueprint(UnitType type) {
		for(Direction d : directions){
			if(blueprint(type, d)){
				return true;
			}
		}
		return false;
	}
	
	public boolean build(int targetId) {
		if(gc.canBuild(id, targetId)){
			gc.build(id, targetId);
			return true;
		}
		return false;
	}
	
	public boolean repair(int targetId) {
		if(gc.canRepair(id, targetId)){
			gc.repair(id, targetId);
			return true;
		}
		return false;
	}
	
	public boolean replicate(Direction direction) {
		if(gc.canReplicate(id, direction)){
			gc.replicate(id, direction);
			return true;
		}
		return false;
	}
	
	public boolean replicate(){
		for(Direction d : Direction.values()){
			if(replicate(d)){
				return true;
			}
		}
		return false;
	}
	
	public boolean harvest(Direction direction) {
		if(gc.canHarvest(id, direction)){
			gc.harvest(id, direction);
			return true;
		}
		return false;
	}
	
	public boolean harvestNearby(){
		for(Direction d : Direction.values()){
			if(harvest(d)){
				return true;
			}
		}
		return false;
	}
	
	/*public boolean harvestClosest(){
		if(harvestNearby()){
			return true;
		}
		return move(gc.unit(id).location().mapLocation().directionTo(closestKarbonite(2)));
	}*/
	
	public MapLocation closestKarbonite(int layer, int maxDistance){
		MapLocation loc = gc.unit(id).location().mapLocation();
		for(int i = -layer; i <= layer; i++){
			MapLocation newloc = loc.translate(-layer, i);
			if(newloc.getX()<0||newloc.getX()>=gc.startingMap(Planet.Earth).getWidth()-1||newloc.getY()<0||newloc.getY()>=gc.startingMap(Planet.Earth).getHeight()-1) {
				continue;
			}
			if(gc.isOccupiable(newloc) == 1 && gc.karboniteAt(newloc)>0){
				return newloc;
			}
		}
		for(int i = -layer; i <= layer; i++){
			MapLocation newloc = loc.translate(layer, i);
			if(newloc.getX()<0||newloc.getX()>=gc.startingMap(Planet.Earth).getWidth()-1||newloc.getY()<0||newloc.getY()>=gc.startingMap(Planet.Earth).getHeight()-1) {
				continue;
			}
			if(newloc.getPlanet()==null) {
				continue;
			}
			if(gc.isOccupiable(newloc) == 1 && gc.karboniteAt(newloc)>0){
				return newloc;
			}
		}
		for(int i = -layer+1; i < layer; i++){
			MapLocation newloc = loc.translate(i, -layer);
			if(newloc.getX()<0||newloc.getX()>=gc.startingMap(Planet.Earth).getWidth()-1||newloc.getY()<0||newloc.getY()>=gc.startingMap(Planet.Earth).getHeight()-1) {
				continue;
			}
			if(newloc.getPlanet()==null) {
				continue;
			}
			if(gc.isOccupiable(newloc) == 1 && gc.karboniteAt(newloc)>0){
				return newloc;
			}
		}
		for(int i = -layer+1; i < layer; i++){
			MapLocation newloc = loc.translate(i, layer);
			if(newloc.getX()<0||newloc.getX()>=gc.startingMap(Planet.Earth).getWidth()-1||newloc.getY()<0||newloc.getY()>=gc.startingMap(Planet.Earth).getHeight()-1) {
				continue;
			}
			if(newloc.getPlanet()==null) {
				continue;
			}
			if(gc.isOccupiable(newloc) == 1 && gc.karboniteAt(newloc)>0){
				return newloc;
			}
		}
		maxDistance--;
		if(maxDistance == 0) {
			return null;
		}else {
			return closestKarbonite(layer+1, maxDistance);
		}
	}
	
	public int getClosestFactoryBlueprint(){
		Unit self = gc.unit(id);
		int closestId = -1;
		MapLocation myLocation = self.location().mapLocation();
		VecUnit nearbyBlueprints = gc.senseNearbyUnitsByType(myLocation, 8, UnitType.Factory);
		int closestDistance = 1000000;
		for(int i = 0; i < nearbyBlueprints.size(); i++){
			int enemyDistance = (int) nearbyBlueprints.get(i).location().mapLocation().distanceSquaredTo(myLocation);
			if(enemyDistance < closestDistance && nearbyBlueprints.get(i).structureIsBuilt() == 0){
				closestDistance = enemyDistance;
				closestId = nearbyBlueprints.get(i).id();
			}
		}
		return closestId;
	}
	
	public void update(){
		//System.out.println(currentTask+", "+targetId);
		switch(currentTask) {
		case 0:
			followPath();//follow de way
			harvestNearby();
		case 1:
			if(!gc.canSenseUnit(targetId)) {
				currentTask = 5;
			}
			if(!load(targetId, id)){
				moveToward(gc.unit(id).location().mapLocation());
			 }//load rocket if necessary
		case 2:
			if(!gc.canSenseUnit(targetId)) {
				currentTask = 5;
				return;
			}
			if(gc.unit(targetId).health()==gc.unit(targetId).maxHealth()) {
				currentTask = 5;
				return;
			}
			else if(!repair(targetId) && !build(targetId)){
				moveToward(gc.unit(targetId).location().mapLocation());
			}
		case 3:
			blueprint(UnitType.Factory);
		case 4:
			blueprint(UnitType.Rocket);
		case 5:
			/*if(!harvestNearby()){
				MapLocation closestKarb = closestKarbonite(1, 4);
				if(closestKarb!=null) {
					moveToward(closestKarb);
				}else {*/
					if(harvestNearby()){
						moveRandomly();
					}
					else{
						moveRandomly();
						harvestNearby();
					}
				//}
			//}
		}
	}

}
