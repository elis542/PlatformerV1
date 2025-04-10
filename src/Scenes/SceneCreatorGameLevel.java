package Scenes;
import LogicPackage.GameLoop;
import LogicPackage.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneCreatorGameLevel extends StackPane {
	private GameLevel currentLevel;
	private double scale = Main.getScale();

	public SceneCreatorGameLevel(int level, Stage primaryStage) {
		currentLevel = chosenLevel(level);
		GameLoop gameLoop = new GameLoop(currentLevel);

		// Menyrad
		HBox menuButtons = new HBox();
		menuButtons.setPadding(new Insets(10 * scale, 10 * scale, 10 * scale, 10 * scale));
		menuButtons.setMinHeight(95 * scale);
		menuButtons.setMaxHeight(75 * scale);
		menuButtons.setStyle("-fx-background-color: rgba(153,153,28,1);");
		menuButtons.setAlignment(Pos.TOP_LEFT);
		menuButtons.setPickOnBounds(true);
		menuButtons.setMouseTransparent(false);

		MyCanvasButton exitButton = new MyCanvasButton(150, 75, "sprites/buttons/backButton.png", 5);
		exitButton.setPickOnBounds(true);
		exitButton.setMouseTransparent(false);
		exitButton.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				gameLoop.stopGame();
				Scene newScene = new Scene(new MainMenuScene(getWidth(), getHeight(), primaryStage));
				primaryStage.setScene(newScene);
			}
		});
		
		menuButtons.getChildren().add(exitButton);

		VBox topLayout = new VBox();
		topLayout.setAlignment(Pos.TOP_CENTER);
		topLayout.setPickOnBounds(false);
		topLayout.getChildren().add(menuButtons);

		this.getChildren().addAll(currentLevel, topLayout);

		gameLoop.startGame();
	}

	
	public GameLevel chosenLevel(int level) {
		GameLevel pickedLevel = null;
		if (level == 0) {
			pickedLevel = new GameLevel1(1600, 900);
			
		} else if (level == 1) {
			pickedLevel = new GameLevel1(1600, 900);
		}
		
		return pickedLevel;
	}

}
