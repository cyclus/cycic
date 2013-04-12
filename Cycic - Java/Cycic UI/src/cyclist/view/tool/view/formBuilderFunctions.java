package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * This class is built to handle all of the functions used in building the forms for Cycic. 
 * @author Robert
 *
 */
public class formBuilderFunctions {
	
	/**
	 * A function used to create the text fields for simplistic inputs. 
	 * @param array This is a ArrayList of Objects that carries that structure required for the form. 
	 * @return An ArrayList of Objects containing the text field and labels for defining an input.
	 */
	static TextField textFieldBuilder(final ArrayList<Object> defaultValue){
		
		TextField textField = new TextField();
		textField.setText(defaultValue.get(0).toString());
		
		textField.textProperty().addListener(new ChangeListener<Object>(){         
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue){
				defaultValue.clear();
				defaultValue.add((String) newValue);
			}
		});
		
		return textField;
	}
	/**
	 * This function builds the slider for continuous ranges given a minimum and maximum. 
	 * @param string The string containing a range in the following format "min...max"
	 * @return Returns the slider created by the function.
	 */
	static Slider sliderBuilder(String string){
		
		final Slider slider = new Slider();
		
		slider.setMin(Double.parseDouble(string.split("[...]")[0]));
		slider.setMax(Double.parseDouble(string.split("[...]")[3]));
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(slider.getMax()/5);
		
		return slider;
	}
	/**
	 * A function to create a text field used to visualize a given slider. 
	 * @param slider The slider that the text field is used to describe.
	 * @return A text field used to interact with the slider. 
	 */
	static TextField sliderTextFieldBuilder(final Slider slider, final ArrayList<Object> defaultValue){
		
		final TextField textField = new TextField();
		
		textField.setText(String.format("%.2f", slider.getValue()));
		textField.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent action){
				if(Double.parseDouble(textField.getText()) <= slider.getMax() && Double.parseDouble(textField.getText()) >= slider.getMin()){
					slider.setValue(Double.parseDouble(textField.getText()));
				}
			}
		});
		textField.textProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				defaultValue.set(0, textField.getText());
			}
		});
		
		slider.valueProperty().addListener(new ChangeListener<Number>(){
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				textField.setText(String.format("%.2f", new_val));
			}
		});
		return textField;
	}
	
	/**
	 * Creates a ComboBox used to build the drop down menus for discrete value ranges or lists.
	 * @param string The string containing the discrete values in the form of "v1,v2,v3,v4,v5,...,vN"
	 * @return Returns a ComboBox to be used in the creation of the form. 
	 */
	static ComboBox<String> comboBoxBuilder(String string, final ArrayList<Object> defaultValue){
		
		final ComboBox<String> cb = new ComboBox<String>();
		
		for(String value : string.split(",")){
			cb.getItems().add(value.trim());
		}
		cb.setValue(defaultValue.get(0).toString());
		
		cb.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				defaultValue.set(0, cb.getValue());
			}
		});
		return cb;
	}
	
	/**
	 * 
	 * @param grid
	 * @param facStoreArray
	 */
	static void formSaveMethod(GridPane grid, ArrayList<Object> facStoreArray){
		for(int i = 0; i < grid.getChildren().size(); i++){
			if(grid.getChildren().get(i) instanceof TextField){
				facStoreArray.add(((TextField) grid.getChildren().get(i)).getText());
			}
			if(grid.getChildren().get(i) instanceof ComboBox){
				facStoreArray.add(((ComboBox<?>) grid.getChildren().get(i)).getValue());
			}
			if(grid.getChildren().get(i) instanceof Slider){
				facStoreArray.add(((Slider) grid.getChildren().get(i)).getValue());
				i++;
			}
		}
	}
	
	/**
	 * 
	 * @param units
	 * @return
	 */
	static Label unitsBuilder(String units){
		Label unitsLabel = new Label();
		
		unitsLabel.setText(units);
		
		return unitsLabel; 
	}
}
