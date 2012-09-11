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
	alert("Cycic Version: 9001!!!!!! \nConstructed by the Cyclus Team:\n		Frodo \n		Legolas \n		Gollum" );
}
function switchToInfo(){
	document.getElementById('fuelCycleInfo').style.display = 'block';
	document.getElementById('variableWindow').style.display = 'none';
	document.getElementById('timeline').style.display = 'none';
}
function switchToVWindow(){
	document.getElementById('fuelCycleInfo').style.display = 'none';
	document.getElementById('variableWindow').style.display = 'block';
	document.getElementById('timeline').style.display = 'none';
}
function switchToTimeline(){
	document.getElementById('fuelCycleInfo').style.display = 'none';
	document.getElementById('variableWindow').style.display = 'none';
	document.getElementById('timeline').style.display = 'block';
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
