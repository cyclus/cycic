/* Material Javascript Functions */

var MATERIALS = {};
var NAME;

function newMaterialForm(){
	$('#form_sandbox div form').empty();	
	$('#sandbox_1 form').append('<p> Material Name:  <input type = "text" name = "material_name"/> </p> <p> Composition </p>');
	$('#sandbox_1 > form').append('<button id = "addIsotopeButton" name = "addIsotope" type="button" onClick="addNewIsotope()"> Add Isotope </button>');
	$('#sandbox_1 > form').append('<p> Age: <input type = "text" name = "age" />');
	$('#submit_area > form').append('<button name = "submit_New_Material" type="submit" onClick="storeMaterial()"> Submit </button>');
	document.getElementById('sandbox_1').style.display = 'none';					
	document.getElementById('sandbox_1').style.display = 'block';
}	

function addNewIsotope(){
	$('#addIsotopeButton').before('Isotope Number: <input type = "text" name= "isotope" /> Fraction: <input type = "text" name ="fraction" />');
}

function storeMaterial(){
	function testing(){
		var length_2 = document.getElementById('sandbox_form').length;
		var material = {};
		window.NAME = document.getElementById('sandbox_form')[0].value
		material['Name'] = window.NAME;
		/*for(i=0; i<length_2-1; i++){
			if(document.getElementById('sandbox_form')[i].name == "fraction"){
				material[document.getElementById('sandbox_form')[i-1].value] = document.getElementById('sandbox_form')[i].value;
			}
			if(document.getElementById('sandbox_form')[i].name == "age"){
				material[document.getElementById('sandbox_form')[i].value] = document.getElementById('sandbox_form')[i].value;
			}
		}*/

		return material;		
	}
	
	if(!MATERIALS[window.NAME]){
		window.MATERIALS[window.NAME] = testing();
	}
}

