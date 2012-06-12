if (window.XMLHttpRequest)
  {
  xhttp=new XMLHttpRequest();
  }
else // IE 5/6
  {
  xhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xhttp.open("GET","recipereactor.xml",false);
xhttp.send();
xmlDoc=xhttp.responseXML; 
x = xmlDoc.childNodes[0].childNodes;

//Print main parent name
document.write(xmlDoc.getElementsByTagName('element')[0].getAttribute('name') + ': {<br>');

var elements = x[1].childNodes[1].childNodes
var space = '';
var node = new Object();
var list = new Array();

//Print main element names
for(j=0; j<elements.length; j++){
	if(j%2){
		if(elements[j].getAttribute('name') === null){
			document.write('&nbsp&nbsp&nbsp&nbsp'+elements[j].nodeName+': {<br>');
		}
		else{
			document.write('&nbsp&nbsp&nbsp&nbsp'+elements[j].getAttribute('name') + ': {<br>');
		}
		//Under each element name, print out all of its possible children
		printChildren(elements[j].childNodes, 2);
		document.write('&nbsp&nbsp&nbsp&nbsp}<br>');
	}
}

var k=0;
function printChildren(a, spaceNumber){
	for (i=0; i<a.length; i++){
		//Extract only the element, not the text objects
		if(i%2){
			//Input appropriate amount of spaces to format output
			inputSpaces(spaceNumber);
			
			//If the child is a tag, print out just its children since it has no 'name' or 'type' attribute
			if(a[i].getAttribute('name') === null){
				if(a[i].getAttribute('type') === null){
					document.write(a[i].nodeName+': {<br>');
					printChildren(a[i].childNodes, spaceNumber + 1);
				}
			}
			
			//If the object has a 'name' attribute, print it
			if(a[i].getAttribute('name')){
				document.write('name: '+ a[i].getAttribute('name') +'<br>');
			}
			
			//If the object has a 'type' attribute, print it
			if(a[i].getAttribute('type')){
				document.write('type: '+a[i].getAttribute('type') +'<br>');
			}
			
			//Check for its children's children. If it exists, recursively call printChildren
			if(hasChildren(a[i])){
				printChildren(a[i].childNodes, spaceNumber + 1);
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
function  inputSpaces(num){
	for(k=0; k < num; k ++){
		document.write('&nbsp&nbsp&nbsp&nbsp');
	}
}


//																																\\
//																																\\
//MENTAL NOTES TO SELF																											\\
//																																\\
//																																\\

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