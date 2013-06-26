package cyclist.view.tool.view;

import java.util.ArrayList;

public class practiceInstitute {
	static ArrayList<Object> deployInst = new ArrayList<Object>();
	
	public static void init(){
		// Name
		ArrayList<Object> name = new ArrayList<Object>();
		name.add("Name");
		name.add("String");
		name.add("String");
		name.add(null);
		name.add(null);
		name.add("DeplayInst");
		name.add("0");
		name.add(null);
		
		deployInst.add(name);
		
		ArrayList<Object> buildOrderArray = new ArrayList<Object>();
		buildOrderArray.add("buildOrder");
		// Build Orders
		ArrayList<Object> builderOrder = new ArrayList<Object>();
		// Prototypes
		ArrayList<Object> prototype = new ArrayList<Object>();
		prototype.add("prototype");
		prototype.add("String");
		prototype.add("String");
		prototype.add(null);
		prototype.add(null);
		prototype.add(null);
		prototype.add(0);
		prototype.add("Facility Prototypes available");
		prototype.add(null);
		// Number
		ArrayList<Object> number = new ArrayList<Object>();
		number.add("number");
		number.add("Double");
		number.add("Double");
		number.add(null);
		number.add(null);
		number.add(null);
		number.add(0);
		number.add("Number of facilities to build");
		number.add(null);		
		// Date
		ArrayList<Object> date = new ArrayList<Object> ();
		date.add("date");
		date.add("String");
		date.add("String");
		date.add(null);
		date.add(null);
		date.add(null);
		date.add(0);
		date.add("Build date");
		date.add(null);			
		builderOrder.add(prototype);
		builderOrder.add(number);
		builderOrder.add(date);
		buildOrderArray.add(builderOrder);
		buildOrderArray.add("oneOrMore");
		buildOrderArray.add(null);
		buildOrderArray.add(null);
		buildOrderArray.add(null);
		buildOrderArray.add(0);
		buildOrderArray.add(null);
		buildOrderArray.add(null);
		
		deployInst.add(buildOrderArray);
		
		dataArrays.institStructs.add(deployInst);
		dataArrays.institNodes.add(new ArrayList<Object>());
		
		formBuilderFunctions.formArrayBuilder(deployInst, dataArrays.institNodes.get(0));
	}
}
