package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.MenuBar;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

public class marketCircle extends Ellipse {
	String facilityType = "";
	Integer facTypeIndex = 0;
	ArrayList<Object> facilityData = new ArrayList<Object>();
	ArrayList<facilityCircle> childrenList = new ArrayList<facilityCircle>();
	ArrayList<nodeLink> childrenLinks = new ArrayList<nodeLink>();
	ArrayList<Double> childrenDeltaX = new ArrayList<Double>();
	ArrayList<Double> childrenDeltaY = new ArrayList<Double>();
	ArrayList<Integer> rgbColor = new ArrayList<Integer>();
	MenuBar menu = new MenuBar();
	Text text = new Text();
	String type = new String();
	String parent = new String();
	Integer parentIndex;
	Integer clickIndex;
	Boolean childrenShow;
}
