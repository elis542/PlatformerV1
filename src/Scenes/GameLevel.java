package Scenes;
import java.util.ArrayList;
import java.util.List;

import GameObjects.*;
import GameObjects.Object;
import LogicPackage.GameControllModel;
import LogicPackage.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameLevel extends Canvas {
	private GraphicsContext gc = getGraphicsContext2D();
	private List<GameEntity> entityList = new ArrayList<>();
	private Player player;
	private GameControllModel model;
	private double scale = Main.getScale();

	public GameLevel(double width, double height) {
		setScaleX(scale);
		setScaleY(scale);
		
		setWidth(width);
		setHeight(height);
		
		player = new Player(getWidth()/2, getHeight()/2, this, width/70, height/25, "/sprites/ninjasprites/Idle__000.png");
		
		model = new GameControllModel(player);
		entityList.add(player);
		
		setFocusTraversable(true);
		
		setOnMouseClicked(event -> {
			model.mouseClicked(event);
		});
		
		setOnKeyPressed(event -> {
			model.keyPressControl(this);
		});
		
		setOnMouseMoved(event -> {
			model.mouseLocation(event);
		});
	}
	
	public List<GameEntity> getEntityList() {
		return entityList;
	}
	
	public void addEntity(GameEntity newEntity) {
		entityList.add(newEntity);
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
			if (entity instanceof Player) {
				continue;
			}
			entity.drawYourself(gc);
		}
		player.drawYourself(gc);
	}

	public void bulletFired(Bullet bullet) {
		entityList.add(bullet);
	}
} 
