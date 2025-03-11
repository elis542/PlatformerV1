package Scenes;
import LogicPackage.GameLoop;
import LogicPackage.Main;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SceneCreatorGameLevel extends HBox {
	private GameLevel currentLevel;
	
	public SceneCreatorGameLevel() {
		GameLevel currentLevel = new GameLevel(Main.getWidthAndHeight()[0], Main.getWidthAndHeight()[1]);
		this.currentLevel = currentLevel;
		getChildren().add(currentLevel);
		setHgrow(currentLevel, Priority.NEVER);
		setAlignment(Pos.CENTER);
		GameLoop gameLoop = new GameLoop(currentLevel);
		gameLoop.startGame();
	}
	
	public GameLevel getLevel() {
		return currentLevel;
	}

}
