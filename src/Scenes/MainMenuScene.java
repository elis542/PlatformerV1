package Scenes;

import LogicPackage.Main;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene extends BorderPane {
	private Stage primaryStage;
	private double scale = Main.getScale();
	
	public MainMenuScene(double width, double height, Stage primaryStage) {
		setWidth(width * scale);
		setHeight(height * scale);
		setStyle("-fx-background-color: linear-gradient(to bottom, #1f1c2c, #928dab);");
		
		setPadding(new Insets(scale * 20, scale * 20, scale * 20, scale * 45)); // Top, Right, Bottom, Left
	
		this.primaryStage = primaryStage;
		
		VBox leftButtons = leftSideButtons();
		HBox bottomButtons = bottomButtons();
		StackPane centerDisplay = centerDisplay("g", "g");
		
		setLeft(leftButtons);
		setCenter(centerDisplay);
		setBottom(bottomButtons);
		
	}
	
	public StackPane centerDisplay(String chosenMapString, String chosenWeaponString) {
		StackPane returnPane = new StackPane();
		
		HBox box = new HBox(25 * scale);
		
		box.setAlignment(Pos.CENTER);
		
		MyCanvasButton chosenMap = new MyCanvasButton(400 * scale, 400 * scale, "sprites/buttons/startGameButton.png");
		MyCanvasButton chosenWeapon = new MyCanvasButton(400 * scale, 400 * scale, "sprites/buttons/startGameButton.png");
		
		box.getChildren().add(chosenMap);
		box.getChildren().add(chosenWeapon);
		
		returnPane.getChildren().add(box);
		
		return returnPane;
	}
	
	public HBox bottomButtons() {
		HBox returnBox = new HBox(scale * 60);
		
		MyCanvasButton exitButton = new MyCanvasButton(150, 75, "sprites/buttons/exitButton.png", 5);
		exitButton.setOnMouseClicked(event -> {
			System.out.println("Currently inactive!");
		});
		
		MyCanvasButton settingsButton = new MyCanvasButton(150, 75, "sprites/buttons/settingsButton.png", 5);
		exitButton.setOnMouseClicked(event -> {
			System.out.println("Currently inactive!");
		});
		
		returnBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		
		returnBox.getChildren().add(exitButton);
		returnBox.getChildren().add(settingsButton);
		
		return returnBox;
	}

	public VBox leftSideButtons() {
		VBox returnBox = new VBox(scale * 60);
		MyCanvasButton startGameButton = new MyCanvasButton(200, 200, "sprites/buttons/startGameButton.png", 5);
		startGameButton.setOnMouseClicked(event -> {
			Scene newScene = new Scene(new SceneCreatorGameLevel(1, primaryStage));
			primaryStage.setScene(newScene);
		});
		
		MyCanvasButton chooseMapButton = new MyCanvasButton(200, 200, "sprites/buttons/chooseMapButton.png", 5);
		chooseMapButton.setOnMouseClicked(event -> {
			Scene newScene = new Scene(new PickMapScene(getWidth(), getHeight(), primaryStage));
			primaryStage.setScene(newScene);
		});
		
		MyCanvasButton chooseWeaponButton = new MyCanvasButton(200, 200, "sprites/buttons/chooseWeaponButton.png", 5);
		chooseWeaponButton.setOnMouseClicked(event -> {
			System.out.println("Currently inactive!");
		});
		
		returnBox.getChildren().add(startGameButton);
		returnBox.getChildren().add(chooseMapButton);
		returnBox.getChildren().add(chooseWeaponButton);	
		
		return returnBox;
	}

}
