var facilities = {};
var customFacilities = {};
var MARKETS = {};
var NAME;
var REGION;
var INSTITUTION;
var TYPE;
var links = [];

function newFacilityForm(){
	$('#sandbox_1 form p, button, select, input, br, b, option').replaceWith('');
	$('#sandbox_1 form').prepend('<p> Facility Name:  <input type = "text" name = "region_name"/> </p>');			
	$('#sandbox_1 form').append('<p> Region: <select id = "region_select"> </select> </p>');
	$('#sandbox_1 form').append('<p> Institution: <select id = "institution_select"> </select> </p>');
	$('#sandbox_1 form').append('<p> Facility Type: <select id = "facility_select"> </select> </p>');
	for(i = 0; i < $('.region_type ul li').length-1; i ++){
		if($('.region_type ul li')[i].hasAttribute('class')===true){
			$('#region_select').append('<option value ="'+$('.region_type ul li')[i].id.toLowerCase()+'">' + $(".region_type ul li")[i].id.toUpperCase() +'</option>');
		}
	}
	for(i = 0; i < $('.institution_type ul li').length-1; i ++){
		if($('.institution_type ul li')[i].hasAttribute('class')===true){
			$('#institution_select').append('<option value ="'+$('.institution_type ul li')[i].id.toLowerCase()+'">' + $(".institution_type ul li")[i].id.toUpperCase() +'</option>');
		}
	}
	for(i = 0; i < $('.custom_facilities ul li').length-1; i ++){
		$('#facility_select').append('<option value ="'+$('.custom_facilities ul li')[i].className.toLowerCase()+'">' + $(".custom_facilities ul li")[i].className.toUpperCase() +'</option>');
		if(i = $('.custom_facilities ul li').length-1){
			$('#sandbox_1 form').append('<button name = "submit_New_Facility" type="submit" onClick="openNewFacilityForm()"> Submit </button>');
		}
	}
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';		
}

function openNewFacilityForm(){
	window.TYPE = "green";
	window.NAME = document.getElementById('sandbox_form')[0].value;
	window.REGION = document.getElementById('sandbox_form')[1].value;
	window.INSTITUTION = document.getElementById('sandbox_form')[2].value;
	$('#sandbox_1 form p, button, input, br, select, b, option').replaceWith('');
	$.getJSON("JavaScriptParser/sample_interface/storagefacility.json", function(data){				
		for(var i =0; i < data[1].length; i++){
			if(data[1][i][1] === "incommodity"){
				$('#sandbox_1 form').append('<p> Market: <select name ="MARKET" id = "market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
				}
			}
			else{
				$('#sandbox_1 form').append('<p>' + data[1][i][0].toUpperCase() + '<input type="text" name ="' + data[1][i][0] +'" value ="' + data[1][i][1] + '"/> </p>');
			}	
		}
		$('#sandbox_1 form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	});
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}

function openFacilityForm(facility){
	window.TYPE = "green";
	$('#sandbox_1 form p, button, input, br, select, b, option').replaceWith('');			
	for(attribute in facilities[facility]){
		var STR = attribute;
		window.NAME = facility;
		$('#sandbox_1 form').append('<p>' + attribute.toUpperCase() + '<input type="text" name ="' + attribute +'" value ="' + facilities[facility][STR] + '"/>');
	}
	$('#sandbox_1 form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}

function printoutFacility(){
	
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var facility = {};
		facility['Name'] = window.NAME;
		facility['Region'] = window.REGION;
		facility['Institution'] = window.INSTITUTION;
		for(i=0; i<length_2-1; i++){
			facility[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;
		}
		return facility;			
	}
	if(!facilities[window.NAME]){
		window.facilities[window.NAME] = testing();
		$('#'+facilities[window.NAME].Region +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.' + window.NAME+'.Name)" >' + facilities[window.NAME].Name);
		$('#'+facilities[window.NAME].Institution +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.' + window.NAME+'.Name)" >' + facilities[window.NAME].Name);
		addNewCircle();
	}
	window.facilities[window.NAME] = testing();
	return facilities;
}