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
