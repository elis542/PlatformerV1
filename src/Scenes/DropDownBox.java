package Scenes;

import javafx.scene.layout.VBox;


public class DropDownBox extends VBox {
	private String shownLabel;
	private MyCanvasButton currentChoice;
	private MyCanvasButton button1, button2, button3;

	
	public DropDownBox(double width, double height, MyCanvasMethod choice1, MyCanvasMethod choice2, MyCanvasMethod choice3, String[] labels) {
		setWidth(width); 
		setHeight(height);
		
		currentChoice = new MyCanvasButton(462, 102, "sprites/buttons/currentResButton.png", 5);
		
		button1 = new MyCanvasButton(462, 102, "sprites/buttons/currentResButton.png", 5);
		button1.addLabel(labels[0]);
		button1.setOnMouseClicked(event -> {
			choice1.method();
			currentChoice.addLabel(labels[0]);
		});
		
		button2 = new MyCanvasButton(462, 102, "sprites/buttons/currentResButton.png", 5);
		button2.addLabel(labels[1]);
		button2.setOnMouseClicked(event -> {
			choice2.method();
			currentChoice.addLabel(labels[1]);
		});
		
		button3 = new MyCanvasButton(462, 102, "sprites/buttons/currentResButton.png", 5);
		button3.addLabel(labels[2]);
		button3.setOnMouseClicked(event -> {
			choice3.method();
			currentChoice.addLabel(labels[2]);
		});
		
		currentChoice.setOnMouseClicked(event -> {
			event.consume();
			if (!getChildren().contains(button1)) {
				getChildren().addAll(button1, button2, button3);
			}
		});
		
		shownLabel = labels[1];
		
		currentChoice.addLabel(shownLabel);
		
		getChildren().add(currentChoice);
	}
	
	public DropDownBox(double width, double height, MyCanvasMethod choice1, MyCanvasMethod choice2, MyCanvasMethod choice3, String[] labels, String shownLabel) {
		this(width, height, choice1, choice2, choice3, labels);
		this.shownLabel = shownLabel;
		currentChoice.addLabel(shownLabel);
		
	}
	
	public void close() {
		getChildren().removeAll(button1, button2, button3);
	}
	
	
}
