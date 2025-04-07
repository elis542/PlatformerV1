package Scenes;

import GameObjects.BirdEnemy;
import GameObjects.Object;
import GameObjects.Player;

public class GameLevel1 extends GameLevel {
	Player player;

	public GameLevel1(double width, double height) {
		super(width, height);
		
		player = getPlayer();
		player.setPlayerSpawn(250, 250);
		player.setModel(getModel());
		
		addEntity(new Object(width/5, height-height/5, this, width/10, height/2.5, null));
		addEntity(new Object(width/2, height-height/10, this, width/1.25, height/10, null));
		addEntity(new BirdEnemy(20, 20, this, width/35, width/35, null));
	}

}
