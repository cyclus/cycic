main();

function main(){
	var parser = new Parser("storagefacility.xml");
	var parsed_obj = parser.parse();
	parser.print_obj(parsed_obj);
}

function Parser(word){
	var currentIndex = new Array();
	this.file = word;
	
	this.speak = function() {
		document.write(this.word);
	}
	
	//Take XML and convert to JS object to be printed out.
	this.parse = function(){
		if (window.XMLHttpRequest)
		{
			xhttp=new XMLHttpRequest();
		}
		else // IE 5/6
		{
			xhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhttp.open("GET",this.file,false);
		xhttp.send();
		xmlDoc=xhttp.responseXML; 
		
		return xmlDoc;
	}

	this.print_obj = function(parsed_obj){
		var x = parsed_obj.childNodes[0].childNodes;
		//var currentIndex = new Array();
		
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
							//isRef(elements[j].nodeName);
							document.write('\",&nbsp');
							//Under each element name, print out all of its possible children
							printChildren(elements[j].childNodes, 2);
							
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
	function printChildren(a, spaceNumber, indexList){
		var spaces = new Array();
		for (i=0; i<a.length; i++){
			//Extract only the element, not the text objects
			if(a[i].nodeName != '#text'){
				var currentElement = a[i];
				//Input appropriate amount of spaces to format output
				//inputSpaces(spaceNumber);
				
				
				//If the child is a tag, print out just its children since it has no 'name' or 'type' attribute
				if(currentElement.getAttribute('name') === null){
					if(currentElement.getAttribute('type') === null){
						document.write('<br>');
						inputSpaces(spaceNumber + 1);						
						document.write('[\"' + currentElement.nodeName + '\",<br>');
						//If it is a reference, mark it
						//isRef(currentElement.nodeName);
						//document.write(': {<br>');
						inputSpaces(spaceNumber + 2);
						//document.write('[');
						//spaceNumber++;
					
						//Print all possible children of the tag
						printChildren(currentElement.childNodes, spaceNumber + 2);
						
						
						document.write(']<br>');
						
						inputSpaces(spaceNumber+1);
						document.write(']<br>');
						inputSpaces(spaceNumber);
						continue;
						//If everything is done, exit the for loop
						if (i = a.length){
							//document.write(']<br>');
							break;
						}	
						document.write(']fghv<br>');
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
					document.write('\"'+currentElement.getAttribute('type') + '\"');
				}
				//isRef(currentElement.nodeName);
				//document.write('<br>');
				if(currentElement.nodeName === 'ref'){
					if(spaceNumber >3){
						if(i + 3 > a.length){
							document.write(',<br>');
							inputSpaces(spaceNumber - 1);
						}
						else{
						document.write(',<br>');
						inputSpaces(spaceNumber);
						//document.write('[');
						}
					}
				}
				
				//Check for its children's children. If it exists, recursively call printChildren
				if(hasChildren(currentElement)){
					//Push index into an array to keep track of
					
					if(currentElement.childNodes.length > 3){
						document.write(',<br>');
						inputSpaces(spaceNumber + 1);
						
						document.write('[<br>');
						inputSpaces(spaceNumber + 2);
						//document.write('[');
						currentIndex.push(i);
						printChildren(currentElement.childNodes, spaceNumber + 2);
						
						if(currentIndex[currentIndex.length -1] + 3 > a.length){
							document.write(']<br>');
							
						}
						else{
							document.write('],<br>');

						}
						
						inputSpaces(spaceNumber );
						//document.write(']<br>');
						//inputSpaces(spaceNumber - 1 );
					}
					else if(currentElement.childNodes.length === 3){
						document.write(',');
						currentIndex.push(i);
						printChildren(currentElement.childNodes, spaceNumber + 1);
						
						if(currentIndex[currentIndex.length -1] + 3 > a.length){
							document.write(']<br>');
							inputSpaces(spaceNumber - 1);
						}
						else{
							document.write('],<br>');
							inputSpaces(spaceNumber);
							
						}
					}
					
					
					//Print out the children's children
				
					//printChildren(currentElement.childNodes, spaceNumber + 1);
					
					//document.write(',');
					//Pop out the most recent index
					i = currentIndex.pop(i);
					
				}
			
								
			}
		}
	}

	//Helper function used to determine if a node has children or not
	function hasChildren(node){
		if(node.childNodes.length >= 1){
			return true;
		}
		else{
			return false;
		}
	}

	//Helper function used to print out 'num' amount of tabs for formatting purposes
	function inputSpaces(num){
		for(k=0; k < num; k ++){
			document.write('&nbsp&nbsp&nbsp&nbsp');
		}
	}

	//Helper function to determine if the element is a reference or not
	function isRef(nodeName){
		if (nodeName === 'ref'){
			document.write('**');
		}
	}

}
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