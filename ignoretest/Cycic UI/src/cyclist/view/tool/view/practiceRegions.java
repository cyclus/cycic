package cyclist.view.tool.view;

import java.util.ArrayList;

public class practiceRegions {
	static ArrayList<Object> growthRegion = new ArrayList<Object>();
	
	public static void init(){
		// Name //
		ArrayList<Object> name = new ArrayList<Object>();
		name.add("Name");
		name.add("String");
		name.add("String");
		name.add(null);
		name.add(null);
		name.add("GrowthRegion");
		name.add("0");
		name.add(null);
		
		growthRegion.add(name);
		
		// Commodity Requirements	
		ArrayList<Object> commodityDemand = new ArrayList<Object>();
		commodityDemand.add("Commodity");
		ArrayList<Object> commodityOrMore = new ArrayList<Object>();
		// Commodity
		ArrayList<Object> commodity = new ArrayList<Object>();
		commodity.add("Name");
		commodity.add("String");
		commodity.add("String");
		commodity.add(null);
		commodity.add(null);
		commodity.add("Commodity");
		commodity.add(0);
		commodity.add(null);
		// Commodity Name
		ArrayList<Object> demandTop = new ArrayList<Object>();
		demandTop.add("Demand");
		ArrayList<Object> demand = new ArrayList<Object>();
		
		ArrayList<Object> demandType = new ArrayList<Object>();
		demandType.add("Type");
		demandType.add("String");
		demandType.add("String");
		demandType.add(null);
		demandType.add(null);
		demandType.add("DemandType");
		demandType.add(0);
		demandType.add(null);
		
		ArrayList<Object> demandParams = new ArrayList<Object>();
		demandParams.add("Parameters");
		demandParams.add("String");
		demandParams.add("String");
		demandParams.add(null);
		demandParams.add(null);
		demandParams.add("Parameters");
		demandParams.add(0);
		demandParams.add(null);
		
		ArrayList<Object> demandStartTime = new ArrayList<Object>();
		demandStartTime.add("Type");
		demandStartTime.add("Integer");
		demandStartTime.add("Integer");
		demandStartTime.add(null);
		demandStartTime.add(null);
		demandStartTime.add("nonNegativeInt");
		demandStartTime.add(1);
		demandStartTime.add(null);
		// Filling Demand //
		demand.add(demandType);
		demand.add(demandParams);
		demand.add(demandStartTime);
		//Finishing demandTop
		demandTop.add(demand);
		demandTop.add("oneOrMore");
		demandTop.add(null);
		demandTop.add(null);
		demandTop.add("GrowthRegion");
		demandTop.add(0);
		demandTop.add(null);
		
		commodityOrMore.add(commodity);
		commodityOrMore.add(demandTop);
		
		
		commodityDemand.add(commodityOrMore);
		commodityDemand.add("oneOrMore");
		commodityDemand.add(null);
		commodityDemand.add(null);
		commodityDemand.add("GrowthRegion");
		commodityDemand.add(0);
		commodityDemand.add(null);
		
		growthRegion.add(commodityDemand);
		dataArrays.regionStructs.add(growthRegion);
	}
}
