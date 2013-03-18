package cyclist.view.tool.view;

import java.util.ArrayList;
import cyclist.view.component.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;


public class Foo extends View{
	double x = 0;
	double y = 0;
	double mousex = 0;
	double mousey = 0;
	
	public Foo(){
		super();
		setMinHeight(600);
		setMinWidth(600);
		init_1();
	}
	
	private void init_1(){
		ArrayList<Object> testing = new ArrayList<Object>();
		testing.add("Wee");
		if(testing.get(0) instanceof String){
			System.out.println("Awesome");
		}
		nodeLink Link1 = new nodeLink();
		Link1.source = "LWR";
		Link1.target = "Market";
		dataArrays.Links.add(Link1);
		final Pane pane = new Pane();
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #ee9121;");
		Button newNode = new Button();
		newNode.setText("Add New Facility Node");
		newNode.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){Nodes.addNode("Test");
			}
		});
		grid.add(newNode, 0, 0);
		Button newLink = new Button();
		newLink.setText("Add New Link");
		newLink.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				
			}
		});
		setContent(grid);
		pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				for(int i = 0; i < pane.getChildren().size(); i ++){
					if(event.getButton().equals(MouseButton.PRIMARY)){
						if(pane.getChildren().get(i).getStyleClass().toString() == "menu-bar"){
							pane.getChildren().get(i).setVisible(false);
						}
					}
				}
			}
		});
		Image testImg = new Image("reactor.png");
		final ImageView iv2 = new ImageView();
		iv2.setImage(testImg);
        iv2.setFitWidth(100);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
		iv2.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				x = iv2.getX() - event.getX();
				y = iv2.getY() - event.getY();
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		iv2.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				iv2.setX(mousex + x);
				iv2.setY(mousey + y);
				mousex = event.getX();
				mousey = event.getY();
			}
		});        
		pane.getChildren().addAll(iv2);
		
		final Text text = new Text("Testing");
		pane.setMinSize(600, 600);
		final Line line = new Line();
		final Line line2 = new Line();
		final Circle circle = new Circle();
		final Circle circle2 = new Circle();
		final Circle circle3 = new Circle();
		circle.setCenterX(200);
		circle.setCenterY(200);
		circle.setRadius(30);
		text.setX(circle.getCenterX()-circle.getRadius()*0.6);
		text.setY(circle.getCenterY());
		circle.setFill(Color.rgb(200,100,50));
		circle.setStroke(Color.BLACK);
		circle.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				double x1 = circle.getCenterX()-circle2.getCenterX();
				double y1 = circle.getCenterY()-circle2.getCenterY();				
				circle.setCenterX(mousex);
				circle.setCenterY(mousey);
				line.setStartX(circle.getCenterX());
				line.setStartY(circle.getCenterY());
				text.setX(circle.getCenterX()-circle.getRadius()*0.6);
				text.setY(circle.getCenterY());
				circle2.setCenterX(mousex - x1);
				circle2.setCenterY(mousey - y1);
				line.setEndX(circle2.getCenterX());
				line.setEndY(circle2.getCenterY());
				line2.setStartX(circle.getCenterX());
				line2.setStartY(circle.getCenterY());
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle2.setCenterX(200);
		circle2.setCenterY(100);
		circle2.setRadius(20);
		circle2.setFill(Color.rgb(240,120,60));
		pane.getChildren().addAll(line, line2, circle3, circle, text, circle2);
		circle2.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle2.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				circle2.setCenterX(mousex);
				circle2.setCenterY(mousey);
				line.setEndX(circle2.getCenterX());
				line.setEndY(circle2.getCenterY());
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle3.setCenterX(250);
		circle3.setCenterY(300);
		circle3.setRadius(30);
		circle3.setFill(Color.rgb(200,100,50));
		circle3.setStroke(Color.BLACK);
		circle3.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle3.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				circle3.setCenterX(mousex);
				circle3.setCenterY(mousey);
				line2.setEndX(circle3.getCenterX());
				line2.setEndY(circle3.getCenterY());
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		line.setStartX(circle.getCenterX());
		line.setStartY(circle.getCenterY());
		line.setEndX(circle2.getCenterX());
		line.setEndY(circle2.getCenterY());
		line2.setStartX(circle.getCenterX());
		line2.setStartY(circle.getCenterY());
		line2.setEndX(circle3.getCenterX());
		line2.setEndY(circle3.getCenterY());
		setContent(pane);
	}
}


