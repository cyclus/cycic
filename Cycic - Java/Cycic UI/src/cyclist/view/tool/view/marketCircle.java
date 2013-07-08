package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.MenuBar;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

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
