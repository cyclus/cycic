package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class dataArrays {
	static ArrayList<facilityCircle> FacilityNodes = new ArrayList<facilityCircle>();
	static ArrayList<nodeLink> Links = new ArrayList<nodeLink>();
	static ArrayList<nodeLink> hiddenLinks = new ArrayList<nodeLink>();
	static ArrayList<String> Commodities = new ArrayList<String>();
	static ArrayList<Nrecipe> Recipes = new ArrayList<Nrecipe>();
	static ArrayList<Label> Regions = new ArrayList<Label>();
	static ArrayList<Label> Institutions = new ArrayList<Label>();
	static ArrayList<Label> FacilityTypes = new ArrayList<Label>();	
	static ArrayList<Label> Markets = new ArrayList<Label>();
	static ArrayList<Label> CommoditiesList = new ArrayList<Label>();
	static ArrayList<Label> RecipesList = new ArrayList<Label>();
}

class Nrecipe {
	String Name = new String();
	String Basis = new String();
	ArrayList<isotopeData> Composition = new ArrayList<isotopeData>();
}

class isotopeData {
	String Name = new String();
	double weightFrac;
	double mass;
}

class nodeLink {
	String name = new String();
	String source = new String();
	String target = new String();
	Line line = new Line();
}