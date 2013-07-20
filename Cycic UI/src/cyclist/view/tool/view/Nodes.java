package cyclist.view.tool.view;

import cyclist.model.vo.DnD;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Nodes{

	protected static double mousey;
	protected static double mousex;
	protected static double x;
	protected static double y;
	
	static void addNode(String name) {
		final facilityCircle circle = new facilityCircle();
		circle.setId(name);
		circle.setRadius(30);
		circle.setCenterX(40);
		circle.setCenterY(40);
		circle.type = "Parent";
		circle.childrenShow = true;
		circle.text = new Text(name);
		circle.name = name;
		circle.text.setX(circle.getCenterX()-circle.getRadius()*0.6);
		circle.text.setY(circle.getCenterY());	
		circle.text.setWrappingWidth(circle.getRadius()*1.6);
		circle.text.setMouseTransparent(true);
		
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
		
		final Menu menu1 = new Menu("Options");
		MenuItem facForm = new MenuItem("Facility Form");
		MenuItem delete = new MenuItem("Delete");
		
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				deleteNode(circle);
			}
		});
		
		final Menu clonesList = new Menu("Children");
		
		CustomMenuItem cloneNode = new CustomMenuItem(new Label("Add Child"));
		cloneNode.setHideOnClick(false);
		cloneNode.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				Clones.addClone("", circle, circle.childrenShow);
			}
		});
		
		clonesList.getItems().add(cloneNode);
		
		MenuItem showImage = new MenuItem("Show Image");
		showImage.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				circle.image.setVisible(true);
				circle.image.toBack();
				circle.setOpacity(0);			}
		});
		
		MenuItem hideImage = new MenuItem("Hide Image");
		hideImage.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				circle.image.setVisible(false);
				circle.setOpacity(100);
			}
		});
		
		menu1.getItems().addAll(facForm, clonesList, delete, showImage, hideImage);
		circle.menu.getMenus().add(menu1);
		circle.menu.setLayoutX(circle.getCenterX());
		circle.menu.setLayoutY(circle.getCenterY());
		circle.menu.setVisible(false);
		
		circle.image.setImage(new Image("reactor.png"));
		circle.image.setLayoutX(circle.getCenterX()-60);
		circle.image.setLayoutY(circle.getCenterY()-60);
		circle.image.setMouseTransparent(true);
		circle.image.setVisible(false);

		circle.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				Cycic.workingNode = circle;
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
				
				circle.image.setLayoutX(circle.getCenterX()-60);
				circle.image.setLayoutY(circle.getCenterY()-50);
				
				circle.text.setX(circle.getCenterX()-circle.getRadius()*0.6);
				circle.text.setY(circle.getCenterY());
				
				for(int i = 0; i < dataArrays.Links.size(); i++){
					if(dataArrays.Links.get(i).source == circle){
						dataArrays.Links.get(i).line.setStartX(circle.getCenterX());
						dataArrays.Links.get(i).line.setStartY(circle.getCenterY());
					}
				}
				for(int i = 0; i < dataArrays.hiddenLinks.size(); i++){
					if(dataArrays.hiddenLinks.get(i).source == circle){
						dataArrays.hiddenLinks.get(i).line.setStartX(circle.getCenterX());
						dataArrays.hiddenLinks.get(i).line.setStartY(circle.getCenterY());
					}
				}
				for(int i = 0; i < circle.childrenLinks.size(); i++){
					circle.childrenLinks.get(i).line.setStartX(circle.getCenterX());
					circle.childrenLinks.get(i).line.setStartY(circle.getCenterY());
					circle.childrenList.get(i).setCenterX(mousex-circle.childrenDeltaX.get(i)+x);
					circle.childrenList.get(i).setCenterY(mousey-circle.childrenDeltaY.get(i)+y);
					circle.childrenLinks.get(i).line.setEndX(circle.childrenList.get(i).getCenterX());
					circle.childrenLinks.get(i).line.setEndY(circle.childrenList.get(i).getCenterY());
					circle.childrenList.get(i).menu.setLayoutX(circle.childrenList.get(i).getCenterX());
					circle.childrenList.get(i).menu.setLayoutY(circle.childrenList.get(i).getCenterY());
					circle.childrenList.get(i).text.setX(circle.childrenList.get(i).getCenterX()-circle.childrenList.get(i).getRadius()*0.6);
					circle.childrenList.get(i).text.setY(circle.childrenList.get(i).getCenterY());
					for(int ii = 0; ii < dataArrays.Links.size(); ii++){
						if(circle.childrenList.get(i) == dataArrays.Links.get(ii).source){
							dataArrays.Links.get(ii).line.setStartX(circle.childrenList.get(i).getCenterX());
							dataArrays.Links.get(ii).line.setStartY(circle.childrenList.get(i).getCenterY());
						}
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
				if (event.getClickCount() >= 2){
					if(circle.childrenShow == true){
						circle.childrenShow = false;
						visFunctions.reloadPane();
					}else{
						circle.childrenShow = true;
						visFunctions.reloadPane();
					}
				}
				if(event.getButton().equals(MouseButton.PRIMARY)){
					for(int i = 0; i < Cycic.pane.getChildren().size(); i++){
						Cycic.pane.getChildren().get(i).setEffect(null);	
					}
					circle.setEffect(visFunctions.colorAdjust);
				}
			}
		});
		
		dataArrays.FacilityNodes.add(circle);
		
		circle.setOnDragDetected(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.isShiftDown() == true){
					Dragboard db = circle.startDragAndDrop(TransferMode.COPY);
					ClipboardContent content = new ClipboardContent();
					content.put( DnD.TOOL_FORMAT, "Form Builder");
					db.setContent(content);
					event.consume();
				}
			}
		});
		
		Cycic.pane.getChildren().add(circle);
		Cycic.pane.getChildren().add(circle.menu);
		Cycic.pane.getChildren().add(circle.text);
		Cycic.pane.getChildren().add(circle.image);
		circle.image.toBack();
	}
	
	/**
	 * 
	 * @param name
	 */
	static void deleteNode(facilityCircle circle){
		for(int i = 0; i < dataArrays.Links.size(); i++){
			if(dataArrays.Links.get(i).source == circle){
				dataArrays.Links.remove(i);
			}
		}
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			if(dataArrays.FacilityNodes.get(i) == circle){
				for(int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size();ii++){
					for(int iii = 0; iii < dataArrays.Links.size(); iii++){
						if(dataArrays.Links.get(i).source == dataArrays.FacilityNodes.get(i).childrenList.get(iii)){
							dataArrays.Links.remove(i);
						}
					}
				}
				dataArrays.FacilityNodes.remove(i);
			}
		}
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			for(int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size(); ii++){
				dataArrays.FacilityNodes.get(i).childrenList.get(ii).parentIndex = i;
			}
		}
		visFunctions.reloadPane();
	}
}