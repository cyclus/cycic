/* Institution Javascript Functions */

var NAME;
var nameStore = {};
var INSTITUTIONS = {};

function newInstitutionForm(){
	$('#form_sandbox div form').empty();
	$('#sandbox_1 form').append('<p> Institution Name:  <input type = "text" name = "instituion_name"/> </p>');
	$('#sandbox_1 form').append('<p> Institution Type: <select id = "inst_select"> </select> </p>');
	for(i = 0; i < customRegion2.length; i ++){
		$('#inst_select').append('<option value ="'+customInst2[i][0]+'">' + customInst[i][0] +'</option>');
		if(i == customInst2.length-1){
			$('#submit_area > form').append('<button name = "submit_New_Facility" type="submit" onClick="openNewInstitutionForm()"> Submit </button>');
		}
	}

	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';			
}
function openNewInstitutionForm(){
	window.NAME = document.getElementById('sandbox_form')[0].value;
	window.MODEL = document.getElementById('sandbox_form')[1].value;
	nameStoreConvert(window.NAME);
	$('#sandbox_form').empty();
	$('#submit_area > form').empty();
	/*jsonReaderInst('FixedInst');*/
	for(i = 0; i < customInst.length; i++){
		if(customInst[i][0] == window.MODEL){
			stor_i = i;
		}
	}
	if(!INSTITUTIONS[window.NAME]){
		INSTITUTIONS[window.NAME] = {}
	}
	JsonToRead(customInst2[stor_i][1], window.MODEL, 'sandbox_form');
	$('.institution_type > ul').prepend('<li id = "' + nameStore[window.NAME] + '" class = "institution"><a>' + window.NAME +  '</a><ul>');
	updateSidebar();
	document.getElementById('wrapper').style.display = 'none';			
	document.getElementById('wrapper').style.display = 'block';
	$('#submit_area > form').append('<button name = "submit_New_Region" type="submit" onClick="printOutInstitution()"> Submit </button>');
}
function printOutInstitution(){
	$('#sandbox_form').empty();
}
function updateSidebar(){
	$('ul li ul').each(function(){
		$(this).prev('a').find('.total').find('div').remove()
	  	$(this).prev('a').find('.total').append('<div>'+ ($(this).find('li').length - $(this).find('li > ul > li').length - 1) +'</div>');
	});
}
function nameStore(name){
	nameStore[name] = [];
	nameStore[name].push(nameConvert(name));
	nameStore[name].push(name);
}
function nameConvert(str){
	return str.replace(/[^a-zA-z 0-9]/g, "").replace(/ /g, "");	
}