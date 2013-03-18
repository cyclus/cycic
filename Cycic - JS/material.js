/* Material Javascript Functions */

var MATERIALS = {};
var NAME;
var COMMODS = {}

function newMaterialForm(){
	$('#form_sandbox form').empty();	
	$('#sandbox_1 form').append('<p> Material Name:  <input type = "text" name = "material_name"/>');
	$('#sandbox_1 form').append('<p> Basis:  <select id = "basis" name = "basis"/>');
	$('#basis').append('<option value ="atom"> atom </option>');
	$('#basis').append('<option value ="mass"> mass </option>');
	$('#sandbox_1 form').append('<p> Unit:  <input type = "text" name = "unit"/>');
	$('#sandbox_1 form').append('<p> Total:  <input type = "text" name = "total"/>');
	$('#sandbox_1 > form').append('<p id = "addIsotopeButton"> <button name = "addIsotope" type="button" onClick="addNewIsotope()"> Add Isotope </button>');
	$('#submit_area > form').append('<button name = "submit_New_Material" onClick="storeMaterial()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}	

function addNewIsotope(){
	$('#addIsotopeButton').before('Isotope Number: <input type = "text" name= "isotope" value ="zzaaa(m)" /> Amount: <input type = "text" name ="fraction" /> <br/>');
}

function storeMaterial(){
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var material = {};
		material['composition'] = {};
		for(i = 1; i < length_2-1; i++){
			if(document.getElementById('sandbox_form')[i].name == "fraction"){
				material['composition'][document.getElementById('sandbox_form')[i-1].value] = document.getElementById('sandbox_form')[i].value;
			}
			if(document.getElementById('sandbox_form')[i].name == "addIsotope"){
				i++;
			}
			if(document.getElementById('sandbox_form')[i].name == "unit" || document.getElementById('sandbox_form')[i].name == "basis"){
				material[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;	
			}
			if(document.getElementById('sandbox_form')[i].name == "total"){
				material[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;	
			}
		}
		return material;	
	}
	window.NAME = document.getElementById('sandbox_form')[0].value
	nameConvert(window.NAME);
	if(!MATERIALS[window.NAME]){
		window.MATERIALS[window.NAME] = testing();
		$('.material_type > ul').prepend('<li id = "' + nameStore[window.NAME] + '" class = "recipe"><a style ="cursor:hand; cursor:pointer" onclick="openMaterialForm(&quot;'+window.NAME+'&quot;)">' + window.NAME + '</a><ul>');
		updateSidebar();
	}else{
		window.MATERIALS[window.NAME] = testing();
	}
}
function openMaterialForm(material){
	var material = material;
	$('#form_sandbox form').empty();	
	$('#sandbox_1 form').append('<p> Material Name:  <input type = "text" name = "material_name" value ="'+material+'"/>');
	$('#sandbox_1 form').append('<p> Basis:  <select id = "basis" name = "basis"/>');
	if(MATERIALS[material]['basis'] == "atom"){
		$('#basis').append('<option value ="atom" selected = "selected"> atom </option>');
		$('#basis').append('<option value ="mass"> mass </option>');
	}else{
		$('#basis').append('<option value ="atom"> atom </option>');
		$('#basis').append('<option value ="mass" selected = "selected"> mass </option>');
	}
	$('#sandbox_1 form').append('<p> Unit:  <input type = "text" name = "unit" value ="'+MATERIALS[material]['unit']+'"/>');
	$('#sandbox_1 form').append('<p> Total:  <input type = "text" name = "total" value ="'+MATERIALS[material]['total']+'"/>');
	$('#sandbox_1 > form').append('<p id = "addIsotopeButton"> <button name = "addIsotope" type="button" onClick="addNewIsotope()"> Add Isotope </button>');
	for(iso in MATERIALS[material]['composition']){
		$('#addIsotopeButton').before('Isotope Number: <input type = "text" name= "isotope" value ='+iso+' /> Amount: <input type = "text" name ="fraction" value ="'+MATERIALS[material]['composition'][iso]+'"/> <br/>');
	}
	$('#submit_area > form').append('<button name = "submit_New_Material" onClick="storeMaterial()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}
function addCommodity(){
	$('#form_sandbox form').empty();	
	$('#sandbox_1 form').append('<p> Commodity Name:  <input type = "text" name = "material_name"/>');
	$('#submit_area > form').append('<button name = "submit_New_Material" onClick="submitCommodity()"> Submit </button>');
}
function submitCommodity(){
	window.NAME = document.getElementById('sandbox_form')[0].value	
	if(!COMMODS[window.NAME]){
		COMMODS[window.NAME] = [];
		$('.commodity_type > ul').prepend('<li id = "' + nameStore[window.NAME] + '" class = "recipe"><a style ="cursor:hand; cursor:pointer">' + window.NAME + '</a><ul>');
		updateSidebar();
	}
}
