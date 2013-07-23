package cyclist.view.tool.view;

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
import cyclist.model.vo.DnD;

/**
 * The Clones class deals with all functions regarding generating and 
 * removing clones.
 * @author Robert
 *
 */
public class Clones {
	protected static double mousey;
	protected static double mousex;
	protected static double x;
	protected static double y;
	
	/**
	 * Builds a child facilityCircle and adds it to the parent facilityCircle's childrenList.
	 * @param name Name of the new child
	 * @param parent facilityCircle node the new child will be added to.
	 * @param parentChildShow Boolean to indicate whether or not to show the child when it is built.
	 */
	static void addClone(String name, final facilityCircle parent, Boolean parentChildShow) {
		// Building the facilityCircle and adding the basic information.
		final facilityCircle clone = new facilityCircle();
		clone.setId(name);
		clone.setRadius(25);
		clone.parent = (String) parent.name;
		clone.type = "Child";
		clone.name = name;
		
		// Providing the child with the index of its parent node in the dataArrays.facilityNodes ArrayList.
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			if(dataArrays.FacilityNodes.get(i) == parent){
				clone.parentIndex = i;
			}
		}
		// Copying important information from parent to child.
		clone.facilityStructure = dataArrays.FacilityNodes.get(clone.parentIndex).facilityStructure;
		parent.childrenList.add(clone);
		clone.facTypeIndex = parent.facTypeIndex;
		clone.setCenterX(parent.getCenterX()+60);
		clone.setCenterY(parent.getCenterY()+60);
		clone.facilityType = parent.facilityType;
		// Building the child's facility data ArrayList
		formBuilderFunctions.formArrayBuilder(clone.facilityStructure, clone.facilityData);
		
		// Setting the Fill Color //
		clone.rgbColor = visFunctions.stringToColor((String) parent.name);
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
		
		// Adding the facility menu //
		final Menu menu1 = new Menu((String) clone.name);
		MenuItem facForm = new MenuItem("Facility Form");
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				removeClone(clone, parent);
			}
		});
		menu1.getItems().addAll(facForm, delete);
		clone.menu.getMenus().add(menu1);
		clone.menu.setLayoutX(clone.getCenterX());
		clone.menu.setLayoutY(clone.getCenterY());
		clone.menu.setVisible(false);
		// Adding circle.text to be shown in the CYCIC pane.
		clone.text.setText(name.toString());
		clone.text.setX(clone.getCenterX()-clone.getRadius()*0.6);
		clone.text.setY(clone.getCenterY());
		clone.text.setWrappingWidth(clone.getRadius()*1.6);
		clone.text.setMouseTransparent(true);
		
		// Adding the Parent Child Link different from normal market links.//
		final nodeLink parentChild = new nodeLink();
		parentChild.source = parent;
		parentChild.target = name;
		parentChild.line.setStroke(Color.GRAY);
		parentChild.line.setStrokeWidth(1.5);
		parentChild.line.getStrokeDashArray().addAll(15d, 5d);
		parentChild.line.setStartX(parent.getCenterX());
		parentChild.line.setStartY(parent.getCenterY());
		parentChild.line.setEndX(parent.childrenList.get(parent.childrenList.size()-1).getCenterX());
		parentChild.line.setEndY(parent.childrenList.get(parent.childrenList.size()-1).getCenterY());
		
		// Mouse drag detection function for initiating the construction of a new view. 
		clone.setOnDragDetected(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.isShiftDown() == true){
					Dragboard db = clone.startDragAndDrop(TransferMode.COPY);
					ClipboardContent content = new ClipboardContent();
					content.put( DnD.TOOL_FORMAT, "Form Builder");
					db.setContent(content);
					event.consume();
				}
			}
		});
		
		// Recording informtaion for making the node move.
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
		// Handles the movement controls of the facilityNode.
		clone.onMouseDraggedProperty().set(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				clone.setCenterX(mousex+x);
				clone.setCenterY(mousey+y);
				
				if(clone.getCenterX() <= Cycic.pane.getLayoutBounds().getMinX()+clone.getRadius()){
					clone.setCenterX(Cycic.pane.getLayoutBounds().getMinX()+clone.getRadius());
				}
				if(clone.getCenterY() <= Cycic.pane.getLayoutBounds().getMinY()+clone.getRadius()){
					clone.setCenterY(Cycic.pane.getLayoutBounds().getMinY()+clone.getRadius());
				}
				if(clone.getCenterY() >= Cycic.pane.getLayoutBounds().getMaxY()-clone.getRadius()){
					clone.setCenterY(Cycic.pane.getLayoutBounds().getMaxY()-clone.getRadius());
				}
				if(clone.getCenterX() >= Cycic.pane.getLayoutBounds().getMaxX()-clone.getRadius()){
					clone.setCenterX(Cycic.pane.getLayoutBounds().getMaxX()-clone.getRadius());
				}
				clone.menu.setLayoutX(clone.getCenterX());
				clone.menu.setLayoutY(clone.getCenterY());
				clone.text.setX(clone.getCenterX()-clone.getRadius()*0.6);
				clone.text.setY(clone.getCenterY());
				parentChild.line.setEndX(clone.getCenterX());
				parentChild.line.setEndY(clone.getCenterY());
				for(int i = 0; i < dataArrays.Links.size();i++){
					if(dataArrays.Links.get(i).source == clone){
						dataArrays.Links.get(i).line.setStartX(clone.getCenterX());
						dataArrays.Links.get(i).line.setStartY(clone.getCenterY());
					}
				}
				mousex = event.getX();
				mousey = event.getY();
			}
		});
		// Double click test && menu handing.
		clone.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				if(event.getButton().equals(MouseButton.SECONDARY)){
					clone.menu.setVisible(true);
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
		parent.childrenLinks.add(parentChild);	
		
		// If applicable adding node to CYCIC pane.
		int childIndex = parent.childrenList.size();
		if(parentChildShow == true){	
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(childIndex-1));
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(childIndex-1).menu);
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(clone.parentIndex).childrenList.get(childIndex-1).text);
			Cycic.pane.getChildren().add(parentChild.line);
			parentChild.line.toBack();
		}
	}
	
	/**
	 * Function to completely remove a child facilityCircle from the simulation.
	 * @param child This is the name of the clone to be removed. 
	 * @param parent Parent of the clone being removed.
	 */
	static void removeClone(facilityCircle child, facilityCircle parent){
		for(int i = 0; i < dataArrays.Links.size(); i++){
			if(dataArrays.Links.get(i).source == child){
				dataArrays.Links.remove(i);
			}
		}
		for(int i = 0; i < parent.childrenList.size(); i++){
			if(parent.childrenList.get(i) == child){
				parent.childrenList.remove(i);
				parent.childrenLinks.remove(i);
			}
		}
		visFunctions.reloadPane();
	}
}
