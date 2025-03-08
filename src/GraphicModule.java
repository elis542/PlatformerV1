
public class GraphicModule {
	private GameLevel level;
	
	public GraphicModule(GameLevel level) {
		this.level = level;
	}
	
	public void renderGraphics() {
		level.reDraw();
	}

}
