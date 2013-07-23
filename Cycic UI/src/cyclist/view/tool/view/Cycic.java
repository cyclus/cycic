	package cyclist.view.tool.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import cyclist.view.component.View;

/**
 * Main View Class for CYCIC. This view contains the CYCIC Pane used for visualization and 
 * generates the data for the simulation. 
 * @author Robert
 *
 */
public class Cycic extends View{
	/**
	 * Function for building the CYCIC Pane and GridPane of this view. 
	 */
	public Cycic(){
		super();
		setMinHeight(600);
		setMinWidth(700);
		init();
	}
	
	static Pane pane = new Pane();
	static facilityCircle workingNode = null;
	static marketCircle workingMarket = null;
	
	/**
	 * Initiates the Pane and GridPane.
	 */
	private void init(){
		if (realFacs.alfredStructs.size() < 1){
			realFacs.init();
		}
		
		HBox cycicBox = new HBox();
		Cycic.pane.setId("cycicPane");
		Cycic.pane.setPrefSize(1000, 1000);
		Cycic.pane.setClip(getParent());
		Cycic.pane.setStyle("-fx-background-color: white;");
		
		// Temp Toolbar //
		final GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #d6d6d6;");
		grid.setHgap(10);
		grid.setVgap(5);
		
		// Adding a new Facility //
		Text scenetitle1 = new Text("Add Facility");
		scenetitle1.setFont(new Font(20));
		grid.add(scenetitle1, 0, 0);
		Label facName = new Label("Name");
		grid.add(facName, 1, 0);
		// Name Field
		final TextField facNameField = new TextField();
		grid.add(facNameField, 2, 0);
		// Facility Type
		final ComboBox<String> structureCB = new ComboBox<String>();
		for(int i = 0; i < realFacs.alfredStructs.size(); i++){
			structureCB.getItems().add((String) realFacs.alfredStructsNames.get(i));	
		}
		structureCB.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				structureCB.setValue(newValue);
			}
		});
		grid.add(structureCB, 3, 0);
		//Submit Button
		Button submit1 = new Button("Submit");
		submit1.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				Nodes.addNode(facNameField.getText());
				dataArrays.FacilityNodes.get(dataArrays.FacilityNodes.size()-1).facilityType = structureCB.getValue();
				for (int i = 0; i < realFacs.alfredStructs.size(); i++){
					if (realFacs.alfredStructsNames.get(i) == structureCB.getValue()){
						dataArrays.FacilityNodes.get(dataArrays.FacilityNodes.size()-1).facilityStructure = realFacs.alfredStructs.get(i);
					}
				}
				formBuilderFunctions.formArrayBuilder(
						dataArrays.FacilityNodes.get(dataArrays.FacilityNodes.size()-1).facilityStructure,
						dataArrays.FacilityNodes.get(dataArrays.FacilityNodes.size()-1).facilityData
						);
			}
		});	
		grid.add(submit1, 4, 0);
		
		// Adding a new Market
		Text scenetitle2 = new Text("Add Market");
		scenetitle2.setFont(new Font(20));
		grid.add(scenetitle2, 0, 1);
		Label markName = new Label("Name");
		grid.add(markName, 1, 1);
		// Name Field
		final TextField markNameField = new TextField();
		grid.add(markNameField, 2, 1);
		Button submit2 = new Button("Submit");
		submit2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				marketNodes.addMarket(markNameField.getText());
				Cycic.workingMarket = dataArrays.marketNodes.get(dataArrays.marketNodes.size() - 1);
			}
		});
		grid.add(submit2, 3, 1);
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
		
		// Prints the Cyclus input associated with this simulator. 
		Button output = new Button();
		output.setText("Print Output");
		output.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				outPut.output();
			}
		});
		grid.add(output, 0, 2);
		
		
		setContent(grid);
		cycicBox.getChildren().addAll(pane);
		setContent(cycicBox);

	}
}
