import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class GameLoop extends AnimationTimer {
	private LogicModule logic;
	private GraphicModule graphic;
	
	public GameLoop(GameLevel level) {
		logic = new LogicModule(level);
		graphic = new GraphicModule(level);
	}
	
	@Override
	public void handle(long arg0) {
		logic.logicModuleUpdate();
		graphic.renderGraphics();
	}
	
 	public void startGame() {
 		start();
 	}
}
