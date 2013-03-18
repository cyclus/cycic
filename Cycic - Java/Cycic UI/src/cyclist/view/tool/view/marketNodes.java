package cyclist.view.tool.view;

import java.util.ArrayList;
import cyclist.model.vo.DnD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
		final facilityCircle circle = new facilityCircle();
		circle.setId(name);
		circle.setRadius(30);
		circle.setCenterX(40);
		circle.setCenterY(40);
		circle.childrenShow = true;
		circle.clickIndex = 0;
		circle.type = "Market";
		circle.text = new Text(name);
		circle.childrenShow = true;
		circle.text.setX(circle.getCenterX()-circle.getRadius()*0.6);
		circle.text.setY(circle.getCenterY());		
		// Setting the circle color //
		circle.setStroke(Color.BLACK);
		circle.rgbColor=visFunctions.stringToColor(circle.getId());
		circle.setFill(Color.rgb(circle.rgbColor.get(0), circle.rgbColor.get(1), circle.rgbColor.get(2)));
		// Setting font color for visibility //
		if(visFunctions.colorTest(circle.rgbColor) == true){
			circle.text.setFill(Color.WHITE);
		}else{
			circle.text.setFill(Color.WHITE);
		}
		final Menu menu1 = new Menu(circle.getId());
		MenuItem facForm = new MenuItem("Facility Form");
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
					content.put( DnD.TOOL_FORMAT, "testForm");
					db.setContent(content);
					event.consume();
				}
			}
		});
		circle.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				circle.childrenDeltaX.clear();
				circle.childrenDeltaY.clear();
				for(int i = 0; i < circle.childrenList.size(); i++){
					circle.childrenDeltaX.add(circle.getCenterX() - circle.childrenList.get(i).getCenterX());
					circle.childrenDeltaY.add(circle.getCenterY() - circle.childrenList.get(i).getCenterY());
				}
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
				if(circle.getCenterX() <= Cycic.pane.getLayoutBounds().getMinX()+circle.getRadius()){
					circle.setCenterX(Cycic.pane.getLayoutBounds().getMinX()+circle.getRadius());
				}
				if(circle.getCenterY() <= Cycic.pane.getLayoutBounds().getMinY()+circle.getRadius()){
					circle.setCenterY(Cycic.pane.getLayoutBounds().getMinY()+circle.getRadius());
				}
				if(circle.getCenterY() >= Cycic.pane.getLayoutBounds().getMaxY()-circle.getRadius()){
					circle.setCenterY(Cycic.pane.getLayoutBounds().getMaxY()-circle.getRadius());
				}
				if(circle.getCenterX() >= Cycic.pane.getLayoutBounds().getMaxX()-circle.getRadius()){
					circle.setCenterX(Cycic.pane.getLayoutBounds().getMaxX()-circle.getRadius());
				}
				circle.menu.setLayoutX(circle.getCenterX());
				circle.menu.setLayoutY(circle.getCenterY());
				circle.text.setX(circle.getCenterX()-circle.getRadius()*0.6);
				circle.text.setY(circle.getCenterY());
				for(int i = 0; i < dataArrays.Links.size(); i++){
					if(dataArrays.Links.get(i).target == circle.getId()){
						dataArrays.Links.get(i).line.setEndX(circle.getCenterX());
						dataArrays.Links.get(i).line.setEndY(circle.getCenterY());
					}
				}				
				for(int i = 0; i < dataArrays.hiddenLinks.size(); i++){
					if(dataArrays.hiddenLinks.get(i).target == circle.getId()){
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
				for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
					if(dataArrays.FacilityNodes.get(i).getId() != circle.getId()){
						dataArrays.FacilityNodes.get(i).clickIndex = 0;
					}
				}
			}
		});
		dataArrays.FacilityNodes.add(circle);
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
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			if(dataArrays.FacilityNodes.get(i).getId() == name){
				dataArrays.FacilityNodes.remove(i);
			}
		}
		visFunctions.reloadPane();
	}
}