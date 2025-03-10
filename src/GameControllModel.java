import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameControllModel {
	private Player player;
	private Set<KeyCode> activeKeys = new HashSet<>();

	public GameControllModel(Player player) {
		this.player = player;
	} 

	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			player.fire(event.getX(), event.getY());
		}
		if (event.getButton() == MouseButton.SECONDARY) {
			if (!player.getCurrentPlaceable()) {
				player.grab(event.getX(), event.getY());
				System.out.println("grabbing!"); //TA BORT
			} else {
				player.placePlaceable(event.getX(), event.getY());
				System.out.println("placing!"); //TA BORT
			}
		}
	}
	
	public void keyPressControl(GameLevel level) {
		level.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
		level.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));
	}

	public void keyPressCheck() {
		//This controls animation
		if (!activeKeys.contains(KeyCode.UP) && !activeKeys.contains(KeyCode.DOWN) &&
				!activeKeys.contains(KeyCode.D) && !activeKeys.contains(KeyCode.A)) {
			  player.stopRun();
		}
		
		//this controls movement
		/*
		if (activeKeys.contains(KeyCode.W)) {
			player.moveCommand("UP");
		} 
		*/
		if (activeKeys.contains(KeyCode.SPACE)) {
			player.moveCommand("SPACE");
		}
		/*
		if (activeKeys.contains(KeyCode.DOWN)) {
			player.moveCommand("DOWN");
		}
		*/
		if (activeKeys.contains(KeyCode.D)) {
			player.moveCommand("RIGHT");
		}
		if (activeKeys.contains(KeyCode.A)) {
			player.moveCommand("LEFT");
		}
		
		//This is inventory controls
		if (activeKeys.contains(KeyCode.I)) {
			player.openInventory();
		} else {
			player.closeInventory();
		}
	}
}

