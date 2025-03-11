package GameObjects;
import LogicPackage.Main;
import Scenes.GameLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BirdEnemy extends Enemy {
	private int timer = 0;
	private int xOffset = 0;
	private Image runImage;
	private int runImageWidth = 32;

	public BirdEnemy(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		super(xPos, yPos, level, width, height, sprite); //This does not use the classic sprite
		setVelocity(Main.getWidthAndHeight()[1] / 1000);
		runImage = new Image("/sprites/animals/Walk.png");
		runAnimation.setCycleCount(Timeline.INDEFINITE);
		runAnimation.play();
	}


	@Override
	public void drawYourself(GraphicsContext gc) {
		double adjustedX;
			gc.save();
			if (getInvertSprite()) {
				gc.scale(-1, 1);
				adjustedX = -(getXPos()+(getEntityWidth()/2));
			} else {
				adjustedX = (getXPos()-(getEntityWidth()/2));
			}
			gc.drawImage(runImage, xOffset, 0, runImageWidth, 32, adjustedX, getYPos() - getEntityHeight()/2, getEntityWidth(), getEntityWidth());
			//gc.strokeRect(adjustedX, (getYPos() - getEntityHeight()/2), getEntityWidth(), getEntityHeight()); //Detta är endast för att se hitboxen av spelaren
			gc.restore(); 
	}
	
	Timeline runAnimation = new Timeline(
			new KeyFrame(Duration.millis(75), e -> {
				xOffset += runImageWidth;

				if (xOffset >= runImageWidth * 6) {
					xOffset = 0;
				}
			}));

	@Override
	public void logicUpdate() {
		
		//Moving the enemy
		if(collisionXandY(+getVelocity(), 0, true)) {
			removeXPos(1);
		} else if(collisionXandY(-getVelocity(), 0, true)) {
			addXPos(1);
		} else if(collisionXandY(0, +getVelocity(), true)) {
			removeYPos(1);
		} else	if(collisionXandY(0, -getVelocity(), true)) {
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
		
		if (getTarget()[0] > getXPos()) {
			setInvertSprite(false);
		} else if (getTarget()[0] < getXPos()) {
			setInvertSprite(true);
		}
	}
	
	public void fire() {
		getLevel().bulletFired(new Bullet(getXPos(), getYPos(), getLevel(), getLevel().getPlayer().getXPos(), getLevel().getPlayer().getYPos(), false, true, 5, "sprite"));
	}
}
