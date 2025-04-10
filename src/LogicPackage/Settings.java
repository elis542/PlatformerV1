package LogicPackage;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Settings {
	private static double scale = 100;
	private static double[] actualRes = new double[]{scale * 16, scale * 9};
	
	public static void setScale(double newScale) {
		actualRes[0] = newScale * 16;
		actualRes[1] = newScale * 9;
		
		scale = newScale;
		
		Main.setScale(newScale*0.01);
	}
	
	public static double getScale() {
		return scale;
	}
	
	public static String getResToString() {
		DecimalFormat format = new DecimalFormat("0.#");
		String res1 = new String((format.format(actualRes[0])));
		String res2 = new String((format.format(actualRes[1])));
		
		return res1 + "x" + res2 + "p";
	}
}
