package Scenes;

import LogicPackage.Main;
import LogicPackage.Settings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PickMapScene extends BorderPane {
	private Stage primaryStage;
	private double scale = Main.getScale();
	private double amountMoved;
	private double dragStarted;
	
	public PickMapScene(double width, double height, Stage primaryStage) {
		setWidth(width * scale);
		setHeight(height * scale);
		
		setPadding(new Insets(scale * 300, scale * 20, scale * 35, scale * 20)); // Top, Right, Bottom, Left
		
		this.primaryStage = primaryStage;
		
		HBox maps = mapSelectorBox();
		
		MyCanvasButton backButton = backButtonCreation();
		
		setMouseTransparent(false);
		
		setOnMousePressed(event -> {
			dragStarted = event.getX();
			event.consume();
		});
		
		setOnMouseDragged(event -> {
			amountMoved += event.getX() - dragStarted;
			
			if (amountMoved > scale * 20) {
				amountMoved = scale * 20;
			}
			
			dragStarted = event.getX();
			
			maps.setTranslateX(amountMoved);
			event.consume();
			
		});
		
		setOnScroll(event -> {
			    amountMoved += event.getDeltaY();
				
				if (amountMoved > scale * 20) {
					amountMoved = scale * 20;
				}
				
				maps.setTranslateX(amountMoved);
		});
		
		setLeft(maps);
		setBottom(backButton);
	}
	
	private HBox mapSelectorBox() {
		HBox returnBox = new HBox(scale * 50); 
		
		MyCanvasButton map1 = new MyCanvasButton(200, 200, Settings.getSelectedMapSpriteNumer(1), 5);
		map1.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				Settings.setSelectedMap(1);
				goToMainMenu();
			}
		});
		
		MyCanvasButton map2 = new MyCanvasButton(200, 200, Settings.getSelectedMapSpriteNumer(2), 5);
		map2.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				Settings.setSelectedMap(2);
				goToMainMenu();
			}
		});
		
		MyCanvasButton map3 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png", 5);
		map3.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active3");
			}
		});
		
		MyCanvasButton map4 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png", 5);
		map4.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active4");
			}
		});
		
		MyCanvasButton map5 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png", 5);
		map5.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active5");
			}
		});
		
		MyCanvasButton map6 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png", 5);
		map6.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active6");
			}
		});
		
		MyCanvasButton map7 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png", 5);
		map7.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active7");
			}
		});
		
		
		
		returnBox.getChildren().add(map1);
		returnBox.getChildren().add(map2);
		returnBox.getChildren().add(map3);
		returnBox.getChildren().add(map4);
		returnBox.getChildren().add(map5);
		returnBox.getChildren().add(map6);
		returnBox.getChildren().add(map7);
		
		return returnBox;
	}
	
	private void goToMainMenu() {
		Scene newScene = new Scene(new MainMenuScene(getWidth(), getHeight(), primaryStage));
		primaryStage.setScene(newScene);
	}
	
	private MyCanvasButton backButtonCreation() { 
		MyCanvasButton returnButton = new MyCanvasButton(250, 125, "sprites/buttons/backButton.png", 5);
		returnButton.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				goToMainMenu();
			}
		});
		
		return returnButton;
	}


}
