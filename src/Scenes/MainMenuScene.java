package Scenes;

import LogicPackage.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene extends BorderPane {
	private Stage primaryStage;
	
	public MainMenuScene(double width, double height, Stage primaryStage) {
		setWidth(width * Main.getScale());
		setHeight(height * Main.getScale());
		
		this.primaryStage = primaryStage;
		VBox leftButtons = leftSideButtons();
		
		setLeft(leftButtons);
	}
	
	public VBox leftSideButtons() {
		VBox returnBox = new VBox();
		MyCanvasButton startGameButton = new MyCanvasButton(200, 200, "sprites/buttons/startGameButton.png");
		startGameButton.setOnMouseClicked(event -> {
			Scene newScene = new Scene(new SceneCreatorGameLevel());
			primaryStage.setScene(newScene);
		});
		
		
		returnBox.getChildren().add(startGameButton);
		
		
		return returnBox;
	}

}
