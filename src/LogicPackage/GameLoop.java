package LogicPackage;
import Scenes.GameLevel;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameLoop extends AnimationTimer {
	private LogicModule logic;
	private GraphicModule graphic;
	
	public GameLoop(GameLevel level) {
		logic = new LogicModule(level);
		graphic = new GraphicModule(level);
		logicLoop.setCycleCount(Timeline.INDEFINITE);
	}
	
	Timeline logicLoop = new Timeline(
			new KeyFrame(Duration.millis(16), e -> {
				logic.logicModuleUpdate();
			}));
	
	@Override
	public void handle(long arg0) {
		graphic.renderGraphics();
	}
	
 	public void startGame() {
 		start();
 		logicLoop.playFromStart();
 	}
 	
 	public void stopGame() {
 		logicLoop.stop();
 	}
}
