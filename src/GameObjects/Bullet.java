package GameObjects;
import LogicPackage.Main;
import Scenes.GameLevel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet extends GameEntity {
	private double[] target = new double[2];
	private double dx;
	private double dy;
	private double travelLength;
	private double velocityX;
	private double velocityY;
	private boolean hitEnemy = true;
	private boolean hitPlayer = true;

	public Bullet(double xPos, double yPos, GameLevel level, double targetX, double targetY) {
		super(xPos, yPos, level);
		
		setVelocity(Main.getWidthAndHeight()[1] / 1250);
		
		trajectoryCalculation(targetX, targetY);
	}
	
	public Bullet(double xPos, double yPos, GameLevel level, double targetX, double targetY, boolean hitEnemy, boolean hitPlayer, double velocity, String sprite) {
		super(xPos, yPos, level);
		
		setVelocity(velocity);
		this.hitEnemy = hitEnemy;
		this.hitPlayer = hitPlayer;
		
		trajectoryCalculation(targetX, targetY);
	}

	public boolean getHitEnemy() {
		return hitEnemy;
	}
	
	public boolean getHitPlayer() {
		return hitPlayer;
	}
	
	public void trajectoryCalculation(double targetX, double targetY) {
		target[0] = targetX;
		target[1] = targetY;
		
		dx = target[0] - getXPos();
		dy = target[1] - getYPos();
		travelLength = Math.sqrt(dx * dx + dy * dy);
		
		velocityX = ((dx / travelLength) * getVelocity());
		velocityY = ((dy / travelLength) * getVelocity());
	}

	@Override
	public void drawYourself(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillRect(getXPos(), getYPos(), 5, 5); 
		
	}

	@Override
	public void logicUpdate() {
		if (getXPos() > getLevel().getWidth() || getXPos() < 0) {
			hit(1000);
		} 
		if (getYPos() > getLevel().getHeight() || getYPos() < 0) {
			hit(1000);
		}
		
		addXPos(velocityX);
		addYPos(velocityY);

	}

	@Override
	public void collidingWith(GameEntity collider) {
		if (collisionXandY(velocityX, velocityY, true)) {
			hit(100);
		}	

	}

}
