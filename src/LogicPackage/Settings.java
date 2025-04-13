package LogicPackage;

import java.text.DecimalFormat;

public class Settings {
	private static double scale = 100;
	private static double[] actualRes = new double[]{scale * 16, scale * 9};
	private static int selectedMap = 1;
	
	public static void setScale(double newScale) {
		actualRes[0] = newScale * 16;
		actualRes[1] = newScale * 9;
		
		scale = newScale;
		
		Main.setScale(newScale*0.01);
	}
	
	public static double getScale() {
		return scale;
	}
	
	public static void setSelectedMap(int value) {
		selectedMap = value;
	}
	
	public static int getSelectedMap() {
		return selectedMap;
	}
	
	public static String getSelectedMapSprite() {
		String mapNumber = "map" + selectedMap;
		return "sprites/buttons/" + mapNumber + ".png";
	}
	
	public static String getSelectedMapSpriteNumer(int number) {
		String mapNumber = "map" + number;
		return "sprites/buttons/" + mapNumber + ".png";
	}
	
	public static String getResToString() {
		DecimalFormat format = new DecimalFormat("0.#");
		String res1 = new String((format.format(actualRes[0])));
		String res2 = new String((format.format(actualRes[1])));
		
		return res1 + "x" + res2 + "p";
	}
}
