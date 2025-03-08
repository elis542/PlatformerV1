import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameLevel extends Canvas {
	private GraphicsContext gc = getGraphicsContext2D();
	private List<GameEntity> entityList = new ArrayList<>();
	private Player player;
	private GameControllModel model;

	public GameLevel(double width, double height) {
		setWidth(width - height/45);
		setHeight(height - height/20);
		
		player = new Player(getWidth()/2, getHeight()/2, this, width/70, height/25, "/sprites/ninjasprites/Idle__000.png");
		
		entityList.add(new Object(width/2, height-height/10, this, width, height/10, null));
		entityList.add(new BirdEnemy(20, 20, this, 40, 40d, null));
		entityList.add(player);
		model = new GameControllModel(player);
		
		setFocusTraversable(true);
		
		setOnMouseClicked(event -> {
			model.mouseClicked(event);
		});
		
		setOnKeyPressed(event -> {
				model.keyPressControl(this);
				});
	}
	
	public List<GameEntity> getEntityList() {
		return entityList;
	}
	
	public GameControllModel getModel() {
		return model;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GraphicsContext getGc() {
		return gc;
	}
	
	public void reDraw() {
		gc.clearRect(0, 0, getWidth(), getHeight());
		for (GameEntity entity : entityList) {
			entity.drawYourself(gc);
		}
	}

	public void bulletFired(Bullet bullet) {
		entityList.add(bullet);
	}
} 
