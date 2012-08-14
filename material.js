/* Material Javascript Functions */

var MATERIALS = {};
var NAME;

function newMaterialForm(){
	$('#form_sandbox div form').empty();	
	$('#sandbox_1 form').append('<p> Material Name:  <input type = "text" name = "material_name"/>');
	$('#sandbox_1 form').append('<p> Basis:  <input type = "text" name = "basis"/>');
	$('#sandbox_1 form').append('<p> Unit:  <input type = "text" name = "unit"/>');
	$('#sandbox_1 > form').append('<p id = "addIsotopeButton"> <button name = "addIsotope" type="button" onClick="addNewIsotope()"> Add Isotope </button>');
	$('#submit_area > form').append('<button name = "submit_New_Material" onClick="storeMaterial()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}	

function addNewIsotope(){
	$('#addIsotopeButton').before('Isotope Number: <input type = "text" name= "isotope" /> Amount: <input type = "text" name ="fraction" /> <br/>');
}

function storeMaterial(){
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var material = {};
		material['composition'] = {};
		window.NAME = document.getElementById('sandbox_form')[0].value
		for(i = 1; i < length_2-1; i++){
			if(document.getElementById('sandbox_form')[i].name == "fraction"){
				material['composition'][document.getElementById('sandbox_form')[i-1].value] = document.getElementById('sandbox_form')[i].value;
			}
			if(document.getElementById('sandbox_form')[i].name == "age"){
				material[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;
			}
			if(document.getElementById('sandbox_form')[i].name == "addIsotope"){
				i++;
			}
			if(document.getElementById('sandbox_form')[i].name == "unit" || document.getElementById('sandbox_form')[i].name == "basis"){
				material[document.getElementById('sandbox_form')[i].name] = document.getElementById('sandbox_form')[i].value;	
			}
		}
		material['total'] = 0;
		for(attribute in material['composition']){
			material['total'] = material['total'] + parseFloat(material['composition'][attribute]);
		}	
		return material;		
	}
	window.NAME = document.getElementById('sandbox_form')[0].value	
	if(!MATERIALS[window.NAME]){
		window.MATERIALS[window.NAME] = testing();
	}
}

