var customFac2 = [
	["StorageFacility", StorageFacility =
	  {
	    incommodity: [],
	    capacity: 'double',
	    inventorysize: 'double',
	    residencetime: 'nonNegativeInteger', 
	    initialstocks:   
	      [
	          {
	            facility: 'string',
	            recipe: 'string',
	            amount: 'double',
	            age: 'double'
	          }
	      ]
	  }],

	["EnrichmentFacility", EnrichmentFacility= 
		{
	        incommodity: 'incommodity',
	        outcommodity: "outcommodity",
	        inventorysize: "double",
	        capacity: "double",
	        tailsassay: "tailsassay"
        }],
    
    ["RecipeReactor", RecipeReactor =
    	{ 
		    fuelpair: [{
	            incommodity: "incommodity",
	            inrecipe: "inrecipe",
	            outcommodity: "outcommodity",
	            outrecipe: "outrecipe"
		     }],
	         startConstrYear: 'nonNegativeInteger',
	         startConstrMonth: 'nonNegativeInteger',
	         startOperYear: "startOperYear",
	         startOperMonth: "startOperMonth",
	         licExpYear: "nonNegativeInteger",
	         licExpMonth: "nonNegativeInteger",
	         lifetime: "lifetime",
	         state: "string",
	         typeReac: "string",
	         capacity: "capacity",
	         elecCF: "double"
     }],
    
    ["SinkFacility" ,SinkFacility = { 
	        incommodity: [],
	        capacity: "capacity",
	        inventorysize: "inventorysize",
	        commodprice: "commodprice"
    }],
    
 	["SourceFacility", SourceFacility = {
 			outcommodity: [],
        	capacity: [],
        	inventorysize: [],
        	commodprice: "commodprice",
        	recipe: "string"
	}]
]
var customRegion2 = [
     ["CapacityRegion", CapacityRegion = {
 	 	input_file: "string",
    	capacitydemand: [
    		{
	        	capacitytype: "string",
	            capacityfunction: "string",
	            nominalvalue: "double",
          		replacementlist: [{
	            	replacementfacility: "string"
            	}]
            }
        ]
    }]
]

var customInst2 = [
    ["FixedInst", FixedInst = {
        facility:
        	[{
            	type: "string",
          		name: "string"
          	}]
    }]
]

var customMark2 = [
    "NullMarket",
    "GreedyMarket"
]
