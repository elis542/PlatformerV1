package Scenes;

import LogicPackage.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MyCanvasButton extends Canvas {
	private Image sprite;
	private GraphicsContext gc = getGraphicsContext2D();
	
	public MyCanvasButton(double width, double height, String sprite) {
		setWidth(width * Main.getScale());
		setHeight(height * Main.getScale());
		
		this.sprite = new Image(sprite);
		gc.drawImage(this.sprite, 0, 0, getWidth(), getHeight());
	}
}
	