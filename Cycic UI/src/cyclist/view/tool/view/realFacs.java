package cyclist.view.tool.view;

import java.util.ArrayList;

public class realFacs {
	
	static ArrayList<ArrayList<Object>> alfredStructs = new ArrayList<ArrayList<Object>>();
	static ArrayList<String> alfredStructsNames = new ArrayList<String>();
	
	public static void init(){
		//build an recipereactor array
		ArrayList<Object> RecipeReactor=new ArrayList<Object>();
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
		RecipeReactor.add(name);
		//build an fuelpair array
		ArrayList<Object> FuelPair=new ArrayList<Object>();
		FuelPair.add("Fuel Pair");
		//build fuelpairsubarray
		ArrayList<Object> FuelPairSub=new ArrayList<Object>();
		//build reactor incommodity
		ArrayList<Object> ReactorIncommodity=new ArrayList<Object>();
		ReactorIncommodity.add("Incommodity");
		ReactorIncommodity.add("String");
		ReactorIncommodity.add("String");
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(0);
		ReactorIncommodity.add(null);
		ReactorIncommodity.add(null);
		//build reactor inrecipe
		ArrayList<Object> ReactorInrecipe=new ArrayList<Object>();
		ReactorInrecipe.add("Inrecipe");
		ReactorInrecipe.add("String");
		ReactorInrecipe.add("String");
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(0);
		ReactorInrecipe.add(null);
		ReactorInrecipe.add(null);
		//build reactor outcommodity
		ArrayList<Object> ReactorOutcommodity=new ArrayList<Object>();
		ReactorOutcommodity.add("Outcommodity");
		ReactorOutcommodity.add("String");
		ReactorOutcommodity.add("String");
		ReactorOutcommodity.add(null);
		ReactorOutcommodity.add(null);
		ReactorOutcommodity.add(null);
		ReactorOutcommodity.add(0);
		ReactorOutcommodity.add(null);
		ReactorOutcommodity.add(null);
		//build reactor outrecipe
		ArrayList<Object> ReactorOutrecipe=new ArrayList<Object>();
		ReactorOutrecipe.add("Outrecipe");
		ReactorOutrecipe.add("String");
		ReactorOutrecipe.add("String");
		ReactorOutrecipe.add(null);
		ReactorOutrecipe.add(null);
		ReactorOutrecipe.add(null);
		ReactorOutrecipe.add(0);
		ReactorOutrecipe.add(null);
		ReactorOutrecipe.add(null);

		FuelPairSub.add(ReactorIncommodity);
		FuelPairSub.add(ReactorInrecipe);
		FuelPairSub.add(ReactorOutcommodity);
		FuelPairSub.add(ReactorOutrecipe);
		FuelPair.add(FuelPairSub);
		FuelPair.add("oneOrMore");
		FuelPair.add(null);
		FuelPair.add(null);
		FuelPair.add(null);
		FuelPair.add(0);
		FuelPair.add(null);
		FuelPair.add(null);
		//build  reactor startConstrYear
		ArrayList<Object> ReactorstartConstrYear=new ArrayList<Object>();
		ReactorstartConstrYear.add("Start Constr Year");
		ReactorstartConstrYear.add("Double");
		ReactorstartConstrYear.add("Double");
		ReactorstartConstrYear.add(" ");
		ReactorstartConstrYear.add(null);
		ReactorstartConstrYear.add(null);
		ReactorstartConstrYear.add(0);
		ReactorstartConstrYear.add("Year construction begins on the reactor");
		ReactorstartConstrYear.add("nonNegativeInteger");
		//build reactor startconstrmonth
		ArrayList<Object> ReactorstartConstrMonth=new ArrayList<Object>();
		ReactorstartConstrMonth.add("Start Constr Month");
		ReactorstartConstrMonth.add("Double");
		ReactorstartConstrMonth.add("Double");
		ReactorstartConstrMonth.add(" ");
		ReactorstartConstrMonth.add(null);
		ReactorstartConstrMonth.add(null);
		ReactorstartConstrMonth.add(0);
		ReactorstartConstrMonth.add("Number of month from 0 (January) to 11 (December)");
		ReactorstartConstrMonth.add("nonNegativeInteger");
		//build reactor startOperYear
		ArrayList<Object> ReactorstartOperYear=new ArrayList<Object>();
		ReactorstartOperYear.add("startOperYear");
		ReactorstartOperYear.add("Double");
		ReactorstartOperYear.add("Double");
		ReactorstartOperYear.add(" ");
		ReactorstartOperYear.add(null);
		ReactorstartOperYear.add(null);
		ReactorstartOperYear.add(0);
		ReactorstartOperYear.add(null);
		ReactorstartOperYear.add(null);
		//build reactor startOperMonth
		ArrayList<Object> ReactorstartOperMonth = new ArrayList<Object>();
		ReactorstartOperMonth.add("startOperMonth");
		ReactorstartOperMonth.add("Double");
		ReactorstartOperMonth.add("Double");
		ReactorstartOperMonth.add(" ");
		ReactorstartOperMonth.add(null);
		ReactorstartOperMonth.add(null);
		ReactorstartOperMonth.add(0);
		ReactorstartOperMonth.add(null);
		ReactorstartOperMonth.add(null);
		//build reactor licExpYear
		ArrayList<Object> ReactorlicExpYear=new ArrayList<Object>();
		ReactorlicExpYear.add("ReactorlicExpYear");
		ReactorlicExpYear.add("Double");
		ReactorlicExpYear.add("Double");
		ReactorlicExpYear.add(" ");
		ReactorlicExpYear.add(null);
		ReactorlicExpYear.add(null);
		ReactorlicExpYear.add(0);
		ReactorlicExpYear.add(null);
		ReactorlicExpYear.add("nonNegativeInteger");
		//build reactor licExpMonth
		ArrayList<Object> ReactorlicExpMonth=new ArrayList<Object>();
		ReactorlicExpMonth.add("ReactorlicExpMonth");
		ReactorlicExpMonth.add("Double");
		ReactorlicExpMonth.add("Double");
		ReactorlicExpMonth.add(" ");
		ReactorlicExpMonth.add(null);
		ReactorlicExpMonth.add(null);
		ReactorlicExpMonth.add(0);
		ReactorlicExpMonth.add(null);
		ReactorlicExpMonth.add("nonNegativeInteger");
		//build reactor lifetime
		ArrayList<Object> ReactorlifeTime=new ArrayList<Object>();
		ReactorlifeTime.add("lifetime");
		ReactorlifeTime.add("Double");
		ReactorlifeTime.add("Double");
		ReactorlifeTime.add(" ");
		ReactorlifeTime.add(null);
		ReactorlifeTime.add(null);
		ReactorlifeTime.add(0);
		ReactorlifeTime.add(null);
		ReactorlifeTime.add(null);
		//build reactor state
		ArrayList<Object> ReactorState=new ArrayList<Object>();
		ReactorState.add("State");
		ReactorState.add("String");
		ReactorState.add("String");
		ReactorState.add(null);
		ReactorState.add(null);
		ReactorState.add(null);
		ReactorState.add(0);
		ReactorState.add(null);
		ReactorState.add(null);
		//build reactor typeReactor
		ArrayList<Object> typeReactor=new ArrayList<Object>();
		typeReactor.add("typeReac");
		typeReactor.add("String");
		typeReactor.add("String");
		typeReactor.add(null);
		typeReactor.add(null);
		typeReactor.add(null);
		typeReactor.add(0);
		typeReactor.add(null);
		typeReactor.add(null);
		//build reactor capacity
		ArrayList<Object> ReactorCapacity=new ArrayList<Object>();
		ReactorCapacity.add("Capacity");
		ReactorCapacity.add("Double");
		ReactorCapacity.add("Double");
		ReactorCapacity.add("MW");
		ReactorCapacity.add("0...6000");
		ReactorCapacity.add("0");
		ReactorCapacity.add(0);
		ReactorCapacity.add(null);
		ReactorCapacity.add(null);
		//build reactor elecCF
		ArrayList<Object> elecCF=new ArrayList<Object>();
		elecCF.add("elecCF");
		elecCF.add("Double");
		elecCF.add("Double");
		elecCF.add(" ");
		elecCF.add(null);
		elecCF.add(null);
		elecCF.add(0);
		elecCF.add(null);
		elecCF.add(null);

		RecipeReactor.add(FuelPair);
		RecipeReactor.add(ReactorstartConstrYear);
		RecipeReactor.add(ReactorstartConstrMonth);
		RecipeReactor.add(ReactorstartOperYear);
		RecipeReactor.add(ReactorstartOperMonth);
		RecipeReactor.add(ReactorlicExpYear);
		RecipeReactor.add(ReactorlicExpMonth);
		RecipeReactor.add(ReactorlifeTime);
		RecipeReactor.add(ReactorState);
		RecipeReactor.add(typeReactor);
		RecipeReactor.add(ReactorCapacity);
		RecipeReactor.add(elecCF);
		
		alfredStructs.add(RecipeReactor);
		
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
		
		//build Storage Facility
		ArrayList<Object> StorageFacility=new ArrayList<Object>();
		//name
		ArrayList<Object> nameStore = new ArrayList<Object>();
		nameStore.add("Name");
		nameStore.add("String");
		nameStore.add("String");
		nameStore.add(null);
		nameStore.add(null);
		nameStore.add(null);
		nameStore.add(0);
		nameStore.add("Name of Storage Facility");
		nameStore.add(null);
		StorageFacility.add(nameStore);
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
		Incommodity.add(null);
		Incommodity.add(null);
		
		subIncommodities.add(Incommodity);
		Incommodities.add(subIncommodities);
		Incommodities.add("oneOrMore");
		Incommodities.add(null);
		Incommodities.add(null);
		Incommodities.add(null);
		Incommodities.add(0);
		Incommodities.add("Commodities the facility accepts");
		Incommodities.add(null);
		//build Capacity array
		ArrayList<Object> Capacity=new ArrayList<Object>();
		Capacity.add("Capacity");
		Capacity.add("Double");
		Capacity.add("Double");
		Capacity.add("kg/yr");
		Capacity.add("0...10000");
		Capacity.add("0");
		Capacity.add(0);
		Capacity.add(null);
		Capacity.add(null);
		//use inventory size from sourcefacility
		//build Residence Time array
		ArrayList<Object> ResidenceTime = new ArrayList<Object>();
		ResidenceTime.add("Residence Time");
		ResidenceTime.add("Double");
		ResidenceTime.add("Double");
		ResidenceTime.add("Months");
		ResidenceTime.add(null);
		ResidenceTime.add(null);
		ResidenceTime.add(0);
		ResidenceTime.add(null);
		ResidenceTime.add("NonNegativeInteger");
		
		//build Initial Stocks array
		ArrayList<Object> InitialStocks=new ArrayList<Object>();
		InitialStocks.add("Initial Stocks");
		//build subarray for InitialStocks
		ArrayList<Object> SubInitialStocks=new ArrayList<Object>();
		
		//Build facility array
		ArrayList<Object> Facility=new ArrayList<Object>();
		Facility.add("Facility");
		Facility.add("String");
		Facility.add("String");
		Facility.add(null);
		Facility.add(null);
		Facility.add(null);
		Facility.add(0);
		Facility.add(null);
		Facility.add(null);
		//using the recipe from sourcefacility
		//build amount array
		ArrayList<Object> Amount=new ArrayList<Object>();
		Amount.add("Amount");
		Amount.add("Double");
		Amount.add("Double");
		Amount.add("kg");
		Amount.add("0...1000");
		Amount.add("0");
		Amount.add(0);
		Amount.add(null);
		Amount.add(null);
		
		//build age array
		ArrayList<Object> Age=new ArrayList<Object>();
		Age.add("Age");
		Age.add("Double");
		Age.add("Double");
		Age.add("Months");
		Age.add(null);
		Age.add(null);
		Age.add(0);
		Age.add(null);
		Age.add(null);
		
		SubInitialStocks.add(Facility);
		SubInitialStocks.add(Incommodity);
		SubInitialStocks.add(Recipe);
		SubInitialStocks.add(Amount);
		SubInitialStocks.add(Age);
		InitialStocks.add(SubInitialStocks);
		InitialStocks.add("oneOrMore");
		InitialStocks.add(null);
		InitialStocks.add(null);
		InitialStocks.add(null);
		InitialStocks.add(0);
		InitialStocks.add(null);
		InitialStocks.add(null);
		
		StorageFacility.add(Incommodities);
		StorageFacility.add(Capacity);
		StorageFacility.add(InventorySize);
		StorageFacility.add(ResidenceTime);
		StorageFacility.add(InitialStocks);
	
		alfredStructs.add(StorageFacility);
		
		alfredStructsNames.add("RecipeReactor");
		alfredStructsNames.add("EnrichmentFacility");
		alfredStructsNames.add("SourceFacility");
		alfredStructsNames.add("StorageFacility");
	}
}
