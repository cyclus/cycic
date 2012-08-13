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
	$('#form_sandbox div form').empty();
	$('#sandbox_1 form').prepend('<p> Facility Name:  <input type = "text" name = "region_name"/> </p>');			
	$('#sandbox_1 form').append('<p> Region: <select id = "region_select"> </select> </p>');
	$('#sandbox_1 form').append('<p> Institution: <select id = "institution_select"> </select> </p>');
	$('#sandbox_1 form').append('<p> Facility Type: <select id = "facility_select"> </select> </p>');
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
	for(i = 0; i < $('.custom_facilities ul li').length-1; i ++){
		$('#facility_select').append('<option value ="'+$('.custom_facilities ul li')[i].className+'">' + nameStoreBack[$(".custom_facilities ul li")[i].className] +'</option>');
		if(i = $('.custom_facilities ul li').length-1){
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
	window.REGION = document.getElementById('sandbox_form')[1].value;
	window.INSTITUTION = document.getElementById('sandbox_form')[2].value;
	window.MODEL = document.getElementById('sandbox_form')[3].value;
	$('#form_sandbox div form').empty();
	$.getJSON("JavaScriptParser/sample_interface/storagefacility.json", function(data){				
		for(var i =0; i < data[1].length; i++){
			if(data[1][i][1] == "incommodity"){
				$('#sandbox_1 form').append('<p> Market: <select name ="inMarket" id = "market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
				}
				/*$('#sandbox_1 form').append('<button onclick="addNewCommodity()" type ="button"> Add Additional Commodity </button>');*/
				i++;
			}
			if(data[1][i][1] == "outcommodity"){
				$('#sandbox_1 form').append('<p> Market: <select name ="outMarket" id = "market_out_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
				}
				i++;
			}
			/*if(data[1][i][1][1][0] == "entry"){
				$('#sandbox_1 form').append('<p> Initial Stocks: </p>');
				for(ii = 0; ii < data[1][i][1][1][1][1].length; ii++){
					$('#sandbox_1 form').append('<p style="text-indent : 20px">' + data[1][i][1][1][1][ii][0].toUpperCase() + '<input type="text" name ="' + data[1][i][1][1][1][ii][0] +'" value ="' + data[1][i][1][1][1][ii][1] + '"/> </p>');
				}
				if(i < data[1].length){
					i++;
				}
			}*/
			$('#sandbox_1 form').append('<p>' + data[1][i][0].toUpperCase() + '<input type="text" name ="' + data[1][i][0] +'" value ="' + data[1][i][1] + '"/> </p>');
		}
		$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	});
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
	window.TYPE = "green";
	$('#form_sandbox div form').empty();
	for(attribute in facilities[facility]){
		var STR = attribute;
		window.NAME = facility;
		if(attribute === "Name"){
			$('#sandbox_1 form').append('<p>' + toTitleCase(attribute) + '<input type="text" name ="' + attribute +'" value ="' + nameStoreBack[facilities[facility][STR]] + '"/>');
			continue;	
		}
		if(attribute === "inMarket"){
			$('#sandbox_1 form').append('<p> Market: <select name ="inMarket" id = "market_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				if(facilities[facility]['inMarket'] === $('.market_type ul li')[ii].id){
					$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
				else{
					$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
				}
			}
			continue;
		}
		if(attribute === "outMarket"){
			$('#sandbox_1 form').append('<p> Market: <select name ="outMarket" id = "market_out_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				if(facilities[facility]['outMarket'] === $('.market_type ul li')[ii].id){
					$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
				else{
					$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
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
		if(attribute === "state"){
			continue;
		}
		$('#sandbox_1 form').append('<p>' + toTitleCase(attribute) + '<input type="text" name ="' + attribute +'" value ="' + toTitleCase(facilities[facility][STR]) + '"/>');
	}
	if(facilities[facility]['circle']['call'] == "fac"){
		$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="cloneFacilityForm()"> Clone Facility </button>');
	}
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
	
}

function printoutFacility(){
	
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var facility = {};
		facility['Name'] = nameStore[window.NAME];
		facility['Region'] = window.REGION;
		facility['Institution'] = window.INSTITUTION;
		facility['model'] = window.MODEL
		facility['state'] = "open"
		for(i=0; i<length_2-1; i++){
			facility[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;
		}
		facility['circle'] = {
			name: nameStore[window.NAME],
  			type: d3.svg.symbol("circle"),
    		size: 50,
    		x: 100,
    		y: 100,
    		id: nameStore[window.NAME],
    		call: "fac",
		};
		facility['circle']['children'] = [];
		delete facility[""];
		delete facility['submit_Facility'];
		return facility;		
	}
	
	if(!facilities[nameStore[window.NAME]]){
		window.facilities[nameStore[window.NAME]] = testing();
		$('#'+facilities[nameStore[window.NAME]].Region +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.' + window.NAME+'.Name)" >' + (window.NAME));
		$('#'+facilities[nameStore[window.NAME]].Institution +' ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "openFacilityForm(facilities.' + window.NAME+'.Name)" >' + window.NAME);
		addParentCircle();
		updateSidebar();
	}else{
		window.facilities[nameStore[window.NAME]] = testing();
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
	$('#form_sandbox div form').empty();
	$('#sandbox_1 form').prepend('<p> Facility Name:  <input type = "text" name = "region_name"/> </p>');
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="newCloneFacilityForm()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}

function newCloneFacilityForm(){
	window.PARENT = window.NAME;
	window.NAME = document.getElementById('sandbox_form')[0].value;
	nameStoreConvert(window.NAME)
	facilities[nameStore[window.NAME]] = $.extend(true, {}, facilities[window.PARENT])
	facilities[nameStore[window.NAME]]['Name'] = nameStore[window.NAME];
	facilities[nameStore[window.NAME]]['circle']['name'] = nameStore[window.NAME];
	facilities[nameStore[window.NAME]]['circle']['id'] = nameStore[window.NAME];
	facilities[nameStore[window.NAME]]['circle']['call'] = "child";
	var tempFacility = $.extend(true, {}, facilities[window.PARENT]['circle']);
	tempFacility['id'] = nameStore[window.NAME];
	tempFacility['name'] = nameStore[window.NAME];
	tempFacility['call'] = "child";
	facilities[window.PARENT]['circle']['children'].push(tempFacility);
	$('#form_sandbox div form').empty();	
	for(attribute in facilities[nameStore[window.NAME]]){
		var STR = attribute;
		if(attribute === "inMarket"){
			$('#sandbox_1 form').append('<p> Market: <select name ="inMarket" id = "market_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				if(facilities[nameStore[window.NAME]]['inMarket'] == $('.market_type ul li')[ii].id){
					$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id +'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
				}
				else{
					$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
				}
			}
			continue;
		}
		if(attribute === "outMarket"){
			$('#sandbox_1 form').append('<p> Market: <select name ="outMarket" id = "market_out_select"> </select> </p>');
			for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
				if(facilities[nameStore[window.NAME]]['outMarket'] == $('.market_type ul li')[ii].id){
					$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id +'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
				}
				else{
					$('#market_out_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
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
		if(attribute === "state"){
			continue;
		}
		$('#sandbox_1 form').append('<p>' + toTitleCase(attribute) + '<input type="text" name ="' + attribute +'" value ="' + toTitleCase(facilities[nameStore[window.NAME]][STR]) + '"/>');
	}
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="addCloneCircle()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
	
}