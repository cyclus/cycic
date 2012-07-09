function main(){
	var parser = new Parser("recipereactor.xml");
	//var parser = new Parser("sourcefacility.xml");
  //var parser = new Parser("file:///home/scopatz/cyclus/src/Models/Facility/StorageFacility/StorageFacility.rng");
  var parsed_obj = parser.parse_obj();
  parser.printObject(parsed_obj, 0, false);	
}

function mainGather(){
  //gatherSchemas('/home/scopatz/cyclus')
  gatherSchemas('../../cyclus')
}



function cycicXMLHttpRequest() {
  if (window.XMLHttpRequest) {
    var xhttp = new XMLHttpRequest()
  }
  else { // IE 5/6
    var xhttp = new ActiveXObject("Microsoft.XMLHTTP")
  }
  return xhttp
}

//
// Parser Class
//

function Parser(word){
	this.currentIndex = new Array();
	this.jsObjName = new Array();
	this.elementTrace = new Array();
	this.file = word;
}

Parser.prototype.speak = function() {
  document.write(this.word);
}
	
//Take XML and convert to JS object to be printed out.
Parser.prototype.parse_obj = function() {
  var xhttp = cycicXMLHttpRequest()
	xhttp.open("GET", this.file, false);
  xhttp.send();
  xmlDoc=xhttp.responseXML; 

  var x = xmlDoc.childNodes[0].childNodes;

  //First, start looking for the main parent name by searching for the 'define' tag
  for(n=0; n<x.length; n++){
    //If found, start printing out the children
    if (x[n].nodeName === 'define'){
		  var elements = x[n].childNodes[1].childNodes;
							
      this.jsObjName[x[n].childNodes[1].getAttribute('name')] = {};
      this.jsObjName[x[n].childNodes[1].getAttribute('name')] = this.parseObject(elements);
	  }
  }
  return this.jsObjName; 
}
	
Parser.prototype.print_obj = function(){
    var xhttp = cycicXMLHttpRequest()
		xhttp.open("GET",this.file,false);
		xhttp.send();
		xmlDoc=xhttp.responseXML; 
		
		var x = xmlDoc.childNodes[0].childNodes;
		//var this.currentIndex = new Array();
		
		//First, start looking for the main parent name by searching for the 'define' tag
		for(n=0; n<x.length; n++){
			//If found, start printing out the children
			if (x[n].nodeName === 'define'){
				var elements = x[n].childNodes[1].childNodes;
				document.write("[\"" + x[n].childNodes[1].getAttribute('name') + '\",<br>');

				if(x[n].childNodes[1].childNodes.length > 3){
					document.write('&nbsp&nbsp&nbsp&nbsp[<br>');
				}

				for(j=0; j<elements.length; j++){
					//Find all valid elements that need to be printed
					if(elements[j].nodeName != '#text'){
						if(elements[j].nodeName != '#comment'){
							//If it is just a tag, print out the node name
							if(elements[j].getAttribute('name') === null){
								document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'[\"'+elements[j].nodeName);
								
								
							}
							//If it is an element, then print its name
							else{
								if(elements[j].nodeName === 'ref'){
									document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'\"'+elements[j].getAttribute('name') + '\"');
									document.write(',<br>');
									
									continue;
								}
								else{
									document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'[\"'+elements[j].getAttribute('name'));
									
									
								}
							}
							
							//Does it have a ref tag? If so, we need to mark it to let everyone know
							//that this is just a temporary reference
							
							document.write('\",&nbsp');
							//Under each element name, print out all of its possible children
							this.printChildren(elements[j].childNodes, 2);
							
							if(j+3> elements.length){
								document.write(']<br>');
							}
							else{
								document.write('],<br>');
							}
							
							
						}
					}
				}
				if(x[n].childNodes[1].childNodes.length > 3){
					document.write('&nbsp&nbsp&nbsp&nbsp]<br>');
				}
				document.write('] <br>');
			}
		}
}

