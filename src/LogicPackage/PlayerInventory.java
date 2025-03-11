package LogicPackage;
import java.util.ArrayList;
import java.util.List;

import GameObjects.Placeable;
import GameObjects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerInventory {
	private boolean inventoryOpen;
	private Player player;
	private List<Placeable> inventoryItems = new ArrayList<>();
	private final double inventoryHeight = 200;
	private final double inventoryWidth = 325;
	private double[] inventoryLocation = new double[2];
	private double[] firstIconPos = new double [2];
	
	
	public PlayerInventory(double width, double height, Player player) {
		this.player = player;
		
	}
	
	public void drawYourself(GraphicsContext gc) {
		locationUpdates();
		
		if (inventoryOpen) {
			gc.setFill(Color.rgb(100, 24, 13, 0.5));
			gc.fillRect(inventoryLocation[0], inventoryLocation[1], inventoryWidth, inventoryHeight);
			double xOffset = 0;
			double yOffset = 0;
			for (Placeable a : inventoryItems) {
				a.drawYourselfInventory(gc, firstIconPos[0] + xOffset, firstIconPos[1] + yOffset);
				xOffset += 55;
				if (xOffset >= 330) {
					xOffset = 0;
					yOffset += 55;
				}
			}
		}
	}
	
	public boolean getOpen() {
		return inventoryOpen;
	}
	
	public List<Placeable> getPlaceables() {
		return inventoryItems;
	}
	
	public void remove(int item) {
		inventoryItems.remove(item);
	}
	
	public void deselectAll() {
		for (Placeable a : inventoryItems) {
			a.deselect();
		}
	}
	
	public void locationUpdates() {
		inventoryLocation[0] = player.getXPos() - inventoryWidth/3;
		inventoryLocation[1] = player.getYPos() - (inventoryHeight + player.getEntityHeight());
		
		firstIconPos[0] = inventoryLocation[0];
		firstIconPos[1] = inventoryLocation[1];
		
	}

	public void add(Placeable item) {
		inventoryItems.add(item);
	}
	
	public void open() {
		inventoryOpen = true;
	}
	
	public void close() {
		inventoryOpen = false;
	}
}
