package LogicPackage;
import java.util.HashSet;
import java.util.Set;

import GameObjects.Player;
import Scenes.GameLevel;
import javafx.event.Event;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameControllModel {
	private Player player;
	private Set<KeyCode> activeKeys = new HashSet<>();
	private double[] mouseCordinate = new double[2];

	public GameControllModel(Player player) {
		this.player = player;
	} 

	public void mouseClicked(MouseEvent event) {
		if (event.getButton() == MouseButton.PRIMARY) {
			player.fire(event.getX(), event.getY());
		}
		if (event.getButton() == MouseButton.SECONDARY) {
			if (!player.getCurrentPlaceable()) {
				System.out.println("grab");
				player.grab(event.getX(), event.getY());
			} else {
				player.placePlaceable(event.getX(), event.getY());
				System.out.println("place");
			}
		}
	}
	
	public void mouseLocation(MouseEvent event) {
		mouseCordinate[0] = event.getX();
		mouseCordinate[1] = event.getY();
	}
	
	public double[] getMouseCordinates() {
		return mouseCordinate;
	}
	
	public void keyPressControl(GameLevel level) {
		level.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
		level.setOnKeyReleased(event -> {
			activeKeys.remove(event.getCode());
			
			if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.A) {
				player.stopRun();
			}
		});
	}

	public void keyPressCheck() {
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
		if (activeKeys.contains(KeyCode.TAB)) {
			player.openInventory();
		} else {
			player.closeInventory();
		}
	}
}

