package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class dataArrays {
	static ArrayList<facilityCircle> FacilityNodes = new ArrayList<facilityCircle>();
	static ArrayList<Label> FacilityTypes = new ArrayList<Label>();	
	
	static ArrayList<nodeLink> Links = new ArrayList<nodeLink>();
	static ArrayList<nodeLink> hiddenLinks = new ArrayList<nodeLink>();
	
	static ArrayList<String> Commodities = new ArrayList<String>();
	static ArrayList<Label> CommoditiesList = new ArrayList<Label>();
	
	static ArrayList<Nrecipe> Recipes = new ArrayList<Nrecipe>();
	static ArrayList<Label> RecipesList = new ArrayList<Label>();
	
	static ArrayList<Label> Regions = new ArrayList<Label>();
	static ArrayList<Object> regionStructs = new ArrayList<Object>();
	static ArrayList<ArrayList<Object>> regionNodes = new ArrayList<ArrayList<Object>>();
	static ArrayList<regionNode> regionNodes1 = new ArrayList<regionNode>();
	
	static ArrayList<Label> Institutions = new ArrayList<Label>();
	static ArrayList<Object> institStructs = new ArrayList<Object>();
	static ArrayList<ArrayList<Object>> institNodes = new ArrayList<ArrayList<Object>>();
	
	static ArrayList<Label> Markets = new ArrayList<Label>();
	static ArrayList<Object> marketStructs = new ArrayList<Object>();
	static ArrayList<marketCircle> marketNodes = new ArrayList<marketCircle>();
	
	static ArrayList<String> simInfor = new ArrayList<String>();
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

class regionNode{
	String name = new String();
	ArrayList<Object> regionStruct = new ArrayList<Object>();
	ArrayList<facilityCircle> availFacilities = new ArrayList<facilityCircle>(); 
}

class simInfo{
	double duration;
	int startMonth;
	int startYear;
	int simStart;
	int decay;
}
