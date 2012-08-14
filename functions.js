// General Functions for a UI //

// Output //
var nameStore = {};
var nameStoreBack = {}

function outPutCommodities(){
	var Materials_JSON = JSON.stringify(window.MATERIALS)
	
}
function nameStoreConvert(name){
	nameStore[name] = nameConvert(name);
	nameStoreBack[nameConvert(name)] = name; 
}
function nameConvert(str){
	return str.replace(/[^a-zA-z 0-9]/g, "").replace(/ /g, "");	
}
function vegeta(){
	alert("Cycic Version: 9000.01!!!!!! \nConstructed by the Cyclus Team:\n   Frodo \n   Legolas \n   Gollum" );
}
function switchToInfo(){
	document.getElementById('fuelCycleInfo').style.display = 'block';
	document.getElementById('variableWindow').style.display = 'none';
}
function switchToVWindow(){
	document.getElementById('fuelCycleInfo').style.display = 'none';
	document.getElementById('variableWindow').style.display = 'block';
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