Parser.prototype.printChildren = function(a, spaceNumber, indexList) {
		var spaces = new Array();
		for (i=0; i<a.length; i++){
			//Extract only the element, not the text objects
			if(a[i].nodeName != '#text'){
				var currentElement = a[i];
				//Input appropriate amount of spaces to format output
								
				//If the child is a tag, print out just its children since it has no 'name' or 'type' attribute
				if(currentElement.getAttribute('name') === null){
					if(currentElement.getAttribute('type') === null){
						document.write('<br>');
						this.inputSpaces(spaceNumber + 1);						
						document.write('[\"' + currentElement.nodeName + '\",<br>');
						//If it is a reference, mark it
						this.inputSpaces(spaceNumber + 2);
											
						//Print all possible children of the tag
						this.printChildren(currentElement.childNodes, spaceNumber + 2);
						
						
						document.write(']<br>');
						
						this.inputSpaces(spaceNumber+1);
						document.write(']<br>');
						this.inputSpaces(spaceNumber);
						continue;
						//If everything is done, exit the for loop
						if (i = a.length){
							break;
						}	
						continue;
					}
					
				}
				
				//If the object has a 'name' attribute, print it
				if(currentElement.getAttribute('name')){
					if(i+ 3 > a.length){
						if(spaceNumber > 3){
							if(currentElement.nodeName != 'ref'){
								document.write('[\"'+ currentElement.getAttribute('name') + '\"');
							}
							else{
								document.write('\"'+ currentElement.getAttribute('name') + '\"');
							}
						}
						else{
							document.write('\"'+ currentElement.getAttribute('name') + '\"');
						}
					}
					else{
						if(currentElement.nodeName === 'ref'){
							document.write('\"'+ currentElement.getAttribute('name') + '\"');
						}
						else{
							document.write('[\"'+ currentElement.getAttribute('name') + '\"');
						}
					}
					//document.write('\"'+ currentElement.getAttribute('name') + '\"');
				}
				
				//If the object has a 'type' attribute, print it
				if(currentElement.getAttribute('type')){
					document.write('\"' + currentElement.getAttribute('type') + '\"');
				}
				//this.isRef(currentElement.nodeName);
				//document.write('<br>');
				if(currentElement.nodeName === 'ref'){
					if(spaceNumber >3){
						if(i + 3 > a.length){
							document.write(',<br>');
							this.inputSpaces(spaceNumber - 1);
						}
						else{
						document.write(',<br>');
						this.inputSpaces(spaceNumber);
						
						}
					}
				}
				
				//Check for its children's children. If it exists, recursively call printChildren
				if(this.hasChildren(currentElement)){
					//Push index into an array to keep track of
					
					if(currentElement.childNodes.length > 3){
						document.write(',<br>');
						this.inputSpaces(spaceNumber + 1);
						
						document.write('[<br>');
						this.inputSpaces(spaceNumber + 2);
						
						this.currentIndex.push(i);
						this.printChildren(currentElement.childNodes, spaceNumber + 2);
						
						if(this.currentIndex[this.currentIndex.length -1] + 3 > a.length){
							document.write(']<br>');
							
						}
						else{
							document.write('],<br>');

						}
						
						this.inputSpaces(spaceNumber);
						
					}
					else if(currentElement.childNodes.length === 3){
						document.write(',');
						this.currentIndex.push(i);
						this.printChildren(currentElement.childNodes, spaceNumber + 1);
						
						if(this.currentIndex[this.currentIndex.length -1] + 3 > a.length){
							document.write(']<br>');
							this.inputSpaces(spaceNumber - 1);
						}
						else{
							document.write('],<br>');
							this.inputSpaces(spaceNumber);
							
						}
					}
					
					
					//Print out the children's children
				
					//printChildren(currentElement.childNodes, spaceNumber + 1);
					
					//document.write(',');
					//Pop out the most recent index
					i = this.currentIndex.pop(i);
					
				}
			
								
			}
		}
}

