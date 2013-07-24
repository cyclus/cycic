package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.MenuBar;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

/**
 * Class used by CYCIC that expands the Ellipse.Java class. The class 
 * is used to create market nodes in the simulation.
 * @author Robert
 *
 */
public class marketCircle extends Ellipse {
	Object name;
	String facilityType = "";
	Integer facTypeIndex = 0;
	ArrayList<Object> marketData = new ArrayList<Object>();
	ArrayList<Object> marketStruct = new ArrayList<Object>();
	MenuBar menu = new MenuBar();
	Text text = new Text();
	String commodity;
}
