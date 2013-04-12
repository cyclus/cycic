package cyclist.view.tool.view;


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
		for(int i =0; i < practiceFacs.structureNames.size(); i++){
			structureCB.getItems().add(practiceFacs.structureNames.get(i));
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
				Integer tempPass  = practiceFacs.structureNames.indexOf(structureCB.getValue());
				dataArrays.FacilityNodes.get(dataArrays.FacilityNodes.size()-1).facTypeIndex = tempPass;
				formBuilder.formArrayBuilder(
						practiceFacs.Structures.get(tempPass),
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
			}
		});
		grid.add(submit2, 2, 6);
		
		// Adding a new Link
		Text scenetitle1 = new Text("Link Stuff");
		scenetitle1.setFont(new Font(20));
		grid.add(scenetitle1, 0, 2);
		Label link1Name = new Label("Source");
		grid.add(link1Name, 0, 3);
		
		final ComboBox<String> source = new ComboBox<String>();
		source.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				source.getItems().clear();
				for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
					if(dataArrays.FacilityNodes.get(i).type == "Parent"){
						for(int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size(); ii++){
							source.getItems().add(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getId());
						}
					}
				}
				source.getItems().add("New Facility...");
			}
		});
		grid.add(source, 1, 3);
		
		final Label link2Name = new Label("Target");
		grid.add(link2Name, 0, 4);
		
		final ComboBox<String> target = new ComboBox<String>();
		target.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				target.getItems().clear();
				for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
					if(dataArrays.FacilityNodes.get(i).type == "Market"){
						target.getItems().add(dataArrays.FacilityNodes.get(i).getId());
					}
				}
				target.getItems().add("New Market...");
			}
		});
		grid.add(target, 1, 4);
		Button submit3 = new Button("Submit Link");
		submit3.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				visFunctions.linkNodes(source.getValue(), target.getValue());
			}
		});
		grid.add(submit3, 2, 4);
		
		Text scenetitle4 = new Text("Add a Clone");
		scenetitle4.setFont(new Font(20));
		grid.add(scenetitle4, 0, 7);
		
		final Label parentName = new Label("Parent");
		grid.add(parentName, 0, 8);
		
		final ComboBox<String> parentBox = new ComboBox<String>();
		parentBox.setOnMouseClicked(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				parentBox.getItems().clear();
				for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
					if(dataArrays.FacilityNodes.get(i).type == "Parent"){
						parentBox.getItems().add(dataArrays.FacilityNodes.get(i).getId());
					}
				}
			}
		});
		grid.add(parentBox, 1, 8);
		
		final Label cloneName = new Label("Clone Name");
		grid.add(cloneName, 0, 9);
		
		final TextField cloneNameField = new TextField();
		grid.add(cloneNameField, 1, 9);
		
		Button submit4 = new Button("Submit Clone");
		submit4.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				int parentPass = 0;
				for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
					if(dataArrays.FacilityNodes.get(i).getId() == parentBox.getValue()){
						parentPass = i;
					}
				}
				Clones.addClone(cloneNameField.getText(), dataArrays.FacilityNodes.get(parentPass).getId(), dataArrays.FacilityNodes.get(parentPass).childrenShow);
			}
		});
		grid.add(submit4, 2,9);
		setContent(grid);
	}
}