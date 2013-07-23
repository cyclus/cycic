package cyclist.view.tool.view;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

/**
 * This class contains all of the data structures used for CYCIC.
 * @author Robert
 *
 */
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
	static simInfo simulationData = new simInfo();
}

/**
 * Class used to build the recipes for cyclus.
 * @author Robert
 *
 */
class Nrecipe {
	String Name = new String();
	String Basis = new String();
	ArrayList<isotopeData> Composition = new ArrayList<isotopeData>();
}

/**
 * Contains name, weightFrac, and mass information for specify an isotope within a recipe.
 * @author Robert
 *
 */
class isotopeData {
	String Name = new String();
	double weightFrac;
	double mass;
}

/**
 * Class used to create the links between nodes.
 * @author Robert
 *
 */
class nodeLink {
	facilityCircle source;
	Object target;
	Line line = new Line();
}

/**
 * Class used to represent regions in Cyclus. Contains structures for; 
 * name, type, data structures, data, available facilities, and institutions.
 * @author Robert
 *
 */
class regionNode{
	String name = new String();
	String type = new String();
	ArrayList<Object> regionStruct = new ArrayList<Object>();
	ArrayList<Object> regionData  = new ArrayList<Object>();
	ArrayList<String> availFacilities = new ArrayList<String>(); 
	ArrayList<String> institutions = new ArrayList<String>();
}

/**
 * Class used to represent institutions in Cyclus. Contains structures for; 
 * name,type, data structures, data, available prototypes, and 
 * initial facilityItems (facility name /number)
 * @author Robert
 *
 */
class instituteNode{
	String name;
	String type;
	ArrayList<Object> institStruct  = new ArrayList<Object>();
	ArrayList<Object> institData  = new ArrayList<Object>();
	ArrayList<facilityItem> availFacilities = new ArrayList<facilityItem>();
	ArrayList<String> availPrototypes = new ArrayList<String>();
}

/**
 * Class used to indicate an initial facility within an institution and
 * the number of these institutions at the start of the simualtion.
 * @author Robert
 *
 */
class facilityItem{
	String name;
	String number;	
}

/**
 * Class containing the simulation information for the current working
 * simulation. 
 * @author Robert
 *
 */
class simInfo{
	String duration;
	String startMonth;
	String startYear;
	String simStart;
	String decay;
}


