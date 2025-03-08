import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class Player extends GameEntity {
	private int x = 0;
	private int timer = 0;
	private int jumpTimer = 0;
	private boolean jumpAllowed = true;

	public Player(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		super(xPos, yPos, level, width, height, sprite);
		setVelocity(2);
		isPlayer();
		
		List<String> runAnimation = new ArrayList<>();
		runAnimation.add("/sprites/ninjasprites/Run__000.png");
		runAnimation.add("/sprites/ninjasprites/Run__001.png");
		runAnimation.add("/sprites/ninjasprites/Run__002.png");
		runAnimation.add("/sprites/ninjasprites/Run__003.png");
		runAnimation.add("/sprites/ninjasprites/Run__004.png");
		runAnimation.add("/sprites/ninjasprites/Run__005.png");
		runAnimation.add("/sprites/ninjasprites/Run__006.png");
		runAnimation.add("/sprites/ninjasprites/Run__007.png");
		runAnimation.add("/sprites/ninjasprites/Run__008.png");
		runAnimation.add("/sprites/ninjasprites/Run__009.png");
		setRunAnimation(runAnimation);
		
		jump.setCycleCount(30);
		runAnimationPlay.setCycleCount(Timeline.INDEFINITE);
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
		gc.drawImage(getEntitySprite(), adjustedX, (getYPos() - getEntityHeight()/2)+Main.getHeight()/800);
		gc.strokeRect(adjustedX, (getYPos() - getEntityHeight()/2), getEntityWidth(), getEntityHeight()); //Detta är endast för att se hitboxen av spelaren
		gc.restore(); 
	}

	@Override
	public void logicUpdate() {
		if (!(collisionY(+getVelocity(), true) && collisionX(0, true) && !(jump.getStatus() == Animation.Status.RUNNING))) {
			if (getLevelHeight() <= (getYPos() + getVelocity())) { 
				setYPos(getLevelHeight() - getEntityHeight() - 3); 
			} else {
				addYPos(getVelocity());
			}
		}

		if (!jumpAllowed) {
			jumpTimer++;
			if (jumpTimer > 75) {
				jumpAllowed = true;
				jumpTimer = 0;
			}
		}
	}

	public void stopRun() {
		runAnimationPlay.stop();
		setOriginalSprite();
	}
	
	Timeline jump = new Timeline(
			new KeyFrame(Duration.millis(5), e -> {
				timer++;
				removeYPos(7);
				}
			));
	
	Timeline runAnimationPlay = new Timeline(
			new KeyFrame(Duration.millis(50), e -> {
				if (x > getRunAnimation().size() - 1) {
					x = 0;
				}
				setSprite(getRunAnimation().get(x), getInvertSprite());
				x++;
			}));

	public void moveCommand(String direction) {
		
		switch (direction) {
		case "UP" : 
			if (collisionY(-getVelocity(), true) && collisionX(0, true)) {
				return;
			}
			if (0 >= (getYPos() - getVelocity())) {
				setYPos(3);
				break;
			}
			removeYPos(getVelocity());
			break;

		case "DOWN" : 
			
			if (collisionY(+getVelocity(), true) && collisionX(0, true)) {
				return;
			}
			if (getLevelHeight() <= (getYPos() + getVelocity())) { 
				setYPos(getLevelHeight() - getEntityHeight() - 3); 
				break;
			}
			addYPos(getVelocity());
			break;

		case "RIGHT" : 
			setInvertSprite(false);
			if (collisionX(+getVelocity(), true) && collisionY(0, true)) {
				return;
			}	
			if(getLevelWidth() <= (getXPos() + getVelocity() + 30)) {
				setXPos(getLevelWidth() - 30 - 3);
				break;
			}
			addXPos(getVelocity());
			break;

		case "LEFT" : 
			setInvertSprite(true);
			if (collisionX(-getVelocity(), true) && collisionY(0, true)) {
				return;
			}
			if (0 >= (getXPos() - getVelocity())) {
				setXPos(3);
				break;
			}
			removeXPos(getVelocity());
			break;
			
		case "SPACE" : 
			if (jumpAllowed && (collisionY(+getVelocity(), true) && collisionX(0, true))) {
				timer = 0;
				jumpAllowed = false;
				jump.play();
			}
		}	
		runAnimationPlay.play();
	}


	public void fire(double xClick, double yClick) {
		getLevel().bulletFired(new Bullet(getXPos(), getYPos(), getLevel(), xClick, yClick, true, false, 5, "sprite"));
	}
}
