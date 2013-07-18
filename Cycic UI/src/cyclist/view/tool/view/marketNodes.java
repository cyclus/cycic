package cyclist.view.tool.view;

import cyclist.model.vo.DnD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class marketNodes{

	protected static double mousey;
	protected static double mousex;
	protected static double x;
	protected static double y;
	
	static void addMarket(String name) {
		final marketCircle circle = new marketCircle();
		circle.setId(name);
		circle.setRadiusX(30);
		circle.setRadiusY(20);
		circle.setCenterX(40);
		circle.setCenterY(40);
		circle.text = new Text(name);
		circle.name = name;
		circle.text.setX(circle.getCenterX()-circle.text.getBoundsInLocal().getWidth()/2);
		circle.text.setY(circle.getCenterY());
		circle.text.setWrappingWidth(circle.getRadiusX()*1);
		circle.text.setMouseTransparent(true);
		
		// Setting the circle color //
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.rgb(100, 150, 200));
		circle.text.setFill(Color.WHITE);
		
		final Menu menu1 = new Menu(circle.getId());
		MenuItem facForm = new MenuItem("Market Form");
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				deleteMarket(circle.getId());
			}
		});
		menu1.getItems().addAll(facForm, delete);
		circle.menu.getMenus().add(menu1);
		circle.menu.setLayoutX(circle.getCenterX());
		circle.menu.setLayoutY(circle.getCenterY());
		circle.menu.setVisible(false);

		circle.setOnDragDetected(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.isShiftDown() == true){
					Dragboard db = circle.startDragAndDrop(TransferMode.COPY);
					ClipboardContent content = new ClipboardContent();
					content.put( DnD.TOOL_FORMAT, "Market Form");
					db.setContent(content);
					event.consume();
				}
			}
		});
		
		circle.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				Cycic.workingMarket = circle;
				x = circle.getCenterX() - event.getX();
				y = circle.getCenterY() - event.getY();
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				circle.setCenterX(mousex+x);
				circle.setCenterY(mousey+y);
				if(circle.getCenterX() <= Cycic.pane.getLayoutBounds().getMinX()+circle.getRadiusX()){
					circle.setCenterX(Cycic.pane.getLayoutBounds().getMinX()+circle.getRadiusX());
				}
				if(circle.getCenterX() >= Cycic.pane.getLayoutBounds().getMaxX()-circle.getRadiusX()){
					circle.setCenterX(Cycic.pane.getLayoutBounds().getMaxX()-circle.getRadiusX());
				}
				if(circle.getCenterY() <= Cycic.pane.getLayoutBounds().getMinY()+circle.getRadiusY()){
					circle.setCenterY(Cycic.pane.getLayoutBounds().getMinY()+circle.getRadiusY());
				}
				if(circle.getCenterY() >= Cycic.pane.getLayoutBounds().getMaxY()-circle.getRadiusY()){
					circle.setCenterY(Cycic.pane.getLayoutBounds().getMaxY()-circle.getRadiusY());
				}

				circle.menu.setLayoutX(circle.getCenterX());
				circle.menu.setLayoutY(circle.getCenterY());
				
				circle.text.setX(circle.getCenterX()-circle.text.getBoundsInLocal().getWidth()/2);
				circle.text.setY(circle.getCenterY());
				
				for(int i = 0; i < dataArrays.Links.size(); i++){
					if(dataArrays.Links.get(i).target == circle){
						dataArrays.Links.get(i).line.setEndX(circle.getCenterX());
						dataArrays.Links.get(i).line.setEndY(circle.getCenterY());
					}
				}				
				for(int i = 0; i < dataArrays.hiddenLinks.size(); i++){
					if(dataArrays.hiddenLinks.get(i).target == circle){
						dataArrays.hiddenLinks.get(i).line.setEndX(circle.getCenterX());
						dataArrays.hiddenLinks.get(i).line.setEndY(circle.getCenterY());
					}
				}
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		circle.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.getButton().equals(MouseButton.SECONDARY)){
					circle.menu.setVisible(true);
				}
			}
		});
		dataArrays.marketNodes.add(circle);
		Cycic.pane.getChildren().add(circle);
		Cycic.pane.getChildren().add(circle.menu);
		Cycic.pane.getChildren().add(circle.text);
	}

	static void deleteMarket(String name){
		for(int i = 0; i < dataArrays.Links.size(); i++){
			if(dataArrays.Links.get(i).target == name){
				dataArrays.Links.remove(i);
				i = i-1;
			}
		}
		for(int i = 0; i < dataArrays.marketNodes.size(); i++){
			if(dataArrays.marketNodes.get(i).getId() == name){
				dataArrays.marketNodes.remove(i);
			}
		}
		visFunctions.reloadPane();
	}
}