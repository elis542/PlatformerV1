package Scenes;

import LogicPackage.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MyCanvasButton extends Canvas {
	private Image sprite;
	private GraphicsContext gc = getGraphicsContext2D();
	private double scale = Main.getScale();
	
	public MyCanvasButton(double width, double height, String sprite) {
		setWidth(width * scale);
		setHeight(height * scale);
		
		this.sprite = new Image(sprite);
		drawMyself();
	}
	
	public MyCanvasButton(double width, double height, String sprite, double borderWidth) {
		this(width, height, sprite);
		
		this.hoverProperty().addListener((observable, oldVal, newVal) -> {
			
			gc.setLineWidth(borderWidth * scale);
			
			if (newVal) {
				gc.strokeRect(0, 0, width * scale, height * scale);
			} else {
				gc.clearRect(0, 0, width * scale, height * scale);
				drawMyself();
			}
		});
	}
	
	private void drawMyself() {
		gc.drawImage(this.sprite, 0, 0, getWidth(), getHeight());
	}
	
	
}
	