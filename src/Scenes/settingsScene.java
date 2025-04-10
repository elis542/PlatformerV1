package Scenes;

import LogicPackage.Main;
import LogicPackage.Settings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class settingsScene extends BorderPane {
	private double scale = Main.getScale();
	private Stage primaryStage;
	private DoubleProperty scaleList = Main.getScaleListener();
	private VBox allSettingsSprite;
	private VBox allAdjusters;
	private HBox combination;
	private ChangeListener<Number> scaleListenerVar = (obs, oldV, newV) -> {
		updateLayout();
	};
	
	public settingsScene(double width, double height, Stage primaryStage) {
		setWidth(width * scale);
		setHeight(height * scale);
		setStyle("-fx-background-color: linear-gradient(to bottom, #1f1c2c, #928dab);");
		
		this.primaryStage = primaryStage;
		
		allSettingsSprite = settingsBoxCreator();
		allAdjusters = adjusterBoxCreator();
		combination = new HBox();
		
		scaleList.addListener(scaleListenerVar);
		
		MyCanvasButton exitButton = new MyCanvasButton(150, 75, "sprites/buttons/backButton.png", 5);
		exitButton.setOnMouseClicked(event -> {
			if (event.isStillSincePress()) {
				scaleList.removeListener(scaleListenerVar);
				Scene newScene = new Scene(new MainMenuScene(Main.getWidthAndHeight()[0], Main.getWidthAndHeight()[1], primaryStage));
				primaryStage.setScene(newScene);
			}
		});
		
		this.setOnMouseClicked(event -> {
			for (Node a : allAdjusters.getChildren()) {
				if (a instanceof DropDownBox) {
					((DropDownBox) a).close();
				}
			}
		});
		
		combination.getChildren().addAll(allSettingsSprite, allAdjusters);
		
		setCenter(combination);
		setBottom(exitButton);
	}
	
	private VBox settingsBoxCreator() {
		VBox returnBox = new VBox();
		returnBox.setPadding(new Insets(75 * scale, 10 * scale, 10 * scale, 350 * scale));
		MyCanvasButton resolutionButton = new MyCanvasButton(462, 102, "sprites/buttons/resolutionsprite.png");
		
		returnBox.getChildren().add(resolutionButton);
		
		return returnBox;
	}
	
	private VBox adjusterBoxCreator() {
		VBox returnBox = new VBox();
		returnBox.setPadding(new Insets(75 * scale, 10 * scale, 10 * scale, 0 * scale));// Top, Right, Bottom, Left
		returnBox.getChildren().add(createResolutionDropDownBox());
		
		return returnBox;
	}
	
	private DropDownBox createResolutionDropDownBox() {
		MyCanvasMethod method1 = () -> { //16x9 X 80 = 1280x720p 16x9 X 100 = 1600x900p, 16x9 X 120 = 1920x1080p
			Settings.setScale(80);
			primaryStage.setWidth(1280);
			primaryStage.setHeight(720);
		};
		
		MyCanvasMethod method2 = () -> {
			Settings.setScale(100);
			primaryStage.setWidth(1600);
			primaryStage.setHeight(900);
		};
		
		MyCanvasMethod method3 = () -> {
			Settings.setScale(120);
			primaryStage.setWidth(1920);
			primaryStage.setHeight(1080);
		};
		
		String[] labels = new String[]{"1280x720p", "1600x900p", "1920x1080p"};
		
		return new DropDownBox(getWidth(), getHeight(), method1, method2, method3, labels, Settings.getResToString());
	}
	
	private void updateLayout() {
	    combination.getChildren().removeAll(allSettingsSprite, allAdjusters);
		allSettingsSprite = settingsBoxCreator();
		allAdjusters = adjusterBoxCreator();
	    
	    MyCanvasButton exitButton = new MyCanvasButton(150, 75, "sprites/buttons/backButton.png", 5);
	    exitButton.setOnMouseClicked(event -> {
	        if (event.isStillSincePress()) {
	            scaleList.removeListener(scaleListenerVar);
	            Scene newScene = new Scene(new MainMenuScene(Main.getWidthAndHeight()[0], Main.getWidthAndHeight()[1], primaryStage));
	            primaryStage.setScene(newScene);
	        }
	    });

	    this.setOnMouseClicked(event -> {
	        for (Node a : this.allAdjusters.getChildren()) {
	            if (a instanceof DropDownBox) {
	                ((DropDownBox) a).close();
	            }
	        }
	    });

	    combination.getChildren().addAll(allSettingsSprite, allAdjusters);
	    setCenter(combination);
	    setBottom(exitButton);
	}
}
