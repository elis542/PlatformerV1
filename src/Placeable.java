import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Placeable extends GameEntity {
	private Image icon;
	private double[] currentPos = new double[2];
	private static final double iconSize = 50;
	private boolean selected;

	public Placeable(double xPos, double yPos, GameLevel level, double width, double height, String icon, String placeSprite) {
		super(xPos, yPos, level, width, height, placeSprite);
		this.icon = new Image(icon);	
	}
	
	@Override
	public void drawYourself(GraphicsContext gc) {
		
	}
	
	public void select() {
		selected = true;
	}
	
	public boolean getSelect() {
		return selected;
	}
	
	public void deselect() {
		selected = false;
	}
	
	public double getIconSize() {
		return iconSize;
	}

	public void drawYourselfInventory(GraphicsContext gc, double xPos, double yPos) {
		gc.drawImage(icon, xPos, yPos, iconSize, iconSize);
		currentPos[0] = xPos;
		currentPos[1] = yPos;

	}
	
	public double[] getCurrentPos() {
		return currentPos;
	}

	@Override
	public void logicUpdate() {
		// TODO Auto-generated method stub
		
	}
}
