import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BasicEnemy extends Enemy {
	private int timer = 0;

	public BasicEnemy(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		super(xPos, yPos, level, width, height, sprite);
		setVelocity(1);
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(Color.FIREBRICK);
		gc.fillRect(getXPos() - getEntityWidth()/2, getYPos() - getEntityHeight()/2, getEntityWidth(), getEntityHeight());
	}

	@Override
	public void logicUpdate() {
		
		//Moving the enemy
		if(collisionX(+getVelocity(), true) && collisionY(0, true)) {
			removeXPos(1);
		} else if(collisionX(-getVelocity(), true) && collisionY(0, true)) {
			addXPos(1);
		} else if(collisionX(0, true) && collisionY(+getVelocity(), true)) {
			removeYPos(1);
		} else	if(collisionX(0, true) && collisionY(-getVelocity(), true)) {
			addYPos(1);
		}	
		
		setTarget(new double[] {getLevel().getPlayer().getXPos(), getLevel().getPlayer().getYPos()});
		
		addXPos(getVelocityXandY()[0]);
		addYPos(getVelocityXandY()[1]);
		
		
		timer++;
		if (timer >= 300) {
			fire();
			timer = 0;
		}
		
	}
	
	public void fire() {
		getLevel().bulletFired(new Bullet(getXPos(), getYPos(), getLevel(), getLevel().getPlayer().getXPos(), getLevel().getPlayer().getYPos(), false, true, 5, "sprite"));
	}
}
