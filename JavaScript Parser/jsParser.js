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


document.write(xmlDoc.getElementsByTagName('element')[0].getAttribute('name') + ': {<br>');
var elements = xmlDoc.childNodes[0].childNodes[1].childNodes[1].childNodes
for (j=0; j<xmlDoc.childNodes[0].childNodes[1].childNodes[1].childNodes.length; j++){
		if(j%2){
			if(elements[j].getAttribute('name') === null){
				document.write('&nbsp&nbsp&nbsp&nbsp'+elements[j].nodeName+': {<br>');
			}
			else{
				document.write('&nbsp&nbsp&nbsp&nbsp'+elements[j].getAttribute('name'));
				if(elements[j].nodeName == 'ref'){
					document.write('(ref)');
				}
				document.write(': {<br>');
				=
			}
			for(k=0; k<elements[j].childNodes.length; k++){
				if(k%2){
					if(elements[j].childNodes[k].getAttribute('name')){
						document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'\"name\": '+elements[j].childNodes[k].getAttribute('name'));
					}
					else if(elements[j].childNodes[k].getAttribute('type')){
						document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'\"type\": '+elements[j].childNodes[k].getAttribute('type'));
					}
					
					if(elements[j].childNodes[k].getAttribute('name') === null){
						if(elements[j].childNodes[k].nodeName == 'ref'){
							document.write('(ref)');
						}
						else if(elements[j].childNodes[k].nodeName != 'data'){
							document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+elements[j].childNodes[k].nodeName);
						}
					}
					document.write(': {<br>');
				}
				for(i=0; i<elements[j].childNodes[k].childNodes.length;i++){
					if(i%2){
						document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+
							'\"name\": '+elements[j].childNodes[k].childNodes[i].getAttribute('name'));
						if(elements[j].childNodes[k].childNodes[i].nodeName == 'ref'){
							document.write('(ref)');
						}
						document.write('<br>');
					}
					for(a=0; a<elements[j].childNodes[k].childNodes[i].childNodes.length;a++){
						if(a%2){
							if(elements[j].childNodes[k].childNodes[i].childNodes[a].getAttribute('name')){
								document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'\"name\": '+elements[j].childNodes[k].childNodes[i].childNodes[a].getAttribute('name'));
							}
							if(elements[j].childNodes[k].childNodes[i].childNodes[a].getAttribute('type')){
								document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp'+'\"type\": '+elements[j].childNodes[k].childNodes[i].childNodes[a].getAttribute('type'));
							}
							if(elements[j].childNodes[k].childNodes[i].childNodes[a].nodeName == 'ref'){
								document.write('(ref)');
							}
							document.write('<br>');
						}
					}
				}
			}
			document.write('&nbsp&nbsp&nbsp&nbsp'+'&nbsp&nbsp&nbsp&nbsp }<br>'+'&nbsp&nbsp&nbsp&nbsp }<br>');
		}
}
document.write('}<br>');
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