//Helper function used to determine if a node has children or not
Parser.prototype.getChildrenNum = function(node) {
		var count = 0;
		for (var key in node){
			if (key) {
				count++;
			}
		}
		return count;
}

//Helper function used to print out 'num' amount of tabs for formatting purposes
Parser.prototype.inputSpaces = function(num) {
		for(k=0; k < num; k ++){
			document.write('&nbsp&nbsp&nbsp&nbsp');
		}
}

//Helper function to determine if the element is a reference or not
Parser.prototype.isRef = function(nodeName){
		if (nodeName === 'ref'){
			document.write('**');
		}
}

Parser.prototype.parseObject = function(a){
		var spaces = new Array();
		result = {};
		for (i=0; i<a.length; i++){
			//Extract only the element, not the text objects
			if(a[i].nodeName != '#text'){
				if(a[i].nodeName != '#comment'){
					var currentElement = a[i];
					//Input appropriate amount of spaces to format output
					
					//If the child is a tag, print out just its children since it has no 'name' or 'type' attribute
					
					
					//If the object has a 'name' attribute, print it
					if(currentElement.getAttribute('name')){
						this.currentIndex.push(i);
						
						result[currentElement.getAttribute('name')] = {};						
						this.elementTrace.push(result);
						tempList = this.parseObject(currentElement.childNodes);
						result = this.elementTrace.pop(result);
						result[currentElement.getAttribute('name')] = tempList;
						
						i = this.currentIndex.pop(i);
						//jsChildName2.push(currentElement.getAttribute('name'));
						//document.write('\"'+ currentElement.getAttribute('name') + '\"');
					}
					
					//If the object has a 'type' attribute, print it
					else if(currentElement.getAttribute('type')){
						this.currentIndex.push(i);	
						
						result[currentElement.getAttribute('type')] = {};
						this.elementTrace.push(result);
						tempList = this.parseObject(currentElement.childNodes);
						result = this.elementTrace.pop(result);
						result[currentElement.getAttribute('type')] = tempList;
						
						i = this.currentIndex.pop(i);
						//jsChildName2.push(currentElement.getAttribute('type'));
						//document.write('\"'+currentElement.getAttribute('type') + '\"');
					}
					else if(currentElement.getAttribute('name') === null){
						if(currentElement.getAttribute('type') === null){

							this.currentIndex.push(i);	
							
							result[currentElement.nodeName] = {};
							this.elementTrace.push(result);
							tempList = this.parseObject(currentElement.childNodes);
							result = this.elementTrace.pop(result);
							result[currentElement.nodeName] = tempList;
						
							i = this.currentIndex.pop(i);
							//parseObject(currentElement.childNodes, spaceNumber + 2);
							
							
						}
						
					}
					
					//Check for its children's children. If it exists, recursively call parseObject
					continue;
					/**
					if(this.hasChildren(currentElement)){
						//Push index into an array to keep track of
						this.currentIndex.push(i);				
						//Print out the children's children
						
						jsChildName2.push(tempArray);
						jsChildName2 = jsChildName2[count];
						count++;
						this.parseObject(currentElement.childNodes, tempArray);
						//Pop out the most recent index
						i = this.currentIndex.pop(i);
					}**/
				}
			}
		}
		return result;
}
	
Parser.prototype.getLength = function(object) {
	var length = 0;
	for (var key in object){
		length++;
	}
	return length;
}
	
