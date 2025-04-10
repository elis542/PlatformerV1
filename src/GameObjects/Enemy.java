package GameObjects;
import LogicPackage.Main;
import Scenes.GameLevel;

public abstract class Enemy extends GameEntity {
	private double[] target = new double[2];
	private double dx;
	private double dy;
	private double travelLength;
	private double velocityX;
	private double velocityY;

	public Enemy(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		super(xPos, yPos, level, width, height, sprite);
		setVelocity(3);
		isEnemy();
	}


	//@Override
	public void collidingWith(GameEntity collider) {
		if (collider instanceof Bullet && collider.getXPos() - getXPos() <= getEntityWidth() && getXPos() - collider.getXPos() <= getEntityWidth()) {
			Bullet colliderHolder = (Bullet) collider;
			if (colliderHolder.getYPos() - getYPos() <= getEntityWidth() && getYPos() - colliderHolder.getYPos() <= getEntityWidth() && colliderHolder.getHitEnemy() && colliderHolder.getAlive())  {
				colliderHolder.hit(1);
				hit(1);
			}
		}
	}
	
	public double[] getTarget() {
		return target;
	}

	public void setTarget(double[] target) {
		this.target[0] = target[0];
		this.target[1] = target[1];

		dx = target[0] - getXPos();
		dy = target[1] - getYPos();
		travelLength = Math.sqrt(dx * dx + dy * dy);

		velocityX = ((dx / travelLength) * getVelocity());
		velocityY = ((dy / travelLength) * getVelocity());
	}

	public double[] getVelocityXandY() {
		return new double[] {velocityX, velocityY};
	}

}
