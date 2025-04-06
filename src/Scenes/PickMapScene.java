package Scenes;

import LogicPackage.Main;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PickMapScene extends BorderPane {
	private Stage primaryStage;
	private double scale = Main.getScale();
	private double amountMoved;
	private double dragStarted;
	
	public PickMapScene(double width, double height, Stage primaryStage) {
		setWidth(width * scale);
		setHeight(height * scale);
		
		setPadding(new Insets(scale * 300, scale * 20, scale * 35, scale * 20)); // Top, Right, Bottom, Left
		
		HBox maps = mapSelectorBox();
		
		MyCanvasButton backButton = new MyCanvasButton(250, 125, "sprites/buttons/backButton.png");
		backButton.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				Scene newScene = new Scene(new MainMenuScene(width, height, primaryStage));
				primaryStage.setScene(newScene);
			}

		});
		
		this.primaryStage = primaryStage;
		
		this.setMouseTransparent(false);
		
		this.setOnMousePressed(event -> {
			dragStarted = event.getX();
			event.consume();
		});
		
		this.setOnMouseDragged(event -> {
			amountMoved += event.getX() - dragStarted;
			
			if (amountMoved > scale * 20) {
				amountMoved = scale * 20;
			}
			
			dragStarted = event.getX();
			
			maps.setTranslateX(amountMoved);
			event.consume();
			
		});
		
		setLeft(maps);
		setBottom(backButton);
	}
	
	private HBox mapSelectorBox() {
		HBox returnBox = new HBox(scale * 50); 
		
		MyCanvasButton map1 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map1.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active1");
			}
		});
		
		MyCanvasButton map2 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map2.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active2");
			}
		});
		
		MyCanvasButton map3 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map3.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active3");
			}
		});
		
		MyCanvasButton map4 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map4.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active4");
			}
		});
		
		MyCanvasButton map5 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map5.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active5");
			}
		});
		
		MyCanvasButton map6 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map6.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active6");
			}
		});
		
		MyCanvasButton map7 = new MyCanvasButton(200, 200, "sprites/buttons/temp.png");
		map7.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				System.out.println("not active7");
			}
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
