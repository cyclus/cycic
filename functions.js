// General Functions for a UI //

var nameStore = {};
var nameStoreBack = {}
nameStore['United States'] = 'UnitedStates';
nameStoreBack['UnitedStates'] = 'United States'
nameStore['Country of Texas'] = 'CountryofTexas';
nameStoreBack['CountryofTexas'] = 'Country of Texas'
nameStore['Constellation Energy'] = 'ConstellationEnergy';
nameStoreBack['ConstellationEnergy'] = 'Constellation Energy'
nameStore['STP'] = 'STP';
nameStoreBack['STP'] = 'STP'
// Output //
function outPut(){
	// Materials //
	for(mat in MATERIALS){
		JSON2XML(MATERIALS[mat]);
	}
	// Facilities //
	outFacilities = $.extend(true, {}, facilities);
	for(fac in outFacilities){
		if(outFacilities[fac]['circle']['call'] == "child"){
			delete outFacilities[fac]['circle'];
			delete outFacilities[fac]['state'];
			JSON2XML(outFacilities[fac]);
		}
	}
	// Markets //
	outcommoditys = $.extend(true, {}, MARKETS);
	for(mark in outcommoditys){
		delete outcommoditys[mark]['circle'];
	}
	// Regions //
	
	// Institutions //
	
	JSON2XML(outcommoditys);
	JSON2XML(REGIONS);
	JSON2XML(INSTITUTIONS);
}
function nameStoreConvert(name){
	nameStore[name] = nameConvert(name);
	nameStoreBack[nameConvert(name)] = name; 
}
function nameConvert(str){
	return str.replace(/[^a-zA-z 0-9]/g, "").replace(/ /g, "");	
}
function vegeta(){
	alert("Cycic Version: 9001!!!!!! \nConstructed by the Cyclus Team:\n		Frodo \n		Legolas \n		Gollum" );
}
function switchToInfo(){
	document.getElementById('fuelCycleInfo').style.display = 'block';
	document.getElementById('variableWindow').style.display = 'none';
	document.getElementById('timeline').style.display = 'none';
	document.getElementById('tableView').style.display = 'none';
}
function switchToVWindow(){
	document.getElementById('fuelCycleInfo').style.display = 'none';
	document.getElementById('variableWindow').style.display = 'block';
	document.getElementById('timeline').style.display = 'none';
	document.getElementById('tableView').style.display = 'none';
}
function switchToTimeline(){
	document.getElementById('fuelCycleInfo').style.display = 'none';
	document.getElementById('variableWindow').style.display = 'none';
	document.getElementById('timeline').style.display = 'block';
	document.getElementById('tableView').style.display = 'none';
}
function switchToTableView(){
	document.getElementById('fuelCycleInfo').style.display = 'none';
	document.getElementById('variableWindow').style.display = 'none';
	document.getElementById('timeline').style.display = 'none';
	document.getElementById('tableView').style.display = 'block';
}
function toFacilityView(){
	document.getElementById('sandbox_3').style.display = "none";
	document.getElementById('Facility_View').style.display = "block";
	force2.start(); 
}
function toFuelCyclView(){
	document.getElementById('sandbox_3').style.display = "block";
	document.getElementById('Facility_View').style.display = "none";
}
function callRobert(){
	alert("What? Did you really think I'd give you my number? \nFor questions please send an email to flanny65@gmail.com\nThanks!");
}
function saveFCDiagramPng(fcsvg){
    var pngCanvas = document.getElementById("pngCanvas");
    pngCanvas.width = fcsvg.width;
    pngCanvas.height = fcsvg.height;

    var fcsvgstr = new XMLSerializer().serializeToString(fcsvg);
    canvg(pngCanvas, fcsvgstr);
    var img = pngCanvas.toDataURL("image/png");
    var popupHTML = '<div align="center" style="width:'+pngCanvas.width+'px;height:'+(150+pngCanvas.height)+'">';
    popupHTML = popupHTML + '<h1>Right-click and select <i>"Save Target As."</i></h1>';
    popupHTML = popupHTML + '<h2><a href="'+img+'">png</a> or ';
    popupHTML = popupHTML + "<a href='data:image/svg+xml,"+fcsvgstr+"'>svg</a></h2>";
    popupHTML = popupHTML + '<img src="'+img+'"/>';
    popupHTML = popupHTML + '</div>';
    $.facybox(popupHTML);
}
function populateFacilities(passList){
	$('').replaceAll('.facilities_menu > li > ul > li');
	$("'#"+passList+" > ul > li'").clone().prependTo($('.facilities_menu > li > ul'));
	$('.facilities_menu > li > ul').append('<li><a style ="cursor:hand; cursor:pointer" onClick = "newFacilityForm()"> Add New Facility </a></li>');
}

