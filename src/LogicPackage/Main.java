package LogicPackage;
import Scenes.SceneCreatorGameLevel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static double screenMultiplier = 100;
	private static double width = (16 * screenMultiplier);
	private static double height = (9 * screenMultiplier);
	private static double scaleAmount = 1.4;

	@Override
	public void start(Stage arg0) throws Exception {
		Stage primaryStage = new Stage();
		primaryStage.setWidth(width * scaleAmount); 
		primaryStage.setHeight(height * scaleAmount);
		primaryStage.setResizable(false);
		primaryStage.setTitle("My Game :)");
		
		SceneCreatorGameLevel gameHBox = new SceneCreatorGameLevel();
		
		primaryStage.setScene(new Scene(gameHBox));
		GameLoop gameLoop = new GameLoop(gameHBox.getLevel());
		gameLoop.startGame();
		
		primaryStage.show();
	}
	
	public static double[] getWidthAndHeight() {
		return new double[] {width, height};
	}
	
	public static double getScale() {
		return scaleAmount;
	}
}
