package cyclist.view.tool.view;

import java.util.ArrayList;

public class realFacs {
	
	static ArrayList<ArrayList<Object>> alfredStructs = new ArrayList<ArrayList<Object>>();
	static ArrayList<String> alfredStructsNames = new ArrayList<String>();
	
	public static void init(){
		//build an recipereactor array
		ArrayList<Object> BatchReactor=new ArrayList<Object>();
		//reactor name
		ArrayList<Object> name = new ArrayList<Object>();
		name.add("Name");
		name.add("String");
		name.add("String");
		name.add(null);
		name.add(null);
		name.add("Reactor 1");
		name.add(0);
		name.add(null);
		name.add(null);
		BatchReactor.add(name);
		//build an fuelpair array
		ArrayList<Object> fuelInput=new ArrayList<Object>();
		fuelInput.add("fuel_input");
		//build fuelpairsubarray
		ArrayList<Object> FuelPairSub=new ArrayList<Object>();
		//build reactor incommodity
		ArrayList<Object> ReactorIncommodity=new ArrayList<Object>();
		ReactorIncommodity.add("incommodity");
		ReactorIncommodity.add("String");
		ReactorIncommodity.add("String");
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(0);
		ReactorIncommodity.add("the input commidty name");
		ReactorIncommodity.add(null);
		//build reactor inrecipe
		ArrayList<Object> ReactorInrecipe=new ArrayList<Object>();
		ReactorInrecipe.add("inrecipe");
		ReactorInrecipe.add("String");
		ReactorInrecipe.add("String");
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(0);
		ReactorInrecipe.add("the input recipe (isotopic");
		ReactorInrecipe.add(null);
		FuelPairSub.add(ReactorIncommodity);
		FuelPairSub.add(ReactorInrecipe);
		fuelInput.add(FuelPairSub);
		fuelInput.add("fuel_input");
		fuelInput.add(null);
		fuelInput.add(null);
		fuelInput.add(null);
		fuelInput.add(0);
		fuelInput.add("information about the fuel consumed by this reactor");
		fuelInput.add(null);
		
		ArrayList<Object> fuelOutput = new ArrayList<Object>();
		fuelOutput.add("fuel_output");
		ArrayList<Object> FuelPairSub2=new ArrayList<Object>();
		//build reactor outcommodity
		ArrayList<Object> ReactorOutCommodity=new ArrayList<Object>();
		ReactorOutCommodity.add("outcommodity");
		ReactorOutCommodity.add("String");
		ReactorOutCommodity.add("String");
		ReactorOutCommodity.add(null);
		ReactorOutCommodity.add(null);
		ReactorOutCommodity.add(null);
		ReactorOutCommodity.add(0);
		ReactorOutCommodity.add("the output commodity name");
		ReactorOutCommodity.add(null);
		//build reactor inrecipe
		ArrayList<Object> ReactorOutRecipe=new ArrayList<Object>();
		ReactorOutRecipe.add("outrecipe");
		ReactorOutRecipe.add("String");
		ReactorOutRecipe.add("String");
		ReactorOutRecipe.add(null);
		ReactorOutRecipe.add(null);
		ReactorOutRecipe.add(null);
		ReactorOutRecipe.add(0);
		ReactorOutRecipe.add("the output recipe (isotopics)");
		ReactorOutRecipe.add(null);
		FuelPairSub2.add(ReactorOutCommodity);
		FuelPairSub2.add(ReactorOutRecipe);
		fuelOutput.add(FuelPairSub2);
		fuelOutput.add("fuel_output");
		fuelOutput.add(null);
		fuelOutput.add(null);
		fuelOutput.add(null);
		fuelOutput.add(0);
		fuelOutput.add("information about the used fuel output by this reactor");
		fuelOutput.add(null);

		//build  reactor cycleLength
		ArrayList<Object> reactorCycleLength=new ArrayList<Object>();
		reactorCycleLength.add("cyclelength");
		reactorCycleLength.add("Double");
		reactorCycleLength.add("Double");
		reactorCycleLength.add(" ");
		reactorCycleLength.add(null);
		reactorCycleLength.add(null);
		reactorCycleLength.add(0);
		reactorCycleLength.add("number of months the reactor is operational before refueling");
		reactorCycleLength.add("nonNegativeInteger");
		//build reactor refuelDelay
		ArrayList<Object> reactorRefuelTime=new ArrayList<Object>();
		reactorRefuelTime.add("refueldelay");
		reactorRefuelTime.add("Double");
		reactorRefuelTime.add("Double");
		reactorRefuelTime.add(" ");
		reactorRefuelTime.add(null);
		reactorRefuelTime.add(null);
		reactorRefuelTime.add(1);
		reactorRefuelTime.add("the number of months the reactor is offline in order to be refuel");
		reactorRefuelTime.add("nonNegativeInteger");
		//build reactor startOperYear
		ArrayList<Object> reactorLoading=new ArrayList<Object>();
		reactorLoading.add("incoreloading");
		reactorLoading.add("Double");
		reactorLoading.add("Double");
		reactorLoading.add(" ");
		reactorLoading.add(null);
		reactorLoading.add(null);
		reactorLoading.add(0);
		reactorLoading.add("the quantity of core material when entering the reactor");
		reactorLoading.add(null);
		//build reactor startOperMonth
		ArrayList<Object> reactorOutLoading = new ArrayList<Object>();
		reactorOutLoading.add("outcoreloading");
		reactorOutLoading.add("Double");
		reactorOutLoading.add("Double");
		reactorOutLoading.add(" ");
		reactorOutLoading.add(null);
		reactorOutLoading.add(null);
		reactorOutLoading.add(0);
		reactorOutLoading.add("the quantity of core material when exiting the reactor");
		reactorOutLoading.add(null);
		//build reactor licExpYear
		ArrayList<Object> batchsPerCore=new ArrayList<Object>();
		batchsPerCore.add("batchespercore");
		batchsPerCore.add("Double");
		batchsPerCore.add("Double");
		batchsPerCore.add(null);
		batchsPerCore.add(null);
		batchsPerCore.add(null);
		batchsPerCore.add(0);
		batchsPerCore.add("the number of batches comprising a full core");
		batchsPerCore.add("nonNegativeInteger");
		//commodity production
		ArrayList<Object> commodityProduction = new ArrayList<Object>();
		commodityProduction.add("commodity_production");
		ArrayList<Object> commodProd = new ArrayList<Object>();
		ArrayList<Object> commodityProd = new ArrayList<Object>();
		commodityProd.add("commodity");
		commodityProd.add("String");
		commodityProd.add("String");
		commodityProd.add(null);
		commodityProd.add(null);
		commodityProd.add(null);
		commodityProd.add(0);
		commodityProd.add("the name of the produced commodity");
		commodityProd.add(null);
		ArrayList<Object> capacityProd = new ArrayList<Object>();
		capacityProd.add("capacity");
		capacityProd.add("String");
		capacityProd.add("String");
		capacityProd.add(null);
		capacityProd.add(null);
		capacityProd.add(null);
		capacityProd.add(0);
		capacityProd.add("the nameplate capacity for the power production");
		capacityProd.add(null);
		ArrayList<Object> costProd = new ArrayList<Object>();
		costProd.add("cost");
		costProd.add("String");
		costProd.add("String");
		costProd.add(null);
		costProd.add(null);
		costProd.add(null);
		costProd.add(0);
		costProd.add("the cost to produce this capacity");
		costProd.add("By default, this should be equal to the capacity");
		
		commodProd.add(commodityProd);
		commodProd.add(capacityProd);
		commodProd.add(costProd);
		
		commodityProduction.add(commodProd);
		commodityProduction.add("commodity_production");
		commodityProduction.add(null);
		commodityProduction.add(null);
		commodityProduction.add(null);
		commodityProduction.add(0);
		commodityProduction.add("information about the power produced by the reactor");
		commodityProduction.add(null);
		
		BatchReactor.add(fuelInput);
		BatchReactor.add(fuelOutput);
		BatchReactor.add(reactorCycleLength);
		BatchReactor.add(reactorRefuelTime);
		BatchReactor.add(reactorLoading);
		BatchReactor.add(reactorOutLoading);
		BatchReactor.add(batchsPerCore);
		BatchReactor.add(commodityProduction);
		
		alfredStructs.add(BatchReactor);
		
		//build enrichment facility array
		ArrayList<Object>EnrichmentFacility=new ArrayList<Object>();
		// name
		ArrayList<Object> nameEnrich = new ArrayList<Object>();
		nameEnrich.add("Name");
		nameEnrich.add("String");
		nameEnrich.add("String");
		nameEnrich.add(null);
		nameEnrich.add(null);
		nameEnrich.add("EnrichmentPlant");
		nameEnrich.add(0);
		nameEnrich.add(null);
		nameEnrich.add(null);
		EnrichmentFacility.add(nameEnrich);
		//build input array
		ArrayList<Object>EnrichInput=new ArrayList<Object>();
		EnrichInput.add("input");
		//build subarray for input
		ArrayList<Object>SubEnrichinput=new ArrayList<Object>();

		//build incommodity array for Enrichment facility
		ArrayList<Object>EnrichIncommodity=new ArrayList<Object>();
		EnrichIncommodity.add("Incommodity");
		EnrichIncommodity.add("String");
		EnrichIncommodity.add("String");
		EnrichIncommodity.add(null);
		EnrichIncommodity.add(null);
		EnrichIncommodity.add(null);
		EnrichIncommodity.add(0);
		EnrichIncommodity.add(null);
		EnrichIncommodity.add(null);
		//build inrecipe for enrichment facility
		ArrayList<Object>Inrecipe = new ArrayList<Object>();
		Inrecipe.add("Inrecipe");
		Inrecipe.add("String");
		Inrecipe.add("String");
		Inrecipe.add(null);
		Inrecipe.add(null);
		Inrecipe.add(null);
		Inrecipe.add(0);
		Inrecipe.add(null);
		Inrecipe.add(null);
		//build inventorysize for enrichment facility
		ArrayList<Object>EnrichInventorySize=new ArrayList<Object>();
		EnrichInventorySize.add("Inventory Size");
		EnrichInventorySize.add("Double");
		EnrichInventorySize.add("Double");
		EnrichInventorySize.add("kg");
		EnrichInventorySize.add("0...1000000");
		EnrichInventorySize.add("0");
		EnrichInventorySize.add(1);
		EnrichInventorySize.add("Storage capacity of the facility");
		EnrichInventorySize.add(null);

		SubEnrichinput.add(EnrichIncommodity);
		SubEnrichinput.add(Inrecipe);
		SubEnrichinput.add(EnrichInventorySize);


		EnrichInput.add(SubEnrichinput);
		EnrichInput.add("input");
		EnrichInput.add(null);
		EnrichInput.add(null);
		EnrichInput.add(null);
		EnrichInput.add(0);
		EnrichInput.add(null);
		EnrichInput.add(null);

		//build output array for enrichment facility
		ArrayList<Object> EnrichOutput= new ArrayList<Object>();
		EnrichOutput.add("Output");

		//build suboutput array for enrichment facility
		ArrayList<Object>EnrichSubOutput=new ArrayList<Object>();

		//build outcommodity for enrichment facility
		ArrayList<Object> EnrichOutcommodity=new ArrayList<Object>();
		EnrichOutcommodity.add("Outcommodity");
		EnrichOutcommodity.add("String");
		EnrichOutcommodity.add("String");
		EnrichOutcommodity.add(null);
		EnrichOutcommodity.add(null);
		EnrichOutcommodity.add(null);
		EnrichOutcommodity.add(0);
		EnrichOutcommodity.add(null);
		EnrichOutcommodity.add(null);
		EnrichSubOutput.add(EnrichOutcommodity);
		EnrichOutput.add(EnrichSubOutput);
		//build tails_assay array for enrichment facility
		ArrayList<Object> Tails_assay=new ArrayList<Object>();
		Tails_assay.add("Tails Assay");
		Tails_assay.add("Double");
		Tails_assay.add("Double");
		Tails_assay.add(null);
		Tails_assay.add("0...1");
		Tails_assay.add("0");
		Tails_assay.add(0);
		Tails_assay.add(null);
		Tails_assay.add(null);

		EnrichSubOutput.add(Tails_assay);
		EnrichOutput.add("output");
		EnrichOutput.add(null);
		EnrichOutput.add(null);
		EnrichOutput.add(null);
		EnrichOutput.add(0);
		EnrichOutput.add(null);
		EnrichOutput.add(null);
		EnrichmentFacility.add(EnrichInput);
		EnrichmentFacility.add(EnrichOutput);
		
		alfredStructs.add(EnrichmentFacility);
		
		ArrayList<Object>  SourceFacility=new ArrayList<Object>();
		//build the name array
		ArrayList<Object> Name=new ArrayList<Object>();
		Name.add("Name");
		Name.add("String");
		Name.add("String");
		Name.add(null);
		Name.add(null);
		Name.add(null);
		Name.add(0);
		Name.add("Name of the Facility");
		Name.add(null);
		SourceFacility.add(Name);
		//build subarray inside OUTPUT
		ArrayList<Object> SubOutput=new ArrayList<Object>();
		
		//build the OutCommodity Array
		ArrayList<Object> OutCommodity=new ArrayList<Object>();
		OutCommodity.add("Outcommodity");
		OutCommodity.add("String");
		OutCommodity.add("String");
		OutCommodity.add(null);
		OutCommodity.add(null);
		OutCommodity.add(null);
		OutCommodity.add(0);
		OutCommodity.add(null);
		OutCommodity.add(null);
		//build outPut capacity Array
		ArrayList<Object> OutputCapacity=new ArrayList<Object>();
		OutputCapacity.add("Output Capacity");
		OutputCapacity.add("Double");
		OutputCapacity.add("Double");
		OutputCapacity.add("kg");
		OutputCapacity.add("0...1000000");
		OutputCapacity.add("100");
		OutputCapacity.add(0);
		OutputCapacity.add(null);
		OutputCapacity.add(null);
		//build the Inventory_Size Array
		ArrayList<Object> InventorySize=new ArrayList<Object>();
		InventorySize.add("Inventory Size");
		InventorySize.add("Double");
		InventorySize.add("Double");
		InventorySize.add("kg");
		InventorySize.add("0...1000000");
		InventorySize.add("0");
		InventorySize.add(1);
		InventorySize.add(null);
		InventorySize.add(null);
		//Build the Recipe Array
		ArrayList<Object> Recipe = new ArrayList<Object>();
		Recipe.add("Recipe");
		Recipe.add("String");
		Recipe.add("String");
		Recipe.add(null);
		Recipe.add(null);
		Recipe.add(null);
		Recipe.add(0);
		Recipe.add(null);
		Recipe.add(null);
		SubOutput.add(OutCommodity);
		SubOutput.add(OutputCapacity);
		SubOutput.add(InventorySize);
		SubOutput.add(Recipe);
		//build the output Array
		ArrayList<Object> Output =new ArrayList<Object>();
		Output.add("output");
		Output.add(SubOutput);
		Output.add("output");
		Output.add(null);
		Output.add(null);
		Output.add(null);
		Output.add(0);
		Output.add(null);
		Output.add(null);
		SourceFacility.add(Output);
		
		alfredStructs.add(SourceFacility);
		
		//build Sink Facility
		ArrayList<Object> SinkFacility=new ArrayList<Object>();
		//name
		ArrayList<Object> nameStore = new ArrayList<Object>();
		nameStore.add("Name");
		nameStore.add("String");
		nameStore.add("String");
		nameStore.add(null);
		nameStore.add(null);
		nameStore.add(null);
		nameStore.add(0);
		nameStore.add("Name of Sink Facility");
		nameStore.add(null);
		SinkFacility.add(nameStore);
		//Build Incommodities array
		ArrayList<Object> Incommodities=new ArrayList<Object>();
		Incommodities.add("Incommodities");
		//build subarray of incommodities
		ArrayList<Object> subIncommodities=new ArrayList<Object>();
		
		//build incommodity array
		ArrayList<Object>Incommodity=new ArrayList<Object>();
		Incommodity.add("Incommodity");
		Incommodity.add("String");
		Incommodity.add("String");
		Incommodity.add(null);
		Incommodity.add(null);
		Incommodity.add(null);
		Incommodity.add(0);
		Incommodity.add("a set of commodityies which can be accepted by this facility");
		Incommodity.add(null);
		
		/*subIncommodities.add(Incommodity);
		Incommodities.add(subIncommodities);
		Incommodities.add("oneOrMore");
		Incommodities.add(null);
		Incommodities.add(null);
		Incommodities.add(null);
		Incommodities.add(0);
		Incommodities.add("a set of commodityies which can be accepted by this facility");
		Incommodities.add(null);*/
		//build Capacity array
		ArrayList<Object> Capacity=new ArrayList<Object>();
		Capacity.add("capacity");
		Capacity.add("Double");
		Capacity.add("Double");
		Capacity.add("kg/yr");
		Capacity.add("0...10000");
		Capacity.add("0");
		Capacity.add(0);
		Capacity.add("a maximum amount of material that can be accepted for any given time step");
		Capacity.add(null);
		//use inventory size from sourcefacility
		ArrayList<Object> InventorySizeSink = new ArrayList<Object>();
		InventorySizeSink.add("inventorysize");
		InventorySizeSink.add("Double");
		InventorySizeSink.add("Double");
		InventorySizeSink.add("kg");
		InventorySizeSink.add("0...1000000");
		InventorySizeSink.add("0");
		InventorySizeSink.add(0);
		InventorySizeSink.add("the maximum amount of material that can be stored at this facility");
		InventorySizeSink.add(null);
		
		SinkFacility.add(Incommodity);
		SinkFacility.add(Capacity);
		SinkFacility.add(InventorySizeSink);
	
		alfredStructs.add(SinkFacility);
		
		alfredStructsNames.add("BatchReactor");
		alfredStructsNames.add("EnrichmentFacility");
		alfredStructsNames.add("SourceFacility");
		alfredStructsNames.add("SinkFacility");
	}
}
