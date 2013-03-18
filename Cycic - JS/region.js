/* Region Javascript Functions */

var NAME;
var REGIONS = {};


function newRegionForm(){
	$('#form_sandbox div form').empty();	
	$('#sandbox_1 form').append('<p> Region Name:  <input type = "text" name = "region_name"/> </p>');
	$('#sandbox_1 form').append('<p> Region Type: <select id = "region_select"> </select> </p>');
	for(i = 0; i < customRegion2.length; i ++){
		$('#region_select').append('<option value ="'+customRegion2[i][0]+'">' + customRegion2[i][0] +'</option>');
		if(i == customRegion2.length-1){
			$('#submit_area > form').append('<button name = "submit_New_Facility" type="submit" onClick="openNewRegionForm()"> Submit </button>');
		}
	}
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';			
}
function openNewRegionForm(){
	window.NAME = document.getElementById('sandbox_form')[0].value;
	window.MODEL = document.getElementById('sandbox_form')[1].value;
	nameStoreConvert(window.NAME);
	$('#sandbox_form').empty();
	$('#submit_area > form').empty();
	/*jsonReaderRegion('CapacityRegion');*/
	for(i = 0; i < customRegion2.length; i++){
		if(customRegion2[i][0] == window.MODEL){
			stor_i = i;
		}
	}
	if(!REGIONS[window.NAME]){
		REGIONS[window.NAME] = {}
	}
	JsonToRead(customRegion2[stor_i][1], window.MODEL, 'sandbox_form');	
	$('.region_type > ul').prepend('<li id = "' + nameStore[window.NAME] + '" class = "region"><a style ="cursor:hand; cursor:pointer" onclick ="populateFacilities('+nameStore[window.NAME]+')">' + window.NAME + '</a><ul>');
	var menu_ul_1 = $('.sidebar_menu > li > ul > li > ul');
	var menu_a_1 = $('.sidebar_menu > li > ul > li > a');
	updateSidebar();
	document.getElementById('wrapper').style.display = 'none';					
	document.getElementById('wrapper').style.display = 'block';
	$('#submit_area > form').append('<button name = "submit_New_Region" type="submit" onClick="printOutRegion()"> Submit </button>');
}
function printOutRegion(){
	$('#sandbox_form').empty();
}
function updateSidebar(){
	$('ul li ul').each(function(){
		$(this).prev('a').find('.total').find('div').remove()
	  	$(this).prev('a').find('.total').append('<div>'+ ($(this).find('li').length - $(this).find('li > ul > li').length - 1) +'</div>');
	});
}
