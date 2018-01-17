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
		
		public boolean harvest(Direction direction) {
			if(gc.canHarvest(id, direction)){
				gc.harvest(id, direction);
				return true;
			}
			return false;
		}
		

}
