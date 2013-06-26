package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import cyclist.view.component.View;

public class regionView extends View{
	public regionView(){
		super();
		structureCB.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				structureCB.getItems().clear();
				for(int i = 0; i < dataArrays.regionNodes.size(); i++){
					structureCB.getItems().add((String) dataArrays.regionNodes.get(i).get(0));
				}
				structureCB.getItems().add("New Growth Region");
			}
		});
		
		structureCB.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				if(newValue == "New Growth Region"){
					dataArrays.regionNodes.add(new ArrayList<Object>());
						formBuilderFunctions.formArrayBuilder((ArrayList<Object>) dataArrays.regionStructs.get(0), dataArrays.regionNodes.get(dataArrays.regionNodes.size()-1));
					formBuilder((ArrayList<Object>) dataArrays.regionStructs.get(0), dataArrays.regionNodes.get(dataArrays.regionNodes.size()-1));
				} else {
					formBuilder((ArrayList<Object>) dataArrays.regionStructs.get(0), dataArrays.regionNodes.get(structureCB.getItems().indexOf(newValue)));
				}
			}
		});
		
		Button button = new Button();
		button.setText("Check Array");
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				System.out.println(dataArrays.regionNodes.get(0));
			}
		});
		topGrid.add(structureCB, 0, 0);
		topGrid.add(button, 2, 0);
		grid.autosize();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setVgap(10);
		grid.setHgap(5);
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setStyle("-fx-background-color: Orange;");
		setContent(topGrid);
		setContent(grid);
	}
	
	private ComboBox<String> structureCB = new ComboBox<String>();
	private GridPane grid = new GridPane();
	private GridPane topGrid = new GridPane();
	private facilityCircle formNode = null;
	private int rowNumber = 0;
	private int columnNumber = 0;
	private int columnEnd = 0;
	
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
				}
				// Handling Zero/One or Mores
				else if(facArray.get(2) == "oneOrMore" || facArray.get(2) == "zeroOrMore"){
					Button button = new Button();
					button.setText("Add");
					grid.add(button, 1+columnNumber, rowNumber);
					columnNumber += 1;
				}
				// TextField Inputs
				else {
					grid.add(formBuilderFunctions.textFieldBuilder((ArrayList<Object>)dataArray), 1+columnNumber, rowNumber);
					columnEnd = 2 + columnNumber;
				}
				grid.add(formBuilderFunctions.unitsBuilder((String)facArray.get(3)), columnEnd, rowNumber);
				columnEnd = 0;
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
 				arrayListCopy(dataArray, (ArrayList<Object>) dataArray.get(0));
				grid.getChildren().clear();
				rowNumber = 0;
				formBuilder((ArrayList<Object>) dataArrays.regionStructs.get(0), dataArrays.regionNodes.get(dataArrays.regionNodes.size()-1));
			}
		});
		return button;
	}
	
	/**
	 * 
	 * @param baseList
	 * @param copyList
	 */
	public void arrayListCopy(ArrayList<Object> baseList, ArrayList<Object> copyList){
		ArrayList<Object> addedArray = new ArrayList<Object>();
		for(int i = 0; i < copyList.size(); i++){
			if(copyList.get(i) instanceof ArrayList){
				arrayListCopy(addedArray, (ArrayList<Object>)copyList.get(i));
			} else {
				addedArray.add(copyList.get(i));
			}
		}
		baseList.add(addedArray);
	}
}
