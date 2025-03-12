package GameObjects;
import LogicPackage.GameControllModel;
import Scenes.GameLevel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Cover extends Placeable {
	private boolean falling = true;

	public Cover(double xPos, double yPos, GameLevel level, double width, double height, String icon, String sprite) {
		super(xPos, yPos, level, width, height, icon, sprite);
		setSprite(sprite);
		setHard(true);
		setVelocity(1);
	}
	
	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.drawImage(getEntitySprite(), getXPos()-getEntityWidth()/2 , getYPos()-getEntityHeight()/2-2, getEntityWidth(), getEntityHeight());
	}

	public void logicUpdate() {
		if(landingOn(getVelocity()) && !(collisionXandY(0, 0, true))) {
			hit(1000);
		}

		if (!(collisionXandY(0, 0, true) && standingOn(getVelocity()) == null)) {
			addYPos(getVelocity());
		}
		 if (standingOn(getVelocity()) != null) {
			setYPos(standingOn(getVelocity()).getYPos() - getEntityHeight()/2 - standingOn(getVelocity()).getEntityHeight()/2 + 2);
			falling = false;
		} 
	} 
	
	public boolean getFalling() {
		return falling;
	}

	
	@Override
	public void collidingWith(GameEntity collider) {
		
	}

	@Override
	public void drawYourselfOnMouse(GraphicsContext gc, GameControllModel model) {
		gc.setFill(Color.rgb(0, 0, 0, 0.5));
		gc.setGlobalAlpha(0.5);
		gc.drawImage(getEntitySprite(), model.getMouseCordinates()[0]-getEntityWidth()/2, model.getMouseCordinates()[1]-getEntityHeight()/2, getEntityWidth(), getEntityHeight());
		gc.setGlobalAlpha(1);
	} 
}



