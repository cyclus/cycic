/* Facilitiy Javascript Functions */

var facilities = {};
var customFacilities = {};
var MARKETS = {};
var NAME;
var REGION;
var INSTITUTION;
var TYPE;
var MODEL;
var links = [];
var PARENT;

function newFacilityForm(){
	$('#form_sandbox form').empty();
	$('#sandbox_1 form').prepend('<p> Facility Name:  <input type = "text" name = "region_name"/> </p>');			
	$('#sandbox_1 form').append('<p> Facility Type: <select id = "facility_select"> </select> </p>');

	for(i = 0; i < customFac2.length; i ++){
		$('#facility_select').append('<option value ="'+customFac2[i][0]+'">' + customFac2[i][0] +'</option>');
		if(i == customFac.length-1){
			$('#submit_area > form').append('<button name = "submit_New_Facility" type="submit" onClick="openNewFacilityForm()"> Submit </button>');
		}
	}
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';		
}

function openNewFacilityForm(){
	window.TYPE = "green";
	window.NAME = document.getElementById('sandbox_form')[0].value;
	nameStoreConvert(window.NAME);
	window.NAME = nameStore[window.NAME];
	window.MODEL = document.getElementById('sandbox_form')[1].value;
	$('#form_sandbox div form').empty();
	/*jsonReaderFac(window.MODEL);*/
	for(i = 0; i < customFac2.length; i++){
		if(customFac2[i][0] == window.MODEL){
			stor_i = i;
		}
	}
	JsonToRead(customFac2[stor_i][1], window.MODEL, 'sandbox_form');
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}
function addNewCommodity(){
	$('#market_select').insertAfter('<p> In Market: <select name ="inMarket" id = "market_select"> </select> </p>');
	for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){			
				$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack$(".market_type ul li")[ii].id +'</option>');
	}
	$('#sandbox_1 form').append('<button onclick="addNewInMarket()"> Add Additional Commodity </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
	
}
function openFacilityForm(facility){
	if(document.getElementById('Facility_View').style.display == "block"){
		if(facilities[facility]['circle']['call'] == "fac"){
			displayingFacility(facility);
		}
	}
	window.TYPE = "green";
	$('#form_sandbox div form').empty();
	JsonToRead(facilities[facility], window.MODEL, 'sandbox_form');
	window.NAME = facility;
	if(facilities[facility]['circle']['call'] == "fac"){
		$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="cloneFacilityForm()"> Build Instance </button>');
	}
	if(facilities[facility]['circle']['call'] == "child"){
		$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printoutClone()"> Submit </button>');
	}else{
		$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	}
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}

function printoutFacility(){
	
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var facility = {};
		facility['Name'] = window.NAME;
		facility['model'] = window.MODEL
		facility['state'] = "open"
		objectBuilder($('#sandbox_1'), facility)
		facility['circle'] = {
			name: window.NAME,
  			type: d3.svg.symbol("circle"),
    		size: 50,
    		x: 100,
    		y: 100,
    		id: window.NAME,
    		call: "fac",
		};
		facility['circle']['children'] = [];
		delete facility[''];
		delete facility[""];
		delete facility['submit_Facility'];
		return facility;		
	}
	
	if(!facilities[window.NAME] && !facilities[nameStore[window.NAME]]){
		window.facilities[window.NAME] = testing();
		$('#'+facilities[window.NAME].Region +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.'+nameConvert(window.NAME)+'.Name)" >' + window.NAME);
		$('#'+facilities[window.NAME].Institution +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.'+nameConvert(window.NAME)+'.Name)" >' + window.NAME);
		$('#facility_types ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.'+nameConvert(window.NAME)+'.Name)" >' + window.NAME);
		addParentCircle();
		objectBuilder($('#sandbox_1'), facilities[window.NAME])
		updateSidebar();
	}else{
		window.facilities[window.NAME] = testing();
		updateLinks();
	}
	
	return facilities;
}

function updateSidebar(){
	$('ul li ul').each(function(){
		$(this).prev('a').find('.total').find('div').remove()
	  	$(this).prev('a').find('.total').append('<div>'+ ($(this).find('li').length - $(this).find('li > ul > li').length - 1) +'</div>');
	});
}

// Clone Facilities // 
function cloneFacilityForm(){
	$('#form_sandbox div form').empty();
	$('#sandbox_1 form').prepend('<p> Facility Name:  <input type = "text" name = "facility_name"/> </p>');
	$('#sandbox_1 form').append('<p> Region: <select id = "region_select"> </select> </p>');
	$('#sandbox_1 form').append('<p> Institution: <select id = "institution_select"> </select> </p>');
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="newCloneFacilityForm()"> Submit </button>');
	for(i = 0; i < $('.region_type ul li').length-1; i ++){
		if($('.region_type ul li')[i].hasAttribute('class')===true){
			$('#region_select').append('<option value ="'+$('.region_type ul li')[i].id+'">' + nameStoreBack[$('.region_type ul li')[i].id] +'</option>');
		}
	}
	for(i = 0; i < $('.institution_type ul li').length-1; i ++){
		if($('.institution_type ul li')[i].hasAttribute('class')===true){
			$('#institution_select').append('<option value ="'+$('.institution_type ul li')[i].id+'">' + nameStoreBack[$(".institution_type ul li")[i].id] +'</option>');
		}
	}
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}

function newCloneFacilityForm(){
	window.PARENT = window.NAME;
	window.NAME = document.getElementById('sandbox_form')[0].value;
	nameStoreConvert(window.NAME)
	window.NAME = nameStore[window.NAME];
	window.MODEL = facilities[window.PARENT]['model']
	facilities[window.NAME] = $.extend(true, {}, facilities[window.PARENT])
	facilities[window.NAME]['Name'] = window.NAME;
	facilities[window.NAME]['circle']['name'] = window.NAME;
	facilities[window.NAME]['circle']['id'] = window.NAME;
	facilities[window.NAME]['circle']['call'] = "child";
	facilities[window.NAME]['circle']['size'] = 40;
	var tempFacility = $.extend(true, {}, facilities[window.PARENT]['circle']);
	tempFacility['id'] = window.NAME;
	tempFacility['name'] = window.NAME;
	tempFacility['call'] = "child";
	tempFacility['size'] = 40;
	facilities[window.PARENT]['circle']['children'].push(tempFacility);
	$('#form_sandbox div form').empty();
	JsonToRead(facilities[window.NAME], window.MODEL, 'sandbox_form');
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="addCloneCircle()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';	
}
function printoutClone(){
	
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var facility = {};
		facility['Name'] = window.NAME;
		facility['Region'] = window.REGION;
		facility['Institution'] = window.INSTITUTION;
		facility['model'] = window.MODEL
		facility['state'] = "open"
		objectBuilder($('#sandbox_1'), facility)
		facility['circle'] = {
			name: window.NAME,
  			type: d3.svg.symbol("circle"),
    		size: 40,
    		x: 100,
    		y: 100,
    		id: window.NAME,
    		call: "child",
		};
		facility['circle']['children'] = [];
		delete facility[""];
		delete facility['submit_Facility'];
		return facility;		
	}
	window.facilities[window.NAME] = testing();
	updateLinks();
	if(!facilities[window.NAME] && !facilities[nameStore[window.NAME]]){
		/*$('#'+facilities[window.NAME].Region +' ul li ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(' + nameStore[window.NAME]+')" >' + (window.NAME));
		$('#'+facilities[window.NAME].Institution +' ul li ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(' + nameStore[window.NAME]+')" >' + window.NAME);*/
		updateSidebar();
	}else{
		updateLinks();
	}
	
	return facilities;
}
