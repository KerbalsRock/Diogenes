package gameobjects;
import bc.*;
public class Worker extends BasicUnit{
		public Worker(GameController gc) {
			super(gc);
		}
		public Worker(GameController gc, int id ) {
			super(gc, id);
		}
		
		public boolean blueprint(UnitType type, Direction direction) {
			if(gc.canBlueprint(id, type, direction)){
				gc.blueprint(id, type, direction);
				return true;
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
		
		public boolean harvestClosest(){
			if(harvestNearby()){
				return true;
			}
			return move(gc.unit(id).location().mapLocation().directionTo(closestKarbonite(2)));
		}
		
		public MapLocation closestKarbonite(int layer){
			MapLocation loc = gc.unit(id).location().mapLocation();
			for(int i = -layer; i <= layer; i++){
				MapLocation newloc = loc.translate(-layer, i);
				if(gc.karboniteAt(newloc)>0 && gc.isOccupiable(newloc) == 1){
					return newloc;
				}
			}
			for(int i = -layer; i <= layer; i++){
				MapLocation newloc = loc.translate(layer, i);
				if(gc.karboniteAt(newloc)>0 && gc.isOccupiable(newloc) == 1){
					return newloc;
				}
			}
			for(int i = -layer+1; i < layer; i++){
				MapLocation newloc = loc.translate(i, -layer);
				if(gc.karboniteAt(newloc)>0 && gc.isOccupiable(newloc) == 1){
					return newloc;
				}
			}
			for(int i = -layer+1; i < layer; i++){
				MapLocation newloc = loc.translate(i, layer);
				if(gc.karboniteAt(newloc)>0 && gc.isOccupiable(newloc) == 1){
					return newloc;
				}
			}
			return closestKarbonite(layer+1);
		}
		

}