function JSON2XML(object, tab) {
   var toXml = function(v, name, ind) {
      var xml = "";
      if (v instanceof Array) {
     	for (var i=0; i < v.length; i++){
        	xml += ind + toXml(v[i], name, ind+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;") + "</br>";
   		}
      }
      else if (typeof(v) == "object") {
         var hasChild = false;
         xml += ind + "&lt;" + name;
         for (m in v) {
            if (m.charAt(0) == "@")
               xml += " " + m.substr(1) + "=\"" + v[m].toString() + "\"";
            else
               hasChild = true;
         }
         xml += hasChild ? "&gt; </br>" : "/&gt;";
         if (hasChild) {
            for (m in v) {
               if (m == "#text")
                  xml += v[m];
               else if (m == "#cdata")
                  xml += "&lt;![CDATA[" + v[m] + "]]&gt;" + "</br>";
               else if (m.charAt(0) != "@")
                  xml += toXml(v[m], m, ind+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            }
            xml += (xml.charAt(xml.length-1)=="</br>"?ind:"") + "&lt;/" + name + "&gt;" + "</br>";
         }
      }
      else {
         xml += ind + "&lt;" + name + "&gt;" + v.toString() +  "&lt;/" + name + "&gt;" +"</br>";
      }
	  console.log(xml);
      $('#variableWindow').append(xml);
	  return xml;
   }, xml="";
   for (m in object)
      xml += toXml(object[m], m, "");
   return tab ? xml.replace(/\t/g, tab) : xml.replace(/\t|\n/g, "");
}

function jsonReader(fac){
	function readInput(array2){
		var test = true;
		for(var i = 0; i < array2.length; i++){
			if(array2[i] instanceof Array){
				var test = false;
				for(var m = 0; m < i; m++){
					if(typeof array2[m] ==="string"){
						facility[array2[m]] = {};
					}
				}
				readInput(array2[i]);
			}
		}
		if(test == true){
			facility[array2[0]] = array2[1];
		}
	}
	for(i = 0; i < customFac.length; i++){
		if(customFac[i][0] === fac){
			var prototypeName = i;
		}
	}
	for(j = 0; j < customFac[prototypeName].length; j++){
		if(customFac[prototypeName][j] instanceof Array){
			readInput(customFac[prototypeName][j]);
		}
		else{
			$('#sandbox_1 form').append('<b>' + customFac[prototypeName][j]);
		}
	}
}