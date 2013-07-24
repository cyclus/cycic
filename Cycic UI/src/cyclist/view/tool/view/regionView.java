package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import cyclist.view.component.View;

/**
 * A View Class for generating the form required for interacting with a Cyclus region.
 * @author Robert
 *
 */
public class regionView extends View{
	/**
	 * Init function for this class. Generates the top grids and form gridpane. 
	 */
	public regionView(){
		super();
		// Facility list view for available facilities list of the region
		final ListView<String> facilityList = new ListView<String>();
		facilityList.setOrientation(Orientation.VERTICAL);
		facilityList.setMinHeight(25);
		facilityList.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				if (event.isSecondaryButtonDown()){
					workingRegion.availFacilities.remove(facilityList.getSelectionModel().getSelectedItem());
					facilityList.getItems().remove(facilityList.getSelectionModel().getSelectedItem());
				}
			}
		});
		//Institution list view for the region.
		final ListView<String> institList = new ListView<String>();
		institList.setOrientation(Orientation.VERTICAL);
		institList.setMinHeight(25);
		institList.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				if (event.isSecondaryButtonDown()){
					workingRegion.institutions.remove(institList.getSelectionModel().getSelectedItem());
					institList.getItems().remove(institList.getSelectionModel().getSelectedItem());
				}
			}
		});		
		// Selection for new or currently built regions.
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
			@SuppressWarnings("unchecked")
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
					rowNumber = 0;
					grid.getChildren().clear();
					facilityList.getItems().clear();
					workingRegion = dataArrays.regionNodes.get(structureCB.getItems().indexOf(newValue));
					for(int i = 0; i < workingRegion.availFacilities.size(); i++){
						facilityList.getItems().add(workingRegion.availFacilities.get(i));
					}
					for (int i = 0; i < workingRegion.institutions.size(); i++){
						institList.getItems().add(workingRegion.institutions.get(i));
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
		
		// Code section to add new facility to region.
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
				workingRegion.availFacilities.add(addNewFacilityBox.getValue());
				for (String facility: workingRegion.availFacilities){
					facilityList.getItems().add(facility);
				}
			}
		});
		
		// Code to add new institution to region.
		final ComboBox<String> addNewInstitBox = new ComboBox<String>();
		addNewInstitBox.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent event){
				addNewInstitBox.getItems().clear();
				for (instituteNode instit: dataArrays.institNodes){
					addNewInstitBox.getItems().add(instit.name);
				}
			}
		});
		
		Button addInstit = new Button();
		addInstit.setText("Add Institution");
		addInstit.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				institList.getItems().clear();
				workingRegion.institutions.add(addNewInstitBox.getValue());
				for (String instit: workingRegion.institutions){
					institList.getItems().add(instit);
				}
			}
		});
		
		// Setting up the view visuals.
		topGrid.add(addNewFacilityBox, 0, 2);
		topGrid.add(addAvailFac, 1, 2);
		topGrid.add(addNewInstitBox, 0, 3);
		topGrid.add(addInstit, 1, 3);
		topGrid.setHgap(10);
		topGrid.setVgap(2);
		
		grid.autosize();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setVgap(10);
		grid.setHgap(5);
		grid.setPadding(new Insets(5, 5, 5, 5));
		grid.setStyle("-fx-background-color: silver;");
				
		HBox regionSideBar = new HBox();
		VBox facilitiesBox = new VBox();
		facilitiesBox.getChildren().addAll(new Label("Allowed Facilities"), facilityList);
		VBox institBox = new VBox();
		institBox.getChildren().addAll(new Label("Institutions"), institList);
		regionSideBar.setPadding(new Insets(0, 5, 0, 0));
		regionSideBar.setMinWidth(200);
		regionSideBar.setPrefWidth(200);
		regionSideBar.getChildren().addAll(facilitiesBox, institBox);
		
		VBox regionGridBox = new VBox();
		regionGridBox.getChildren().addAll(topGrid, grid);		
		
		HBox regionBox = new HBox();
		regionBox.getChildren().addAll(regionSideBar, regionGridBox);
		
		final ScrollPane sc = new ScrollPane();
		sc.setPrefSize(500, 500);
		sc.setContent(regionBox);
		sc.setStyle("-fx-background-color: silver;");
		setContent(sc);
		setPrefSize(600,400);
		
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
	private int userLevel = 0;
	static regionNode workingRegion;
	/**
	 * This function takes a constructed data array and it's corresponding facility structure array and creates
	 * a form in for the structure and data array and facility structure.
	 * @param facArray This is the structure of the data array. Included in this array should be all of the information
	 * needed to fully describe the data structure of a facility.
	 * @param dataArray The empty data array that is associated with this facility. It should be built to match the structure
	 * of the facility structure passed to the form. 
	 */
	@SuppressWarnings("unchecked")
	public void formBuilder(ArrayList<Object> facArray, ArrayList<Object> dataArray){
		for (int i = 0; i < facArray.size(); i++){
			if (facArray.get(i) instanceof ArrayList && facArray.get(0) instanceof ArrayList) {
				formBuilder((ArrayList<Object>) facArray.get(i), (ArrayList<Object>) dataArray.get(i));
			} else if (i == 0){
				if (facArray.get(2) == "oneOrMore"){
					if ((int)facArray.get(6) <= userLevel && i == 0){
						Label name = new Label((String) facArray.get(0));
						grid.add(name, columnNumber, rowNumber);
						grid.add(orMoreAddButton(grid, (ArrayList<Object>) facArray, (ArrayList<Object>) dataArray), 1+columnNumber, rowNumber);
						rowNumber += 1;
						// Indenting a sub structure
						columnNumber += 1;
						for(int ii = 0; ii < dataArray.size(); ii ++){
							if ( ii > 0 ) {
								grid.add(arrayListRemove(dataArray, ii), columnNumber-1, rowNumber);
							}
							formBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));	
							rowNumber += 1;
						}
						// resetting the indent
						columnNumber -= 1;
					}
				} else if (facArray.get(2) == "zeroOrMore") {
					if ((int)facArray.get(6) <= userLevel && i == 0){
						Label name = new Label((String) facArray.get(0));
						grid.add(name, columnNumber, rowNumber);
						grid.add(orMoreAddButton(grid, (ArrayList<Object>) facArray, (ArrayList<Object>) dataArray), 1+columnNumber, rowNumber);
						rowNumber += 1;
						// Indenting a sub structure
						columnNumber += 1;
						for(int ii = 0; ii < dataArray.size(); ii ++){
							grid.add(arrayListRemove(dataArray, ii), columnNumber-1, rowNumber);
							formBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));	
							rowNumber += 1;
						}
						// resetting the indent
						columnNumber -= 1;
					}
				} else if (facArray.get(2) == "input" || facArray.get(2) == "output") {
					if ((int)facArray.get(6) <= userLevel){
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
						case "Incommodity":
							grid.add(formBuilderFunctions.comboBoxInCommod(formNode, dataArray), 1+columnNumber, rowNumber);
							break;
						case "Outcommodity":
							grid.add(formBuilderFunctions.comboBoxOutCommod(formNode, dataArray), 1+columnNumber, rowNumber);
							break;
						case "Recipe":
							grid.add(formBuilderFunctions.recipeComboBox(formNode, dataArray), 1+columnNumber, rowNumber);
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
		}
	}
	
	/**
	 * Function to add an orMore button to the form. This button allows the user to add additional fields to zeroOrMore or oneOrMore form inputs.
	 * @param grid This is the grid of the current view. 
	 * @param facArray The ArrayList<Object> used to make a copy of the one or more field. 
	 * @param dataArray The ArrayList<Object> the new orMore field will be added to.
	 * @return Button that will add the orMore field to the dataArray and reload the form.
	 */
	public Button orMoreAddButton(final GridPane grid, final ArrayList<Object> facArray,final ArrayList<Object> dataArray){
		Button button = new Button();
		button.setText("Add");
		
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
 				formBuilderFunctions.formArrayBuilder(facArray, (ArrayList<Object>) dataArray);
				grid.getChildren().clear();
				rowNumber = 0;
				formBuilder(workingRegion.regionStruct, workingRegion.regionData);
			}
		});
		return button;
	}

	/**
	 * This function removes a orMore that has been added to a particular field.
	 * @param dataArray The ArrayList<Object> containing the orMore field
	 * @param dataArrayNumber the index number of the orMore field that is to be removed.
	 * @return Button for executing the commands in this function.
	 */
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