Parser.prototype.printObject = function(parsed_object, spaces, justCheck) {
	var count = 0;
	if (justCheck == false){
		for (var key in parsed_object) {
			count++;
			if(this.getChildrenNum(parsed_object[key]) > 1){
				document.write("[" + key + ",<br>");
				this.inputSpaces(spaces + 1);
				document.write("[<br>");
				this.inputSpaces(spaces + 2);
				this.printObject(parsed_object[key], spaces + 2, false);
				this.inputSpaces(spaces + 1);
				document.write("]<br>");
				//this.inputSpaces(spaces);
				this.inputSpaces(spaces);
				document.write("]<br>");
				
			}
			else if (this.getChildrenNum(parsed_object[key]) == 1){
				//document.write("[" + key + ", " + this.printObject(parsed_object[key], spaces, true) + "],<br>");
				
				document.write("[" + key + ", ");
				length = this.getLength(parsed_object);
				tempKey = this.printObject(parsed_object[key], spaces, true);
				if (this.getChildrenNum(parsed_object[key][tempKey]) == 0){
					document.write(this.printObject(parsed_object[key], spaces, true));
					if(count == length){
						document.write("]<br>");
						//this.inputSpaces(spaces - 2);
					}
					else{
						document.write("],<br>");
						this.inputSpaces(spaces);
					}
				}
				else {
					document.write("<br>");
					this.inputSpaces(spaces +1);
					this.printObject(parsed_object[key], spaces + 1, false);
					this.inputSpaces(spaces);
					document.write("]<br>");
					//this.inputSpaces(spaces);
				}
				
			}
			//TODO: Check if this is the last element in the object; don't add a comma if so
			else if (this.getChildrenNum(parsed_object[key]) == 0){
				length = this.getLength(parsed_object);
				if (count == length) {
					document.write(key + "<br>");
				}
				else {
					document.write(key + ",<br>");
					this.inputSpaces(spaces);
				}
			}
		}	
		
	}
	else {
		var att = "";
		for (var key in parsed_object) {
			att = key;
		}
		return att;
	}
	
}


function gatherSchemas(cyclusPath){
  // Default arguments
  cyclusPath = typeof cyclusPath !== 'undefined' ? cyclusPath : ''
  if (cyclusPath == ''){
    console.error("cyclusPath is empty")
  }

  // local vars
  var i = 0
  var parser = null
  var schemas = {}
  var rngRelPath = ""
  var rngFullPath = ""
  var reFac = /eFacility/

  // get the list of all rng files and parse them
  var xhttp = cycicXMLHttpRequest();
  xhttp.open("GET", "rngdump.json", false)
  xhttp.send()
  var rngPaths = JSON.parse(xhttp.responseText)
  document.write(typeof(rngPaths) + "<br/>")
  document.write(Object.prototype.toString.call(rngPaths) + "<br/>")
  for (i in rngPaths) {
    rngRelPath = rngPaths[i]
    rngFullPath = cyclusPath + '/' + rngRelPath
    //parser = new Parser(rngFullPath)
    //schemas[rngRelPath] = parser.parse_obj()
    if (rngRelPath.match(reFac) !== null) {
      schemas[rngRelPath] = new Parser(rngFullPath)
    }
  }

  for (s in schemas) {
    document.write(s + ":  " + "<br/>")
    parsed_obj = schemas[s].parse_obj()
    
    //schemas[s].print_obj()
  }
  return schemas
}

main();
//mainGather();


//MENTAL NOTES TO SELF

//xmlDoc.childNodes[0].nodeName -- 'grammer'
//xmlDoc.childNodes[0].childNodes[1].nodeName -- 'define'
//xmlDoc.childNodes[0].childNodes[1].childNodes[1] --element RecipeReactor 
//xmlDoc.childNodes[0].childNodes[1].childNodes[1].childNodes[1].nodeName --oneOrMore

/**for(i=0; i<elements.length; i++){
	document.write(elements[1].childNodes[i]);
}

//print tag name and value of the name attribute. change 'name' to type to get the data type of the object
document.write(xmlDoc.getElementsByTagName('element')[1].childNodes[1].getAttribute('name'));
document.write(xmlDoc.getElementsByTagName('element')[1].childNodes[1].nodeName);

//prints inrecipt.. why
document.write(xmlDoc.getElementsByTagName('element')[1].childNodes[1].nextSibling.nextSibling.getAttribute('name'));
**/



