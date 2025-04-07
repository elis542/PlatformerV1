package GameObjects;
import java.util.ArrayList;
import java.util.List;

import LogicPackage.GameControllModel;
import LogicPackage.Main;
import LogicPackage.PlayerInventory;
import Scenes.GameLevel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.util.Duration;

public class Player extends GameEntity {
	private int x = 0;
	private int timer = 0;
	private int jumpTimer = 0;
	private int jumpTimerModifier = 50;
	private boolean jumpAllowed = true;
	private PlayerInventory inventory;
	private boolean itemSelected = false;
	private boolean allowRunAnimation = true;
	private GameControllModel model;

	public Player(double xPos, double yPos, GameLevel level, double width, double height, String sprite) {
		super(xPos, yPos, level, width, height, sprite);
		
		setVelocity(6);
		isPlayer();
		
		inventory = new PlayerInventory(xPos, yPos, this);
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder.png", "/sprites/placeables/grass_pad.png"));
		inventory.add(new Cover(0, 0, level, Main.getWidthAndHeight()[0]/10, Main.getWidthAndHeight()[1]/20, "sprites/icons/iconplaceholder2.png", "/sprites/placeables/stone_pad.png"));

		setRunAnimation(createRunAnimationList());
		setJumpAnimation(createJumpAnimationList());
		setOriginalSprite();
		
		runAnimationPlay.setCycleCount(Timeline.INDEFINITE);
		
		jumpAnimationPlay.setCycleCount(9);
		jump.setCycleCount(35);
		jumpAnimationPlay.setOnFinished(event -> {
			setOriginalSprite();
			allowRunAnimation = true;
		});
	}
	
	public void setModel(GameControllModel model) {
		this.model = model;
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
		gc.drawImage(getEntitySprite(), adjustedX, (getYPos() - getEntityHeight()/2)+Main.getWidthAndHeight()[1]/800);
		//gc.strokeRect(adjustedX, (getYPos() - getEntityHeight()/2), getEntityWidth(), getEntityHeight()); //Detta är endast för att se hitboxen av spelaren
		gc.restore(); 
		
		inventory.drawYourself(gc);
		for (Placeable item : inventory.getPlaceables()) {
			if (item.getSelect()) {
				item.drawYourselfOnMouse(gc, model);
			}
		}
	}

	@Override
	public void logicUpdate() {
		if (!(collisionXandY(0, +getVelocity(), true) && !(jump.getStatus() == Animation.Status.RUNNING))) {
			addYPos(getVelocity());
			if (standingOn(getVelocity()) != null) {
				setYPos(standingOn(getVelocity()).getYPos() - getEntityHeight()/2 - standingOn(getVelocity()).getEntityHeight()/2);
			} 
		}

		if (!jumpAllowed) {
			jumpTimer++;
			if (jumpTimer > jumpTimerModifier) {
				jumpAllowed = true;
				jumpTimer = 0;
			}
		}
	}

	public void stopRun() {
		runAnimationPlay.stop();
		setOriginalSprite();
	}
	
	Timeline jumpAnimationPlay = new Timeline(
			new KeyFrame(Duration.millis(65), e -> {
				if (x > getJumpAnimation().size() - 1) {
					x = 0;
				}
				setSprite(getJumpAnimation().get(x));
				x++;
			}));
	
	Timeline jump = new Timeline(
			new KeyFrame(Duration.millis(7), e -> {
				timer++;
				if (!collisionXandY(0, -getVelocity()*4.5, true)) {
					removeYPos((getVelocity()*2) - timer/5);
				}
			}
					));

	Timeline runAnimationPlay = new Timeline(
			new KeyFrame(Duration.millis(20), e -> {
				if (x > getRunAnimation().size() - 1) {
					x = 0;
				}
				if(allowRunAnimation == true) {
					setSprite(getRunAnimation().get(x));
				}
				x++;
			}));

	public void moveCommand(String direction) {
		
		switch (direction) {
		case "UP" : //Används ej atm
			if (collisionXandY(0, -getVelocity(), true)) {
				return;
			}
			if (0 >= (getYPos() - getVelocity())) {
				setYPos(3);
				break;
			}
			removeYPos(getVelocity());
			break;

		case "DOWN" : //Används ej atm
			
			if (collisionXandY(0, +getVelocity(), true)) {
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
			if (collisionXandY(+getVelocity(), 0, true)) {
				return;
			}	
			if(getLevelWidth() <= (getXPos() + getVelocity() + 30)) {
				setXPos(getLevelWidth() - 30 - 3);
				break;
			}
			addXPos(getVelocity());
			runAnimationPlay.play();
			break;

		case "LEFT" : 
			setInvertSprite(true);
			if (collisionXandY(-getVelocity(), 0, true)) {
				return;
			}
			if (0 >= (getXPos() - getVelocity())) {
				setXPos(3);
				break;
			}
			removeXPos(getVelocity());
			runAnimationPlay.play();
			break;
			
		case "SPACE" : 
			if (jumpAllowed && (collisionXandY(0, +getVelocity(), true))) {
				timer = 0;
				allowRunAnimation = false;
				jumpAnimationPlay.play();
				jumpAllowed = false;
				jump.play();
			}
		}	
	}
	
	public void openInventory() {
		inventory.open();
	}

	public void closeInventory() {
		inventory.close();
	}
	
	public boolean getCurrentPlaceable() {
		return itemSelected;
	}
	
	public void placePlaceable(double xPos, double yPos) {
		for (int i = 0; i < inventory.getPlaceables().size(); i++) {
			if (inventory.getPlaceables().get(i).getSelect()) {
				inventory.getPlaceables().get(i).setXPos(xPos);
				inventory.getPlaceables().get(i).setYPos(yPos);
				if (!inventory.getPlaceables().get(i).collisionXandY(0, 0, false)) {
					getLevel().addEntity(inventory.getPlaceables().get(i));
					itemSelected = false;
					inventory.remove(i);
				} 
			}
		}
	}
	
	public void grab(double xPos, double yPos) {
		for (Placeable a : inventory.getPlaceables()) {
			if (a.getCurrentPos()[0] + a.getIconSize() > xPos && xPos > a.getCurrentPos()[0] && inventory.getOpen()) {
				if (a.getCurrentPos()[1] + a.getIconSize() > yPos && yPos > a.getCurrentPos()[1]) {
					a.select();
					itemSelected = true;
					break;
				}
			}
		}
	}

	public void fire(double xClick, double yClick) {
		getLevel().bulletFired(new Bullet(getXPos(), getYPos(), getLevel(), xClick, yClick, true, false, 17, "sprite"));
		itemSelected = false;
		inventory.deselectAll();
	}
	
	public List<String> createRunAnimationList() {
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
		return runAnimation;
	}
	
	public List<String> createJumpAnimationList() {
		List<String> jumpAnimation = new ArrayList<>();
		jumpAnimation.add("/sprites/ninjasprites/Jump__000.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__001.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__002.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__003.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__004.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__005.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__006.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__007.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__008.png");
		jumpAnimation.add("/sprites/ninjasprites/Jump__009.png");
		return jumpAnimation;
	}
	
	public void setPlayerSpawn(double spawnX, double spawnY) {
		setXPos(spawnX);
		setYPos(spawnY);
	}
}
