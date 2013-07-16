package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import cyclist.view.component.View;

public class regionView extends View{
	public regionView(){
		super();
		
		final ListView<String> facilityList = new ListView<String>();
		facilityList.setOrientation(Orientation.VERTICAL);
		facilityList.setMinHeight(25);
		
		structureCB.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				structureCB.getItems().clear();
				for(int i = 0; i < dataArrays.regionNodes.size(); i++){
					structureCB.getItems().add((String) dataArrays.regionNodes.get(i).name);
				}
				structureCB.getItems().add("New Region");
			}
		});
		
		structureCB.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				if (newValue == null){
					
				} else if(newValue == "New Region"){
					grid.getChildren().clear();
					rowNumber = 0;
					dataArrays.regionNodes.add(new regionNode());
					workingRegion = dataArrays.regionNodes.get(dataArrays.regionNodes.size()-1);
					workingRegion.type = "GrowthRegion";
					workingRegion.regionStruct = (ArrayList<Object>) dataArrays.regionStructs.get(0);
					formBuilderFunctions.formArrayBuilder(workingRegion.regionStruct, workingRegion.regionData);
					formBuilder(workingRegion.regionStruct, workingRegion.regionData);
				} else {
					grid.getChildren().clear();
					facilityList.getItems().clear();
					workingRegion = dataArrays.regionNodes.get(structureCB.getItems().indexOf(newValue));
					for(int i = 0; i < workingRegion.availFacilities.size(); i++){
						facilityList.getItems().add(workingRegion.availFacilities.get(i));
					}
					formBuilder(workingRegion.regionStruct, workingRegion.regionData);
				}
			}
		});
		
		Button button = new Button();
		button.setText("Check Array");
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				System.out.println(workingRegion.regionData);
			}
		});
		
		
		topGrid.add(structureCB, 0, 0);
		topGrid.add(button, 2, 0);
		


		facilityList.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				if (event.isSecondaryButtonDown()){
					workingRegion.availFacilities.remove(facilityList.getSelectionModel().getSelectedItem());
					facilityList.getItems().remove(facilityList.getSelectionModel().getSelectedItem());
				}
			}
		});

		final ComboBox<String> addNewFacilityBox = new ComboBox<String>();
		addNewFacilityBox.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				addNewFacilityBox.getItems().clear();
				for (facilityCircle circle: dataArrays.FacilityNodes){
					for (facilityCircle child: circle.childrenList) {
						addNewFacilityBox.getItems().add((String)child.name);
					}
				}
			}
		});
		
		Button addAvailFac = new Button();
		addAvailFac.setText("Add Facility Prototype to Region");
		addAvailFac.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				facilityList.getItems().clear();
				for (String facility: workingRegion.availFacilities){
					facilityList.getItems().add(facility);
				}
				facilityList.getItems().add(addNewFacilityBox.getValue());
				workingRegion.availFacilities.add(addNewFacilityBox.getValue());
			}
		});
		
		topGrid.add(addNewFacilityBox, 0, 2);
		topGrid.add(addAvailFac, 1, 2);
		topGrid.setHgap(10);
		topGrid.setVgap(2);
		
		grid.autosize();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setVgap(10);
		grid.setHgap(5);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setStyle("-fx-background-color: Orange;");
				
		VBox regionSideBar = new VBox();
		regionSideBar.setPadding(new Insets(0, 5, 0, 0));
		regionSideBar.setMinWidth(100);
		regionSideBar.setPrefWidth(100);
		regionSideBar.getChildren().add(new Label("Available Facilities"));
		regionSideBar.getChildren().add(facilityList);
		
		VBox regionGridBox = new VBox();
		regionGridBox.getChildren().addAll(topGrid, grid);		
		
		HBox regionBox = new HBox();
		regionBox.getChildren().addAll(regionSideBar, regionGridBox);
		
		final ScrollPane sc = new ScrollPane();
		sc.setPrefSize(500, 500);
		sc.setContent(regionBox);
		sc.setStyle("-fx-background-color: Orange;");
		setContent(sc);
		
		/*setOnMouseDragged(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				sc.setPrefSize(getWidth(), getHeight());
			}
		});*/
		
		if (dataArrays.regionStructs.size() < 1) {
			practiceRegions.init();
		}
	}
	
	private ComboBox<String> structureCB = new ComboBox<String>();
	private GridPane grid = new GridPane();
	private GridPane topGrid = new GridPane();
	private facilityCircle formNode = null;
	private int rowNumber = 0;
	private int columnNumber = 0;
	private int columnEnd = 0;
	static regionNode workingRegion;
	/**
	 * This function takes a constructed data array and it's corresponding facility structure array and creates
	 * a form in for the structure and data array and facility structure.
	 * @param facArray This is the structure of the data array. Included in this array should be all of the information
	 * needed to fully describe the data structure of a facility.
	 * @param dataArray The empty data array that is associated with this facility. It should be built to match the structure
	 * of the facility structure passed to the form. 
	 */
	public void formBuilder(ArrayList<Object> facArray, ArrayList<Object> dataArray){
		for(int i = 0; i < dataArray.size(); i++){
			if (dataArray.get(i) instanceof ArrayList){
				if(facArray.size() > 2){
					if (facArray.get(2) == "oneOrMore" || facArray.get(2) == "zeroOrMore"){
						Label name = new Label((String) facArray.get(0));
						grid.add(name, columnNumber, rowNumber);
						grid.add(orMoreAddButton(grid, dataArray), 1+columnNumber, rowNumber);
						rowNumber += 1;
						// Indenting a sub structure
						columnNumber += 1;
						for(int ii = 0; ii < dataArray.size(); ii ++){
							if ( ii > 0 ) {
								grid.add(arrayListRemove(dataArray, ii), columnNumber-1, rowNumber);
							}
							formBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));						
						}
						// resetting the indent
						columnNumber -= 1;
						i = dataArray.size();
						
					} else if (facArray.get(2) == "input" || facArray.get(2) == "output") {
						Label name = new Label((String) facArray.get(0));
						grid.add(name, columnNumber, rowNumber);
						rowNumber += 1;
						// Indenting a sub structure
						columnNumber += 1;
						for(int ii = 0; ii < dataArray.size(); ii ++){
							formBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));						
						}
						// resetting the indent
						columnNumber -= 1;
						i = dataArray.size();
					} else {
						formBuilder((ArrayList<Object>)facArray.get(i), (ArrayList<Object>) dataArray.get(i));
					}
				} else {
					formBuilder((ArrayList<Object>)facArray.get(i), (ArrayList<Object>) dataArray.get(i));
				}
			} else {
				// Adding the label
				Label name = new Label((String) facArray.get(0));
				name.setTooltip(new Tooltip((String) facArray.get(7)));
				grid.add(name, columnNumber, rowNumber);
				// Setting up the input type for the label
				if (facArray.get(4) != null){
					// If statement to test for a continuous range for sliders.
					if (facArray.get(4).toString().split("[...]").length > 1){
						Slider slider = formBuilderFunctions.sliderBuilder(facArray.get(4).toString(), dataArray.get(0).toString());
						TextField textField = formBuilderFunctions.sliderTextFieldBuilder(slider, dataArray);
						grid.add(slider, 1+columnNumber, rowNumber);
						grid.add(textField, 2+columnNumber, rowNumber);
						columnEnd = 2+columnNumber+1;
					// Slider with discrete steps
					} else {
						ComboBox<String> cb = formBuilderFunctions.comboBoxBuilder(facArray.get(4).toString(), dataArray);
						grid.add(cb, 1+columnNumber, rowNumber);
						columnEnd = 2 + columnNumber;
					}
				} else {
					switch ((String) facArray.get(0)) {
					case "Name":
						grid.add(formBuilderFunctions.regionNameBuilder(workingRegion, dataArray), 1+columnNumber, rowNumber);
						columnEnd = 2 + columnNumber;
						break;
					default:
						grid.add(formBuilderFunctions.textFieldBuilder((ArrayList<Object>)dataArray), 1+columnNumber, rowNumber);
						columnEnd = 2 + columnNumber;
						break;
					}
				}
				grid.add(formBuilderFunctions.unitsBuilder((String)facArray.get(3)), columnEnd, rowNumber);
				columnEnd = 0;
				rowNumber += 1;
			}
		}
		rowNumber += 1;
	}
	
	/**
	 * 
	 * @param grid
	 * @param dataArray
	 * @return
	 */
	public Button orMoreAddButton(final GridPane grid, final ArrayList<Object> dataArray){
		Button button = new Button();
		button.setText("Add");
		
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
 				formBuilderFunctions.arrayListCopy(dataArray, (ArrayList<Object>) dataArray.get(0));
				grid.getChildren().clear();
				rowNumber = 0;
				formBuilder(workingRegion.regionStruct, workingRegion.regionData);
			}
		});
		return button;
	}
	
	public Button arrayListRemove(final ArrayList<Object> dataArray, final int dataArrayNumber){
		Button button = new Button();
		button.setText("Remove");
		
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				dataArray.remove(dataArrayNumber);
				grid.getChildren().clear();
				rowNumber = 0;
				formBuilder(workingRegion.regionStruct, workingRegion.regionData);
			}
		});		
		
		return button;
	}
}
