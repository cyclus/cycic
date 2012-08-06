/* Facilitiy Javascript Functions */

var facilities = {};
var customFacilities = {};
var MARKETS = {};
var NAME;
var REGION;
var INSTITUTION;
var TYPE;
var links = [];
var PARENT;

function newFacilityForm(){
	$('#sandbox_form').empty();	
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
	$('#sandbox_form').empty();	
	$.getJSON("JavaScriptParser/sample_interface/storagefacility.json", function(data){				
		for(var i =0; i < data[1].length; i++){
			if(data[1][i][1] == "incommodity"){
				$('#sandbox_1 form').append('<p> In Market: <select name ="inMarket" id = "market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
				}
				/*$('#sandbox_1 form').append('<button onclick="addNewInMarket()" type ="button"> Add Additional Incommodity </button>');*/
				i++;
			}
			if(data[1][i][1] == "outcommodity"){
				$('#sandbox_1 form').append('<p> Out Market: <select name ="outMarket" id = "market_out_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
				}
				/*$('#sandbox_1 form').append('<button onclick="addNewOutMarket()"> Add Additional Outcommodity </button>');*/
				i++;
			}
			if(data[1][i][1][1][0] == "entry"){
				$('#sandbox_1 form').append('<p> Initial Stocks: </p>');
				for(ii = 0; ii < data[1][i][1][1][1][1].length; ii++){
					$('#sandbox_1 form').append('<p style="text-indent : 20px">' + data[1][i][1][1][1][ii][0].toUpperCase() + '<input type="text" name ="' + data[1][i][1][1][1][ii][0] +'" value ="' + data[1][i][1][1][1][ii][1] + '"/> </p>');
				}
				if(i < data[1].length){
					i++;
				}
			}
			$('#sandbox_1 form').append('<p>' + data[1][i][0].toUpperCase() + '<input type="text" name ="' + data[1][i][0] +'" value ="' + data[1][i][1] + '"/> </p>');
		}
		$('#sandbox_1 form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	});
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}
function addNewInMarket(){
	$('#market_select').insertAfter('<p> In Market: <select name ="inMarket" id = "market_select"> </select> </p>');
	for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){			
				$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
	}
	$('#sandbox_1 form').append('<button onclick="addNewInMarket()"> Add Additional Incommodity </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
	
}
function openFacilityForm(facility){
	window.TYPE = "green";
	$('#sandbox_form').empty();			
	for(attribute in facilities[facility]){
		var STR = attribute;
		window.NAME = facility;
		if(attribute === "inMarket"){
			$('#sandbox_1 form').append('<p> Market: <select name ="inMarket" id = "market_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				if(facilities[facility]['inMarket'].toLowerCase() === $('.market_type ul li')[ii].id.toLowerCase()){
					$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'" selected = "selected">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
					}
				else{
					$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
				}
			}
			continue;
		}
		if(attribute === "outMarket"){
			$('#sandbox_1 form').append('<p> Out Market: <select name ="outMarket" id = "market_out_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				if(facilities[facility]['outMarket'].toLowerCase() === $('.market_type ul li')[ii].id.toLowerCase()){
					$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'" selected = "selected">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
					}
				else{
					$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
				}
			}
			continue;
		}
		if(attribute === "circle"){
			continue;
		}
		if(attribute === "children"){
			continue;
		}
		$('#sandbox_1 form').append('<p>' + toTitleCase(attribute) + '<input type="text" name ="' + attribute +'" value ="' + toTitleCase(facilities[facility][STR]) + '"/>');
	}
	$('#sandbox_1 form').append('<button name = "submit_Facility" type="button" onClick="cloneFacilityForm()"> Clone Facility </button>');
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
		facility['circle'] = {
			name: window.NAME,
  			type: d3.svg.symbol("circle"),
    		size: 50,
    		x: 100,
    		y: 100,
    		id: window.NAME
		};
		facility['circle']['children'] = [];
		delete facility[""];
		delete facility['submit_Facility'];
		return facility;		
	}
	
	if(!facilities[window.NAME]){
		window.facilities[window.NAME] = testing();
		$('#'+facilities[window.NAME].Region +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.' + window.NAME+'.Name)" >' + toTitleCase(facilities[window.NAME].Name));
		$('#'+facilities[window.NAME].Institution +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.' + window.NAME+'.Name)" >' + toTitleCase(facilities[window.NAME].Name));
		addParentCircle();
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
function toTitleCase(str){
	return str.replace(/\w\S*/g, function(txt){
		return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
	});
}

// Clone Facilities // 
function cloneFacilityForm(){
	$('#sandbox_form').empty();	
	$('#sandbox_1 form').prepend('<p> Facility Name:  <input type = "text" name = "region_name"/> </p>');
	$('#sandbox_1 form').append('<button name = "submit_Facility" type="button" onClick="newCloneFacilityForm()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}

function newCloneFacilityForm(){
	window.PARENT = window.NAME;
	window.NAME = document.getElementById('sandbox_form')[0].value;
	facilities[window.NAME] = $.extend(true, {}, facilities[window.PARENT])
	var tempFacility = $.extend(true, {}, facilities[window.PARENT]['circle']);
	facilities[window.PARENT]['cirlce']['children'].push(tempFacility);
	$('#sandbox_form').empty();	
	for(i = 0; i < facilities[window.PARENT].children.length; i++){
		if(facilities[window.PARENT].children[i].Name === window.NAME){
			var facilityPass = i;
		}
	}			
	for(attribute in facilities[window.PARENT].children[facilityPass]){
		var STR = attribute;
		if(attribute === "inMarket"){
			$('#sandbox_1 form').append('<p> Market: <select name ="inMarket" id = "market_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id.toLowerCase()+'">' + $(".market_type ul li")[ii].id.toUpperCase() +'</option>');
			}
			continue;
		}
		if(attribute === "circle"){
			continue;
		}
		if(attribute === "children"){
			continue;
		}
		$('#sandbox_1 form').append('<p>' + toTitleCase(attribute) + '<input type="text" name ="' + attribute +'" value ="' + toTitleCase(facilities[facility][STR]) + '"/>');
	}
	$('#sandbox_1 form').append('<button name = "submit_Facility" type="button" onClick="addCloneCircle()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
	
}