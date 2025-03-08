import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static double screenMultiplier = 125;
	private static double width = (16 * screenMultiplier);
	private static double height = (9 * screenMultiplier);

	@Override
	public void start(Stage arg0) throws Exception {
		Stage primaryStage = new Stage();
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);
		primaryStage.setTitle("My Game :)");
		
		SceneCreatorGameLevel gameVBox = new SceneCreatorGameLevel();
		
		primaryStage.setScene(new Scene(gameVBox));
		GameLoop gameLoop = new GameLoop(gameVBox.getLevel());
		gameLoop.startGame();
		
		primaryStage.show();
	}
	
	public static double getHeight() {
		return height;
	}
	
	public static double getWidth() {
		return width;
	}

}
