/* Institution Javascript Functions */

var NAME;
var nameStore = {};

function newInstitutionForm(){
	$('#form_sandbox div form').empty();
	$('#sandbox_1 form').append('<p> Institution Name:  <input type = "text" name = "instituion_name"/> </p>');
	$('#submit_area > form').append('<button name = "submit_New_Institution" type="submit" onClick="openNewInstitutionForm()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';			
}
function openNewInstitutionForm(){
	window.NAME = document.getElementById('sandbox_form')[0].value;
	nameStoreConvert(window.NAME);
	$('#sandbox_form').empty();
	$('.institution_type > ul').prepend('<li id = "' + nameStore[window.NAME] + '" class = "institution"><a>' + window.NAME +  '</a><ul>');
	updateSidebar();
	document.getElementById('wrapper').style.display = 'none';			
	document.getElementById('wrapper').style.display = 'block';
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