package cyclist.view.tool.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import cyclist.view.component.View;

public class commoditiesView extends View{
	public commoditiesView(){
		super();
		init();
		addNewCommod.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				addNewCommodity();
			}
		});
		addNewCommod.setText("Add New Commodity");
		setContent(commodGrid);
	}
	
	private static GridPane commodGrid = new GridPane();
	private static Button addNewCommod = new Button();
			
	public static void init(){
		commodGrid.getChildren().clear();
		for (int i = 0; i < dataArrays.CommoditiesList.size(); i++){
			TextField commodity = new TextField();
			commodity.setText(dataArrays.CommoditiesList.get(i).getText());
			commodGrid.add(commodity, 0, i );
			final int index = i;
			commodity.textProperty().addListener(new ChangeListener<String>(){
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
					dataArrays.CommoditiesList.get(index).setText(newValue);
				}
			});
			Button removeCommod = new Button();
			removeCommod.setText("Delete Commodity");
			removeCommod.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					dataArrays.CommoditiesList.remove(index);
					init();
				}
			});	
			commodGrid.add(removeCommod, 1, index);
		}
		commodGrid.add(addNewCommod, 0, dataArrays.CommoditiesList.size());
	}
	
	static public void addNewCommodity(){
		Label commodity = new Label();
		commodity.setText("");
		dataArrays.CommoditiesList.add(commodity);
		TextField newCommod = new TextField();
		newCommod.setText(dataArrays.CommoditiesList.get(dataArrays.CommoditiesList.size()-1).getText());
		newCommod.textProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				dataArrays.CommoditiesList.get(dataArrays.CommoditiesList.size()-1).setText(newValue);
			}
		});
		init();
	}
}
