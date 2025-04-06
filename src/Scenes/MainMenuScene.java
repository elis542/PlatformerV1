package Scenes;

import LogicPackage.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene extends BorderPane {
	private Stage primaryStage;
	private double scale = Main.getScale();
	
	public MainMenuScene(double width, double height, Stage primaryStage) {
		setWidth(width * scale);
		setHeight(height * scale);
		
		setPadding(new Insets(scale * 20, scale * 20, scale * 20, scale * 45)); // Top, Right, Bottom, Left
		
		this.primaryStage = primaryStage;
		VBox leftButtons = leftSideButtons();
		
		setLeft(leftButtons);
	}

	public VBox leftSideButtons() {
		VBox returnBox = new VBox(scale * 30);
		MyCanvasButton startGameButton = new MyCanvasButton(200, 200, "sprites/buttons/startGameButton.png");
		startGameButton.setOnMouseClicked(event -> {
			Scene newScene = new Scene(new SceneCreatorGameLevel());
			primaryStage.setScene(newScene);
		});
		
		MyCanvasButton chooseMapButton = new MyCanvasButton(200, 200, "sprites/buttons/chooseMapButton.png");
		chooseMapButton.setOnMouseClicked(event -> {
			Scene newScene = new Scene(new PickMapScene(getWidth(), getHeight(), primaryStage));
			primaryStage.setScene(newScene);
		});
		
		returnBox.getChildren().add(startGameButton);
		returnBox.getChildren().add(chooseMapButton);
		
		
		return returnBox;
	}

}
