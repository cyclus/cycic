package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * This class extends the Java Circle class. Used to represent the facility
 * on the CYCIC pane and contains all of the information assocaited with
 * the facility.
 * @author Robert
 *
 */
public class facilityCircle extends Circle {
	String facilityType = "";
	Integer facTypeIndex = 0;
	ArrayList<Object> facilityData = new ArrayList<Object>();
	ArrayList<Object> facilityStructure = new ArrayList<Object>();
	ArrayList<facilityCircle> childrenList = new ArrayList<facilityCircle>();
	ArrayList<nodeLink> childrenLinks = new ArrayList<nodeLink>();
	ArrayList<Double> childrenDeltaX = new ArrayList<Double>();
	ArrayList<Double> childrenDeltaY = new ArrayList<Double>();
	ArrayList<Integer> rgbColor = new ArrayList<Integer>();
	/*ArrayList<String> incommods = new ArrayList<String>();
	ArrayList<String> outcommods = new ArrayList<String>();*/
	MenuBar menu = new MenuBar();
	Text text = new Text();
	ImageView image = new ImageView();
	String type = new String();
	String parent = new String();
	Integer parentIndex;
	Object name; 
	Boolean childrenShow;
}
