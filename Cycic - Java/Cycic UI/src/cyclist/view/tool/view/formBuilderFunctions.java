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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * This class is built to handle all of the functions used in building the forms for Cycic. 
 * @author Robert
 *
 */
public class formBuilderFunctions {
	/**
	 * This method is used to read and create the forms associated with each facility. 
	 * @param facArray This is the array object that contains the data structure for the facility. 
	 */
	public static void formArrayBuilder(ArrayList<Object> facArray, ArrayList<Object> dataArray){
		Boolean test = true;
		String defaultValue = "";
		for (int i = 0; i < facArray.size(); i++){
			if (facArray.get(i) instanceof ArrayList){
				test = false;
				dataArray.add(new ArrayList<Object>());
				formArrayBuilder((ArrayList<Object>)facArray.get(i), (ArrayList<Object>)dataArray.get(dataArray.size()-1));
			} else if (i == 0){
				defaultValue = (String) facArray.get(5);
			}
		}
		if (test == true) {
			if(facArray.get(5) != null){
				dataArray.add(defaultValue);
			} else {
				dataArray.add("");
			}
		}
	}
	
	/**
	 * 
	 * @param baseList
	 * @param copyList
	 */
	public static void arrayListCopy(ArrayList<Object> baseList, ArrayList<Object> copyList){
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
	 * Special function used for the name flag in a facility.
	 * @param node The facility node that is currently being worked on
	 * @return TextField that controls the input of this field. 
	 */
	static TextField nameFieldBuilder(final facilityCircle node){
		TextField textField = new TextField();
		textField.setText(node.name);
		
		textField.textProperty().addListener(new ChangeListener<String>(){         
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				node.name = (String) newValue;
				node.text.setText(newValue);
			}
		});
		
		return textField;
	}
	
	/***
	 * 
	 * @param node
	 * @return
	 */
	static TextField marketNameBuilder(final marketCircle node){
		TextField textField = new TextField();
		textField.setText(node.name);
		
		textField.textProperty().addListener(new ChangeListener<String>(){         
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				node.name = (String) newValue;
				node.text.setText(newValue);
			}
		});
		
		return textField;
	}
	
	/**
	 * This function builds the slider for continuous ranges given a minimum and maximum. 
	 * @param string The string containing a range in the following format "min...max"
	 * @return Returns the slider created by the function.
	 */
	static Slider sliderBuilder(String string, String defaultValue){
		
		final Slider slider = new Slider();
		
		slider.setMin(Double.parseDouble(string.split("[...]")[0]));
		slider.setMax(Double.parseDouble(string.split("[...]")[3]));
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(slider.getMax()/5);
		
		slider.setValue(Double.parseDouble(defaultValue));
		
		return slider;
	}
	
	/**
	 * A function to create a text field used to visualize a given slider. 
	 * @param slider The slider that the text field is used to describe.
	 * @return A text field used to interact with the slider. 
	 */
	static TextField sliderTextFieldBuilder(final Slider slider, final ArrayList<Object> defaultValue){
		
		final TextField textField = new TextField();
		
		textField.setText((String) defaultValue.get(0));
		
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
				if (slider.getMax() < 9000 && Double.parseDouble(newValue) > 9000){
					textField.setText("IT'S OVER 9000!!!!!");
				} else {
					defaultValue.set(0, textField.getText());
				}
			}
		});
		
		slider.valueProperty().addListener(new ChangeListener<Number>(){
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				textField.setText(String.format("%.2f", new_val));
				defaultValue.set(0, textField.getText());
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
	 * This applies the units for the forms. 
	 * @param units A string that contains the unit "type". Ex. "kg", "MW", etc. 
	 * @return returns a label with this string
	 */
	static Label unitsBuilder(String units){
		Label unitsLabel = new Label();
		
		unitsLabel.setText(units);
		
		return unitsLabel; 
	}

	/**
	 * 
	 * @param string
	 * @param defaultValue
	 * @return
	 */
	static ComboBox<String> comboBoxInCommod(final String string, final ArrayList<Object> defaultValue){
		
		final ComboBox<String> cb = new ComboBox<String>();
		
		cb.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				cb.getItems().clear();
				for (marketCircle circle: dataArrays.marketNodes){
					cb.getItems().add(circle.commodity);
				}
				cb.getItems().add("New Commodity");
			}
		});
		cb.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				String marketName = null;
				if (newValue == "New Commodity"){
					// Tell Commodity Window to add a new commodity 
				} else {
					defaultValue.set(0, newValue);
					for (marketCircle circle: dataArrays.marketNodes){
						if (newValue == circle.commodity){
							marketName = circle.name;
						}
					}
					for (int i = 0; i < dataArrays.Links.size(); i++){
						if (dataArrays.Links.get(i).source == string && dataArrays.Links.get(i).target == oldValue){
							dataArrays.Links.remove(i);
							i = i- 1;
						}
					}
					if (marketName != null){
						visFunctions.linkNodes(string, marketName);
					}
				}
			}
		});
		
		return cb;
	}
	
	/***
	 * 
	 * @param string
	 * @param defaultValue
	 * @return
	 */
	static ComboBox<String> comboBoxOutCommod(final String string, final ArrayList<Object> defaultValue){

		final ComboBox<String> cb = new ComboBox<String>();
				
		cb.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				cb.getItems().clear();
				for (marketCircle circle: dataArrays.marketNodes){
					cb.getItems().add(circle.commodity);
				}
				cb.getItems().add("New Commodity");
			}
		});
		cb.valueProperty().addListener(new ChangeListener<String>(){
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
				String marketName = null;
				String oldMarket = null;
				String parentName = null;
				String lazySpaceSaver = null;
				Boolean hiddenLinkTest = false;
				if (newValue == "New Commodity"){
					// Tell Commodity Window to add a new commodity 
				} else {
					for (marketCircle circle: dataArrays.marketNodes){
						if (newValue == circle.commodity){
							marketName = circle.name;
						}
						if (defaultValue.get(0) == circle.commodity) {
							oldMarket = circle.name;
						}
					}
					for (int j = 0; j < dataArrays.Links.size(); j++) {
						if (dataArrays.Links.get(j).source == string && dataArrays.Links.get(j).target == oldMarket){
							dataArrays.Links.remove(j);
							j -= 1;
						}
					}
					for (int i = 0; i < dataArrays.FacilityNodes.size(); i++){
						for ( int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size(); ii++){
							dataArrays.FacilityNodes.get(i).childrenList.get(ii).name = lazySpaceSaver;
							if (dataArrays.FacilityNodes.get(i).childrenList.get(ii).name == string) {
								parentName = dataArrays.FacilityNodes.get(i).name;
							}
							for (int j = 0; j < dataArrays.Links.size(); j++){
								if (dataArrays.Links.get(j).source == lazySpaceSaver && dataArrays.Links.get(j).target == oldMarket){
									hiddenLinkTest = true;
								}
							}
							visFunctions.hiddenLinkRemoval(parentName, oldMarket, hiddenLinkTest);
						}
					}
					if (marketName != null){
						visFunctions.linkNodes(string, marketName);
						defaultValue.set(0, newValue);
						visFunctions.reloadPane();
					}
					
				}
			}
		});

		return cb;
	}
	
}
