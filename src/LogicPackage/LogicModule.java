package LogicPackage;
import java.util.List;

import GameObjects.*;
import Scenes.GameLevel;

public class LogicModule {
	private GameLevel level;
	private List<GameEntity> entityList;
	
	public LogicModule(GameLevel level) {
		this.level = level;
		this.entityList = level.getEntityList();
	}
	
	public void logicModuleUpdate() {
		checkCollisions();
		keyCheck(level);
		moveEntitys();
		
	}
	
	public void checkCollisions() {
		for (int i = 0; i < entityList.size(); i++) {
			for (int j = 0; j < entityList.size(); j++) {
				entityList.get(i).collidingWith(entityList.get(j));
			}
			
			if (entityList.get(i) instanceof Player && !entityList.get(i).getAlive()) {
				System.exit(0);
			}
			
			if (!entityList.get(i).getAlive()) {
				entityList.remove(i);
			}
		}
	}
	
	public void aliveCheck() {
		
	}
	
	public void keyCheck(GameLevel level) {
		level.getModel().keyPressCheck();
	}

	public void moveEntitys() {
		
		for (int i = 0; i < entityList.size(); i++) {
			entityList.get(i).logicUpdate();
		}
	}
}
