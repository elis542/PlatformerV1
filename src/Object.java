import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Object extends GameEntity {

	public Object(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		super(xPos, yPos, level, width, height, sprite);
		setHard(true);
	}

	
	public void collidingWith(GameEntity collider) { 
		
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(getXPos()-getEntityWidth()/2, getYPos()-getEntityHeight()/2, getEntityWidth(), getEntityHeight());
	}

	@Override
	public void logicUpdate() {
		// TODO Auto-generated method stub
	}
}
