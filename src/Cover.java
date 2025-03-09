import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cover extends Placeable {

	public Cover(double xPos, double yPos, GameLevel level, double width, double height, String icon, String sprite) {
		super(xPos, yPos, level, width, height, icon, sprite);
		setHard(true);
		setVelocity(1);
	}
	
	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.fillRect(getXPos()-getEntityWidth()/2, getYPos()-getEntityHeight()/2, getEntityWidth(), getEntityHeight());
	}

	public void logicUpdate() {
		if (!(collisionXandY(0, 0, true)) && (collisionXandY(0, 0, false))) {
			hit(1000);
		}
		if (!(collisionXandY(0, 0, true))) {
			if (getLevelHeight() <= (getYPos() + getVelocity())) { 
				setYPos(getLevelHeight() - getEntityHeight() - 3); 
			} 
			else {
				addYPos(getVelocity());
			}
		} 
	}
}



