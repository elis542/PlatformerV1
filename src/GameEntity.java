import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameEntity {
	private double xPos;
	private double yPos;
	private double velocity = 1;
	private GameLevel level;
	private double entityWidth;
	private double entityHeigth;
	private int health = 1;
	private boolean alive = true;
	private boolean hard = false; //You cant walk through hard objects
	private Image entitySprite;
	private List<String> runAnimation = new ArrayList<>();
	private String originalSprite;
	private boolean isEnemy = true;
	private boolean isPlayer = false;
	private boolean invertSprite = false;
	
	public GameEntity(double xPos, double yPos, GameLevel level) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.level = level;
	}
	
	public void isPlayer() {
		isPlayer = true;
		isEnemy = false;
	}
	
	public boolean getInvertSprite() {
		return invertSprite;
	}
	
	public void setInvertSprite(boolean value) {
		invertSprite = value;
	}
	
	public GameEntity(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.level = level;
		entityWidth = width;
		entityHeigth = height;
		originalSprite = sprite;
		if (sprite != null) {
			entitySprite = new Image(sprite);
		}
	}
	
	public void setOriginalSprite() {
		setSprite(originalSprite, false);
	}
	
	public void setRunAnimation(List<String> animations) {
		runAnimation = animations;
	}
	
	public List<String> getRunAnimation() {
		return runAnimation;
	}
	
	public Image getEntitySprite() {
		return entitySprite;
	}

	public void setSprite(String sprite, boolean inverted) {
		entitySprite = new Image(sprite, -entityWidth, entityHeigth, true, true);
	}

	public void setHard(boolean value) {
		hard = value;
	}

	public boolean getHard() {
		return hard;
	}
	
	public void collidingWith(GameEntity collider) {
		if (collider instanceof Bullet && collider.getXPos() - getXPos() <= getEntityWidth() && getXPos() - collider.getXPos() <= getEntityWidth()) {
			Bullet colliderHolder = (Bullet) collider;
			if (colliderHolder.getYPos() - getYPos() < getEntityHeight() && getYPos() - colliderHolder.getYPos() < getEntityHeight() && (colliderHolder.getHitEnemy() == isEnemy || colliderHolder.getHitPlayer() == isPlayer) && colliderHolder.getAlive())  {
				colliderHolder.hit(1);
				hit(1);
			}
		}
	}
	
	public double getLevelHeight() {
		return level.getHeight();
	}
	
	public double getLevelWidth() {
		return level.getWidth();
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public boolean collisionY(double direction, boolean hasToBeHard) {	
		for (GameEntity a : getLevel().getEntityList()) {
			if (a.getHard() == hasToBeHard && a.getYPos() - (getYPos()-getEntityHeight()/2 + direction) <= (a.getEntityHeight()/2) && ((getYPos()-getEntityHeight()/2) + direction) - a.getYPos() <= (a.getEntityHeight()/2)) {
				return true;
			}else if (a.getHard() == hasToBeHard && a.getYPos() - (getYPos()+getEntityHeight()/2 + direction) <= (a.getEntityHeight()/2) && ((getYPos()+getEntityHeight()/2) + direction) - a.getYPos() <= (a.getEntityHeight()/2)) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean collisionX(double direction, boolean hasToBeHard) {
		for (GameEntity a : getLevel().getEntityList()) {
			if (a.getHard() == hasToBeHard && a.getXPos() - (getXPos()-getEntityWidth()/2 + direction) <= (a.getEntityWidth()/2) && ((getXPos()-getEntityWidth()/2) + direction) - a.getXPos() <= (a.getEntityWidth()/2)) {
				return true;
			} else if (a.getHard() == hasToBeHard && a.getXPos() - (getXPos()+getEntityWidth()/2 + direction) <= (a.getEntityWidth()/2) && (getXPos()+getEntityWidth()/2 + direction) - a.getXPos() <= a.getEntityWidth()/2) {
				return true;
			}
		}
		
		return false;
	}
	
	public void hit(int damage) {
		health -= 1;
		if (health <= 0) {
			alive = false;
		}
	}
	
	public GameLevel getLevel() {
		return level;
	}
	
	public double getEntityHeight() {
		return entityHeigth;
	}
	
	public double getEntityWidth() {
		return entityWidth;
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setVelocity(double amount) {
		velocity = amount;
	}
	
	public void removeYPos(double amount) {
		yPos -= amount;
	}
	
	public void setYPos(double amount) {
		yPos = amount;
	}
	
	public void removeXPos(double amount) {
		xPos -= amount;
	}
	
	public void addYPos(double amount) {
		yPos += amount;
	}
	
	public void addXPos(double amount) {
		xPos += amount;
	}
	
	public void setXPos(double amount) {
		xPos = amount;
	}
	
	public abstract void drawYourself(GraphicsContext gc);
	
	public abstract void logicUpdate();
	
}
