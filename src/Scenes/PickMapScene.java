package Scenes;

import LogicPackage.Main;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PickMapScene extends BorderPane {
	private Stage primaryStage;
	private double scale = Main.getScale();
	private int amountMoved = 35;
	
	public PickMapScene(double width, double height, Stage primaryStage) {
		setWidth(width * scale);
		setHeight(height * scale);
		
		setPadding(new Insets(scale * 300, scale * 20, scale * 20, amountMoved)); // Top, Right, Bottom, Left
		
		this.primaryStage = primaryStage;
		
		this.setMouseTransparent(false);
		this.setOnDragDetected(event -> {
			double startPoint = event.getX();
		});
		
		HBox maps = mapSelectorBox();
		
		setLeft(maps);
	}
	
	private HBox mapSelectorBox() {
		HBox returnBox = new HBox(scale * 50); 
		
		MyCanvasButton map1 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		MyCanvasButton map2 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		MyCanvasButton map3 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		MyCanvasButton map4 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		MyCanvasButton map5 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		MyCanvasButton map6 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		MyCanvasButton map7 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			System.out.println("not active");
		});
		
		
		
		returnBox.getChildren().add(map1);
		returnBox.getChildren().add(map2);
		returnBox.getChildren().add(map3);
		returnBox.getChildren().add(map4);
		returnBox.getChildren().add(map5);
		returnBox.getChildren().add(map6);
		returnBox.getChildren().add(map7);
		
		return returnBox;
	}
	

}
