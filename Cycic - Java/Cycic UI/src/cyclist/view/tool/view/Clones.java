package cyclist.view.tool.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import cyclist.model.vo.DnD;

public class Clones {
	protected static double mousey;
	protected static double mousex;
	protected static double x;
	protected static double y;
	
	static void addClone(String name, final String parent, Boolean parentChildShow) {
		final facilityCircle clone = new facilityCircle();
		clone.setId(name);
		clone.setRadius(25);
		clone.parent = parent;
		clone.type = "Child";
		clone.name = name;
		
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			if(dataArrays.FacilityNodes.get(i).getId() == parent){
				clone.parentIndex = i;
			}
		}
		clone.facilityStructure = dataArrays.FacilityNodes.get(clone.parentIndex).facilityStructure;
		
		dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.add(clone);
		clone.facTypeIndex = dataArrays.FacilityNodes.get(clone.parentIndex).facTypeIndex;
		clone.setCenterX(dataArrays.FacilityNodes.get(clone.parentIndex).getCenterX()+60);
		clone.setCenterY(dataArrays.FacilityNodes.get(clone.parentIndex).getCenterY()+60);
		clone.facilityType = dataArrays.FacilityNodes.get(clone.parentIndex).facilityType;
		clone.facilityData = dataArrays.FacilityNodes.get(clone.parentIndex).facilityData;
		
		// Setting the Fill Color //
		clone.rgbColor = visFunctions.stringToColor(parent);
		clone.rgbColor.set(0, (int)(clone.rgbColor.get(0)*visFunctions.colorMultiplierTest(clone.rgbColor.get(0))));
		clone.rgbColor.set(1, (int)(clone.rgbColor.get(1)*visFunctions.colorMultiplierTest(clone.rgbColor.get(1))));
		clone.rgbColor.set(2, (int)(clone.rgbColor.get(2)*visFunctions.colorMultiplierTest(clone.rgbColor.get(2))));
		clone.setFill(Color.rgb(clone.rgbColor.get(0), clone.rgbColor.get(1), clone.rgbColor.get(2)));
		
		// Setting font color for visibility //
		if(visFunctions.colorTest(clone.rgbColor) == true){
			clone.text.setFill(Color.WHITE);
		}else{
			clone.text.setFill(Color.BLACK);
		}
		clone.setStroke(Color.BLACK);
		
