package LogicPackage;
import Scenes.MainMenuScene;
import Scenes.SceneCreatorGameLevel;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static double width = (16 * Settings.getScale());
	private static double height = (9 * Settings.getScale());
	private static double scaleAmount = 1;
	private static DoubleProperty scaleListener = new SimpleDoubleProperty();

	@Override
	public void start(Stage arg0) throws Exception {
		Stage primaryStage = new Stage();
		primaryStage.setWidth(width * scaleAmount); 
		primaryStage.setHeight(height * scaleAmount);
		primaryStage.setResizable(false);
		primaryStage.setTitle("My Game :)");
		
		
		Scene newScene = new Scene(new MainMenuScene(width, height, primaryStage));
		primaryStage.setScene(newScene);
		
		primaryStage.show();
	}
	
	public static double[] getWidthAndHeight() {
		width = (16 * Settings.getScale());
		height = (9 * Settings.getScale());
		
		return new double[] {width, height};
	}
	
	public static double getScale() {
		return scaleAmount;
	}
	
	public static DoubleProperty getScaleListener() {
		return scaleListener;
	}
	
	public static void setScale(double scale) {
		scaleAmount = scale;
		scaleListener.set(scaleAmount);
	}
}
