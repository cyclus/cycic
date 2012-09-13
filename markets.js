/* Market Javascript Functions */

var facilities = {};
var customFacilities = {};
var MARKETS = {};
var NAME;
var REGION;
var INSTITUTION;
var links =[];
var TYPE;
var MODEL;
var PASS_I;
function newMarketForm(){
	$('#form_sandbox form').empty();
	$('#sandbox_1 form').append('<p> Market Name:  <input type = "text" name = "market_name"/> </p>');
	$('#submit_area > form').append('<button name = "submit_New_Market" type="submit" onClick="openNewMarketForm()"> Submit </button>');
	$('#sandbox_1 form').append('<p> Market Type: <select id = "market_type_select"> </select> </p>');
	for(i = 0; i < customMark2.length; i ++){
		$('#market_type_select').append('<option value ="'+customMark2[i]+'">' + customMark2[i] +'</option>');
	}
	$('#sandbox_1 form').append('<p> mktcommdoity: <select id = "market_commod_select"> </select> </p>');
	for(mat in COMMODS){
		$('#market_commod_select').append('<option value ="'+mat+'">' + mat +'</option>');
	}
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';			
}
function openNewMarketForm(){
	window.TYPE = "market"
	window.NAME = document.getElementById('sandbox_form')[0].value;
	window.PASS_I = document.getElementById('sandbox_form')[2].value;
	window.MODEL = document.getElementById('sandbox_form')[1].value;
	nameStoreConvert(window.NAME);
	window.NAME = nameStore[window.NAME];
	$('#form_sandbox div form').empty();
	$('.market_type ul').prepend('<li id = "' + window.NAME + '"><a style ="cursor:hand; cursor:pointer">' + nameStoreBack[window.NAME]);
	updateSidebar();
	document.getElementById('wrapper').style.display = 'none';					
	document.getElementById('wrapper').style.display = 'block';
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printOutMarket()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}
function printOutMarket(){

	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var market = {};
		market['Market Name'] = nameStore[window.NAME];
		market['model'] = window.MODEL;
		market['mktcommodity'] = window.PASS_I;
		for(i=0; i<length_2; i++){
			market[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;
		}
		market['circle'] = {
			name: window.NAME,
  			type: d3.svg.symbol("square"),
    		size: 40,
    		x: 100,
    		y: 100,
    		id: window.NAME,
    		call: "mark"
		};
		return market;		
	}
	if(!MARKETS[window.NAME]){
		window.MARKETS[window.NAME] = testing();
		addParentCircle();
	}
	window.MARKETS[window.NAME] = testing();
}
function openMarketForm(facility){
	window.TYPE = "market";
	$('#form_sandbox div form').empty();		
	for(attribute in MARKETS[facility]){
		var STR = attribute;
		window.NAME = facility;
		if(attribute === "circle"){
			continue;
		}
		$('#sandbox_1 form').append('<p>' + attribute + '<input type="text" name ="' + attribute +'" value ="' + MARKETS[facility][STR] + '"/>');
	}
	$('#submit_area > form').append('<button name = "submit_Facility" type="button" onClick="printoutFacility()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
	
}
function updateSidebar(){
	$('ul li ul').each(function(){
		$(this).prev('a').find('.total').find('div').remove()
	  	$(this).prev('a').find('.total').append('<div>'+ ($(this).find('li').length - $(this).find('li > ul > li').length - 1) +'</div>');
	});
}