function jsonReaderFac(fac){
	function readInput(array2){
		var test = true;
		console.log(dict);
		for(var i = 0; i < array2.length; i++){
			if(array2[i] instanceof Array){
				var test = false;
				for(var m = 0; m < i; m++){
					if(typeof array2[m] ==="string"){
						$('#sandbox_1 form').append('<p>'+array2[m]);
					}
				}
				readInput(array2[i]);

			}
		}
		if(test == true){
			$('#sandbox_1 form').append('<p>' + array2[0] + ':  <input type="text" name ="' + array2[0] +'" value ="' + array2[1] + '"/> </p>');
			dict.array2[0] = array2[1];
		}
	}
	for(i = 0; i < customFac.length; i++){
		if(customFac[i][0] === fac){
			var prototypeName = i;
		}
	}
	for(j = 0; j < customFac[prototypeName].length; j++){
		if(customFac[prototypeName][j] instanceof Array){
			readInput(customFac[prototypeName][j], facilities[window.NAME]);
		}
		else{
			$('#sandbox_1 form').append('<b>' + customFac[prototypeName][j]);
		} 
	}
}
function jsonReaderMark(fac){
	function readInput(array2){
		var test = true;
		for(var i = 0; i < array2.length; i++){
			if(array2[i] instanceof Array){
				var test = false;
				for(var m = 0; m < i; m++){
					if(typeof array2[m] ==="string"){
						$('#sandbox_1 form').append('<p>' + array2[m]);
					}
				}
				readInput(array2[i]);

			}
		}
		if(test == true){
			$('#sandbox_1 form').append('<p>' +array2[0] + ':  <input type="text" name ="' + array2[0] +'" value ="' + array2[1] + '"/>');
		}
	}
	for(i = 0; i < customMark.length; i++){
		if(customMark[i][0] === fac){
			var prototypeName = i;
		}
	}
	for(j = 0; j < customMark[prototypeName].length; j++){
		if(customMark[prototypeName][j] instanceof Array){
			var indentV = '    ';
			readInput(customMark[prototypeName][j]);
		}
		else{
			$('#sandbox_1 form').append('<b>' + customMark[prototypeName][j]);
		}
	}
}

function jsonReaderRegion(fac){
	function readInput(array2){
		var test = true;
		for(var i = 0; i < array2.length; i++){
			if(array2[i] instanceof Array){
				var test = false;
				for(var m = 0; m < i; m++){
					if(typeof array2[m] ==="string"){
						$('#sandbox_1 form').append('<p>' + array2[m]);
					}
				}
				readInput(array2[i]);

			}
		}
		if(test == true){
			$('#sandbox_1 form').append('<p>' + array2[0].toUpperCase() + ':  <input type="text" name ="' + array2[0] +'" value ="' + array2[1] + '"/> </p>');
		}
	}
	for(i = 0; i < customRegion.length; i++){
		if(customRegion[i][0] === fac){
			var prototypeName = i;
		}
	}
	for(j = 0; j < customRegion[prototypeName].length; j++){
		if(customRegion[prototypeName][j] instanceof Array){
			readInput(customRegion[prototypeName][j]);
		}
		else{
			$('#sandbox_1 form').append('<b>' + customRegion[prototypeName][j]);
		}
	}
}

