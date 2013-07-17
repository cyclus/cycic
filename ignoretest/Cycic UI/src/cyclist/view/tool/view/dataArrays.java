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
	
	static ArrayList<Object> regionStructs = new ArrayList<Object>();
	static ArrayList<regionNode> regionNodes = new ArrayList<regionNode>();
	
	static ArrayList<Object> institStructs = new ArrayList<Object>();
	static ArrayList<instituteNode> institNodes = new ArrayList<instituteNode>();
	
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
	facilityCircle source;
	Object target;
	Line line = new Line();
}

class regionNode{
	String name = new String();
	String type = new String();
	ArrayList<Object> regionStruct = new ArrayList<Object>();
	ArrayList<Object> regionData  = new ArrayList<Object>();
	ArrayList<String> availFacilities = new ArrayList<String>(); 
}

class instituteNode{
	String name;
	String type;
	ArrayList<Object> institStruct  = new ArrayList<Object>();
	ArrayList<Object> institData  = new ArrayList<Object>();
	ArrayList<String> availFacilities = new ArrayList<String>();
	ArrayList<prototypeItem> availPrototypes = new ArrayList<prototypeItem>();
}

class simInfo{
	double duration;
	int startMonth;
	int startYear;
	int simStart;
	int decay;
}

class prototypeItem{
	String prototypeName;
	Integer Number;	
}
