import javafx.scene.layout.HBox;

public class SceneCreatorGameLevel extends HBox {
	private GameLevel currentLevel;
	
	public SceneCreatorGameLevel() {
		GameLevel currentLevel = new GameLevel(Main.getWidth(), Main.getHeight());
		this.currentLevel = currentLevel;
		getChildren().add(currentLevel);
	}
	
	public GameLevel getLevel() {
		return currentLevel;
	}

}
