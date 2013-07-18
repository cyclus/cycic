package cyclist.view.tool.view;

import java.util.ArrayList;

import cyclist.view.component.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class marketView extends View{
	public marketView(){
		super();
		formNode = Cycic.workingMarket;
		
		/*formBuilder(practiceFacs.Structures.get(formNode.facTypeIndex), formNode.facilityData);*/
		if (formNode.marketStruct != null){
			marketFormBuilder(formNode.marketStruct, formNode.marketData);
		}
		
		Button button = new Button();
		button.setText("Check Array");
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				System.out.println(formNode.marketData);
				System.out.println(formNode.commodity);
			}
		});
		for(int i = 0; i < 11; i++){
			userLevelBox.getItems().add(String.format("%d", i));
		}
		userLevelBox.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				userLevelBox.setValue(newValue);
				userLevel = Integer.parseInt(newValue);
				grid.getChildren().clear();
				rowNumber = 0;
				marketFormBuilder(formNode.marketStruct, formNode.marketData);
			}
		});
		
		topGrid.add(formBuilderFunctions.marketNameBuilder(formNode), 0, 0);
		topGrid.add(new Label("User Level"), 1, 0);
		topGrid.add(userLevelBox, 2, 0);
		topGrid.add(button, 3, 0);
		
		Label commod = new Label("Market Commodity");
		topGrid.add(commod, 0, 1);
		
		final ComboBox<String> marketCommod = new ComboBox<String>();
		
		marketCommod.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				marketCommod.getItems().clear();
				for (Label commod: dataArrays.CommoditiesList){
					marketCommod.getItems().add(commod.getText());
				}
				marketCommod.getItems().add("Add New Commodity");
			}
		});
		marketCommod.valueProperty().addListener(new ChangeListener<String>(){         
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				if (marketCommod.getValue() != "Add New Commodity"){
					formNode.commodity = (String) newValue;
				} else {
					// maybe some stufff //
				}
			}
		});
		topGrid.add(marketCommod, 1, 1);
		
		topGrid.setPadding(new Insets(10, 10, 10, 10));
		topGrid.setHgap(10);
		topGrid.setVgap(10);
		
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setVgap(15);
		grid.setHgap(10);
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setStyle("-fx-background-color: silver;");
		
		VBox formGrid = new VBox();
		formGrid.getChildren().addAll(topGrid, grid);
		final ScrollPane sc = new ScrollPane();
		sc.setPrefSize(500, 500);
		sc.setStyle("-fx-background-color: silver;");
		sc.setContent(formGrid);
		setPrefSize(600,400);
		
		// This is a quick hack.
		sc.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				Cycic.workingMarket = formNode;
			}
		});
		
		setContent(sc);
	}
	
	private ComboBox<String> userLevelBox = new ComboBox<String>();
	private GridPane grid = new GridPane();
	private GridPane topGrid = new GridPane();
	private marketCircle formNode = null;
	private int rowNumber = 0;
	private int columnNumber = 0;
	private int columnEnd = 0;
	private int userLevel= 0;
	
	/**
	 * This function takes a constructed data array and it's corresponding facility structure array and creates
	 * a form in for the structure and data array and facility structure.
	 * @param facArray This is the structure of the data array. Included in this array should be all of the information
	 * needed to fully describe the data structure of a facility.
	 * @param dataArray The empty data array that is associated with this facility. It should be built to match the structure
	 * of the facility structure passed to the form. 
	 */
	@SuppressWarnings("unchecked")
	public void marketFormBuilder(ArrayList<Object> facArray, ArrayList<Object> dataArray){
		for (int i = 0; i < facArray.size(); i++){
			if (facArray.get(i) instanceof ArrayList && facArray.get(0) instanceof ArrayList) {
				marketFormBuilder((ArrayList<Object>) facArray.get(i), (ArrayList<Object>) dataArray.get(i));
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
							marketFormBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));	
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
							marketFormBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));	
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
							marketFormBuilder((ArrayList<Object>)facArray.get(1), (ArrayList<Object>) dataArray.get(ii));						
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
							grid.add(formBuilderFunctions.marketNameBuilder(formNode), 1+columnNumber, rowNumber);
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
		}
	}
	
	/**
	 * 
	 * @param grid
	 * @param dataArray
	 * @return
	 */
	public Button orMoreAddButton(final GridPane grid, final ArrayList<Object> facArray,final ArrayList<Object> dataArray){
		Button button = new Button();
		button.setText("Add");
		
		button.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
 				formBuilderFunctions.formArrayBuilder(facArray, (ArrayList<Object>) dataArray);
				grid.getChildren().clear();
				rowNumber = 0;
				marketFormBuilder(formNode.marketStruct, formNode.marketData);
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
				marketFormBuilder(formNode.marketStruct, formNode.marketData);
			}
		});		
		
		return button;
	}

}