function jsonReaderInst(fac){
	function readInput(array2){
		var test = true;
		for(var i = 0; i < array2.length; i++){
			if(array2[i] instanceof Array){
				var test = false;
				for(var m = 0; m < i; m++){
					if(typeof array2[m] ==="string"){
						$('#sandbox_1 form').append('<p>' + array2[m]);
					}
				}
				readInput(array2[i]);

			}
		}
		if(test == true){
			$('#sandbox_1 form').append('<p>' + array2[0].toUpperCase() + ':  <input type="text" name ="' + array2[0] +'" value ="' + array2[1] + '"/> </p>');
		}
	}
	for(i = 0; i < customInst.length; i++){
		if(customInst[i][0] === fac){
			var prototypeName = i;
		}
	}
	for(j = 0; j < customInst[prototypeName].length; j++){
		if(customInst[prototypeName][j] instanceof Array){
			readInput(customInst[prototypeName][j]);
		}
		else{
			$('#sandbox_1 form').append('<b>' + customInst[prototypeName][j]);
		}
	}
}

function JsonToRead(dict, name, form2){  
	var form = $('#'+form2)  
    for(var prop in dict){
        if(dict[prop] instanceof Array){
            if(dict[prop][0]){
            	form.append('<p><b>' + prop + ': <button onclick = "additionalMore('+name+'.'+prop+'[0], \''+prop+'\', \''+prop+'\')"> Add Additional '+prop+'</button><hr />')
            	for(i = 0; i < dict[prop].length; i++){
            		form.append('<hr />')
            		form.append('<form id = "'+prop+'"> <!-- //COMM </form>');
               		JsonToRead(dict[prop][i], prop, prop);
 
               		form.append('<hr />');
                }
            }else{
	        	if(prop == "incommodity"){
					form.append('<p> In Market: <select name ="incommodity" id = "in_market_select"> </select> </p>');
					for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						if(false){
							$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
						}
						else{
							$('#in_market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
						}
					}
				}
       			else if(prop == "outcommodity"){
					form.append('<p> Out Market: <select name ="outcommodity" id = "out_market_select"> </select> </p>');
					for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
						if(false){
							$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
						}
						else{
							$('#out_market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
						}
					}
				}
       			else if(prop == "recipe"){
					form.append('<p> Recipe: <select name ="recipe" id = "recipe_select"> </select> </p>');
					for(ii = 0; ii < $('.material_type ul li').length-1; ii ++){
						if(false){
							$('#recipe_select').append('<option value ="'+$('.material_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".material_type ul li")[ii].id] +'</option>');
						}
						else{
							$('#recipe_select').append('<option value ="'+$('.material_type ul li')[ii].id+'">' + nameStoreBack[$(".material_type ul li")[ii].id] +'</option>');
						}
					}
	        	}
	        	else if(prop == 'circle' || prop == 'state'){
	        		continue;
	        	}
	        	else{
	        		form.append('<p>' + prop + ':  <input type="text" name ="' + prop +'" value ="' + dict[prop] + '"/> </p>')
	        	}
            }
        }else{
        	if(prop == "incommodity"){
				form.append('<p> In Market: <select name ="incommodity" id = "in_market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
					if(false){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
					else{
						$('#in_market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
				}
				continue;
			}
       		else if(prop == "outcommodity"){
				form.append('<p> Out Market: <select name ="outcommodity" id = "out_market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
					if(false){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
					else{
						$('#out_market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
				}
				continue;
        	}
   			else if(prop == "recipe"){
				form.append('<p> Recipe: <select name ="recipe" id = "recipe_select"> </select> </p>');
				for(ii = 0; ii < $('.material_type ul li').length-1; ii ++){
					if(false){
						$('#recipe_select').append('<option value ="'+$('.material_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".material_type ul li")[ii].id] +'</option>');
					}
					else{
						$('#recipe_select').append('<option value ="'+$('.material_type ul li')[ii].id+'">' + nameStoreBack[$(".material_type ul li")[ii].id] +'</option>');
					}
				}
        	}
        	else if(prop == 'circle' || prop == 'state' || prop == 'model'){
        		continue;
        	}
        	else{
        		form.append('<p>' + prop + ':  <input type="text" name ="' + prop +'" value ="' + dict[prop] + '"/> </p>')
        	}
        }        
    }
}
function additionalMore(dict, prop2, form2){
	var form = $('#'+form2) 
	form.append('<hr id = "dotted"/>') 
    for(var prop in dict){
        if(dict[prop] instanceof Array){
            if(dict[prop][0]){
            	form.append('<p><b>' + prop + ': <button onclick = "additionalMore('+name+'.'+prop+'[0], \''+prop+'\')"> Add Additional '+prop+'</button><hr />')
            	for(i = 0; i < dict[prop].length; i++){
            		form.append('<hr/>')
               		JsonToRead(dict[prop][i], prop, prop);
               		form.append('<hr />')
                }
            }else{
            	form.append('<p>' + prop + ':  <input type="text" name ="' + prop +'" value ="' + prop + '"/> </p>')
            }
        }else{
        	if(prop == "incommodity"){
				form.append('<p> In Market: <select name ="incommodity" id = "in_market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
					if(false){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
					else{
						$('#in_market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
				}
				continue;
			}
       		else if(prop == "outcommodity"){
				form.append('<p> Out Market: <select name ="outcommodity" id = "out_market_select"> </select> </p>');
				for(ii = 0; ii < $('.market_type ul li').length-1; ii ++){
					if(false){
						$('#market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
					else{
						$('#out_market_select').append('<option value ="'+$('.market_type ul li')[ii].id+'">' + nameStoreBack[$(".market_type ul li")[ii].id] +'</option>');
					}
				}
				continue;
        	}
   			else if(prop == "recipe"){
				form.append('<p> Recipe: <select name ="recipe" id = "recipe_select"> </select> </p>');
				for(ii = 0; ii < $('.material_type ul li').length-1; ii ++){
					if(false){
						$('#recipe_select').append('<option value ="'+$('.material_type ul li')[ii].id+'" selected = "selected">' + nameStoreBack[$(".material_type ul li")[ii].id] +'</option>');
					}
					else{
						$('#recipe_select').append('<option value ="'+$('.material_type ul li')[ii].id+'">' + nameStoreBack[$(".material_type ul li")[ii].id] +'</option>');
					}
				}
        	}
        	else if(prop == 'circle' || prop == 'state' || prop == 'model'){
    			continue;
        	}
        	else{
        		form.append('<p>' + prop + ':  <input type="text" name ="' + prop +'" value ="' + dict[prop] + '"/> </p>')
        	}
        }        
    }
}


function objectBuilder(div, dict){
	function innerBuild(div, dict){
		for(var m = 0; m < div.length; m++){
			dict[div[m].id] = [{}]
			var first_variable = div[m][0].name;
			var pass_i = 0;
			for(var i = 0; i < div[m].length; i++){
			    if(i == 0){
			        dict[div[m].id][0][div[m][i].name] = div[m][i].value
			    }
			    if(i > 0){
			        if(div[m][i].name == first_variable){
			            pass_i = pass_i + 1;
			            dict[div[m].id].push({})
			        }
			        dict[div[m].id][pass_i][div[m][i].name] = div[m][i].value
			    }
			}
			if(div.parent().children('form')){
				for(var j = 0; j < div.children('form').length; j++){
					dict[div.children('form')[j].id] = [{}]
					objectBuilder(div.children('form'), dict[div.children('form')[j].id])
				}
			}
			if(dict['']){
				delete dict[''];
			}
		}
	}
	// FUNCTION //
	var div = div;
	for(var i = 0; i < div.children('form')[0].length; i++){
		dict[div.children('form')[0][i].name] = div.children('form')[0][i].value
	}
	if(div.children('form').children('form')){
		innerBuild(div.children('form').children('form'), dict);
	}
	delete dict[''];
}

function marketArrayBuilder(object){
	function marketNamePass(object){
		function passArray(array){
			for(var i = 0; i < array.length; i++){
				if(array[i] instanceof Array){
					passarray(array[i]);
				}
				else if(array[i] instanceof Object){
					marketNamePass(array[i]);
				}
			}
		}
		for(var prop in object){
			if(prop == "incommodity" || prop == "outcommodity"){
				marketArray.push(object[prop]);
			}
			else if(object[prop] instanceof Array){
				passArray(object[prop])
			}
		}
	}
	var marketArray = [];
	marketNamePass(object);
	return marketArray
}