		// Adding the label and the menu //
		final Menu menu1 = new Menu(clone.getId());
		MenuItem facForm = new MenuItem("Facility Form");
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				removeClone(clone.getId(), parent);
			}
		});
		menu1.getItems().addAll(facForm, delete);
		clone.menu.getMenus().add(menu1);
		clone.menu.setLayoutX(clone.getCenterX());
		clone.menu.setLayoutY(clone.getCenterY());
		clone.menu.setVisible(false);
		clone.text.setText(name.toString());
		clone.text.setX(clone.getCenterX()-clone.getRadius()*0.6);
		clone.text.setY(clone.getCenterY());
		clone.text.setWrappingWidth(clone.getRadius()*1.6);
		
		// Adding the Parent Child Link //
		final nodeLink parentChild = new nodeLink();
		parentChild.source = parent;
		parentChild.target = name;
		parentChild.line.setStroke(Color.GRAY);
		parentChild.line.setStrokeWidth(1.5);
		parentChild.line.getStrokeDashArray().addAll(15d, 5d);
		parentChild.line.setStartX(dataArrays.FacilityNodes.get(clone.parentIndex).getCenterX());
		parentChild.line.setStartY(dataArrays.FacilityNodes.get(clone.parentIndex).getCenterY());
		parentChild.line.setEndX(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.size()-1).getCenterX());
		parentChild.line.setEndY(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.size()-1).getCenterY());
		
		// Mouse Interaction Functions //
		clone.setOnDragDetected(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.isShiftDown() == true){
					Dragboard db = clone.startDragAndDrop(TransferMode.COPY);
					ClipboardContent content = new ClipboardContent();
					content.put( DnD.TOOL_FORMAT, "formBuilder");
					db.setContent(content);
					event.consume();
				}
			}
		});
		
		clone.onMousePressedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				Cycic.workingNode = clone;
				x = clone.getCenterX() - event.getX();
				y = clone.getCenterY() - event.getY();
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		clone.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				clone.setCenterX(mousex+x);
				clone.setCenterY(mousey+y);
				if(clone.getCenterX() <= clone.getParent().getLayoutBounds().getMinX()+clone.getRadius()){
					clone.setCenterX(clone.getParent().getLayoutBounds().getMinX()+clone.getRadius());
				}
				if(clone.getCenterY() <= clone.getParent().getLayoutBounds().getMinY()+clone.getRadius()){
					clone.setCenterY(clone.getParent().getLayoutBounds().getMinY()+clone.getRadius());
				}
				if(clone.getCenterY() >= clone.getParent().getParent().getLayoutBounds().getMaxY()-clone.getRadius()-60){
					clone.setCenterY(clone.getParent().getParent().getLayoutBounds().getMaxY()-clone.getRadius()-60);
				}
				if(clone.getCenterX() >= clone.getParent().getParent().getLayoutBounds().getMaxX()-clone.getRadius()){
					clone.setCenterX(clone.getParent().getParent().getLayoutBounds().getMaxX()-clone.getRadius());
				}
				clone.menu.setLayoutX(clone.getCenterX());
				clone.menu.setLayoutY(clone.getCenterY());
				clone.text.setX(clone.getCenterX()-clone.getRadius()*0.6);
				clone.text.setY(clone.getCenterY());
				parentChild.line.setEndX(clone.getCenterX());
				parentChild.line.setEndY(clone.getCenterY());
				for(int i = 0; i < dataArrays.Links.size();i++){
					if(dataArrays.Links.get(i).source == clone.name){
						dataArrays.Links.get(i).line.setStartX(clone.getCenterX());
						dataArrays.Links.get(i).line.setStartY(clone.getCenterY());
					}
				}
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		clone.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.getButton().equals(MouseButton.SECONDARY)){
					clone.menu.setVisible(true);
				}
				for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
					dataArrays.FacilityNodes.get(i).clickIndex = 0;
				}
				if(event.getButton().equals(MouseButton.PRIMARY)){
					for(int i = 0; i < Cycic.pane.getChildren().size(); i++){
						Cycic.pane.getChildren().get(i).setEffect(null);	
					}
					clone.setEffect(visFunctions.colorAdjust);
				}
			}
		});
		
		// Used for tracking the objects //
		dataArrays.FacilityNodes.get(clone.parentIndex).childrenLinks.add(parentChild);	
		
		int childIndex = dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.size();
		if(parentChildShow == true){	
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(childIndex-1));
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(childIndex-1).menu);
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(childIndex-1).text);
			Cycic.pane.getChildren().add(parentChild.line);
			parentChild.line.toBack();
		}
	}
	
	static void removeClone(String name, String parent){
		int parentPass = 0;
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			if(dataArrays.FacilityNodes.get(i).getId() == parent){
				parentPass = i;
			}
		}
		for(int i = 0; i < dataArrays.Links.size(); i++){
			if(dataArrays.Links.get(i).source == name){
				dataArrays.Links.remove(i);
			}
		}
		for(int i = 0; i < dataArrays.FacilityNodes.get(parentPass).childrenList.size(); i++){
			if(dataArrays.FacilityNodes.get(parentPass).childrenList.get(i).getId() == name){
				dataArrays.FacilityNodes.get(parentPass).childrenList.remove(i);
				dataArrays.FacilityNodes.get(parentPass).childrenLinks.remove(i);
			}
		}
		visFunctions.reloadPane();
	}
}
