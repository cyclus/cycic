package cyclist.view.tool.view;


import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import cyclist.view.component.View;

public class testForm extends View {
	public testForm(){
		super();
		init();
	}

	public void init(){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		Text scenetitle = new Text("Form Stuff");
		scenetitle.setFont(new Font(20));
		grid.add(scenetitle, 0, 0);
		
		// Adding a new Facility //
		Label facName = new Label("Facility Name");
		grid.add(facName, 0, 1);
		
		final TextField facNameField = new TextField();
		grid.add(facNameField, 1, 1);
		
		final ComboBox<String> structureCB = new ComboBox<String>();

		for(int i = 0; i < realFacs.alfredStructs.size(); i++){
			structureCB.getItems().add((String) realFacs.alfredStructsNames.get(i));	
		}
		
		structureCB.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				structureCB.setValue(newValue);
			}
		});
		grid.add(structureCB, 2, 1);
		
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
		
		grid.add(submit1, 3, 1);
		
		// Adding a new Market
		Text scenetitle3 = new Text("Market Stuff");
		scenetitle3.setFont(new Font(20));
		grid.add(scenetitle3, 0, 5);
		Label markName = new Label("Market Name");
		grid.add(markName, 0, 6);
		
		final TextField markNameField = new TextField();
		grid.add(markNameField, 1, 6);
		Button submit2 = new Button("Submit");
		submit2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				marketNodes.addMarket(markNameField.getText());
				Cycic.workingMarket = dataArrays.marketNodes.get(dataArrays.marketNodes.size() - 1);
			}
		});
		grid.add(submit2, 2, 6);
		
		
		setContent(grid);
	}
}