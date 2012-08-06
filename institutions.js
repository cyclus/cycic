/* Institution Javascript Functions */

var NAME;


function newInstitutionForm(){
	$('#sandbox_form').empty();	
	$('#sandbox_1 form').append('<p> Institution Name:  <input type = "text" name = "instituion_name"/> </p>');
	$('#sandbox_1 form').append('<button name = "submit_New_Institution" type="submit" onClick="openNewInstitutionForm()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';			
}
function openNewInstitutionForm(){
	window.NAME = document.getElementById('sandbox_form')[0].value;
	$('#sandbox_form').empty();	
	$('.institution_type > ul').prepend('<li id = "' + window.NAME.trim() + '" class = "institution"><a>' + window.NAME +  '</a><ul>');
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
function toTitleCase(str){
	return str.replace(/\w\S*/g, function(txt){
		return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
	});
}