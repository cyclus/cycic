// Nodes Functions //
var Fnodes = [],
    Tnodes= [],
    links = [],
    Tlinks = [];

function addParentCircle(){
	if(window.TYPE ==="green"){	
		Fnodes.push(facilities[window.NAME]['circle']);
		Tnodes.push(facilities[window.NAME]['circle']);
		
		for( k = 0; k < Fnodes.length; k ++){
			if(Fnodes[k].name === window.NAME){
				var facilityNamePass = k;
			}
		}
		marketNamePass = [];
		marketNamePass = marketArrayBuilder(facilities[window.NAME])
		marketNumPass = []
		for(n = 0; n < Fnodes.length; n++){
			for(j = 0; j < marketNamePass.length; j++){
				if(Fnodes[n].name == marketNamePass[j]){
					marketNumPass.push(n);
				}
			}
		}
		console.log(marketNumPass)
		for(k = 0; k < marketNumPass.length; k++){
			links.push({source: Fnodes[facilityNamePass], target: Fnodes[marketNumPass[k]]});
			Tlinks.push({source: Fnodes[facilityNamePass], target: Fnodes[marketNumPass[k]]})
		}		  		
	}
	if(window.TYPE ==="market"){
		Fnodes.push(MARKETS[window.NAME]['circle']);
		Tnodes.push(MARKETS[window.NAME]['circle']);
	}
	
  	var Fnode = vis.selectAll("g.node")
  		.data(Tnodes, function(d) {return d.name;});
  		
  	if(window.TYPE === "green"){
  		Fnode.enter().append("svg:g")
	     .attr("class", "node")
		 .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
		 .call(force.drag)
		 .on("dblclick", function(d){if(facilities[d.name]['state']=="open"){hideChildren(d.name)}else{showChildren(d.name)};})
		 /*.on("mouseover", function(d){showChildren(d.name);})
		 .on("mouseout", function(d){hideChildren(d.name);})*/
		 .on("click", function(d){openFacilityForm(d.name);})
		Fnode.append("circle")
		 .attr("r", function(d) {return d.size;})
		 .style("fill", function() {return window.TYPE;})
		 .style("stroke", "white")
		 .style("stroke-width", "1.5px")
		Fnode.append("text")
		 .attr("text-anchor", "middle")
		 .attr("dy", "0.2em")
		 .attr("font-size", "12")
		 .text(function(d) {return nameStoreBack[d.name];})
  	}
  	if(window.TYPE === "market"){
  		Fnode.enter().append("svg:g")
	     .attr("class", "node")
		 .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
		 .call(force.drag)
		 .on("click", function(d){openMarketForm(d.name);})
	    Fnode.append("ellipse")
		 .attr("rx", function(d) {return d.size;})
		 .attr("ry", function(d) {return d.size * 0.75;})
		 .style("fill", "steelblue")
		 .style("stroke", "black")
		 .style("stroke-width", "1.5px")
		Fnode.append("text")
		 .attr("text-anchor", "middle")
		 .attr("dy", "0.2em")
		 .attr("font-size", "12")
		 .text(function(d) {return nameStoreBack[d.name];})	 	  		
  	}

	Fnode.append("title")
		.text(function(d) {return nameStoreBack[d.name];})

  	var link = vis.selectAll("line.link")
	      .data(Tlinks, function(d) {return d.source.id + "-" + d.target.id; });
	
	link.enter().insert("svg:line", "g.node")
	      .attr("class", "link")
  	      .style("stroke", "black")
  	      .style("stroke-width", "1px")
      
    force.start();
    Tnodes.splice(0, Tnodes.length);
    Tlinks.splice(0, Tlinks.length);
 }
 
function addCloneCircle(){
 	var test = false;
 	for(i = 0; i < Fnodes.length; i++){
		if(Fnodes[i]['name'] == window.NAME){
			test = true;
		}
 	}
 	for(i = 0; i < Fnodes.length; i++){
 		if(Fnodes[i]['name'] == window.PARENT){
 		    if(facilities[Fnodes[i]['name']]['state'] == "closed"){
 				test = true;
 			}
 		}
 	}
	if(test == false){
		for(i = 0; i < Fnodes.length; i++){
			if(Fnodes[i]['name'] == window.PARENT){
				var facNamePass = i;
			}
		}
		Fnodes.push(facilities[window.NAME]['circle']);
		var test1 = false;
		for(i = 0; i < Fnodes[facNamePass]['children'].length; i++){
			if(Fnodes[facNamePass]['children'][i]['name'] === window.NAME){
				test1 = true;
			}
		}
		if(test1 == false){
			Fnodes[facNamePass]['children'].push(facilities[window.NAME]['circle']);
		}
		Tnodes.push(facilities[window.NAME]['circle']);
		
  		links.push({source: Fnodes[facNamePass], target: Fnodes[Fnodes.length-1]});
  		Tlinks.push({source: Fnodes[facNamePass], target: Fnodes[Fnodes.length-1]});	
  				
		Fnode = vis.selectAll("g.node")
	  		.data(Tnodes, function(d) {return d.name;});
  		Fnode.enter().append("svg:g")
  		     .attr("class", "node")
  			 .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
  			 .call(force.drag)
  			 .on("click", function(d){openFacilityForm(d.name);})
	 		 .on("mouseover", nodeMouseOver)
		 	 .on("mouseout", nodeMouseOut)
  		Fnode.append("title")
  			.text(function(d) {return nameStoreBack[d.name];})
		Fnode.append("circle")
			.attr("r", "40")
			.style("fill", function() {return window.TYPE;})
  			.style("stroke", "white")
  			.style("stroke-width", "1.5px")
  			
		Fnode.append("text")
			.attr("text-anchor", "middle")
			.attr("dy", "0.2em")
			.attr("font-size", "10")
			.text(function(d) {return nameStoreBack[d.name];})
	  	
	  	var link = vis.selectAll("line.link")
		      .data(Tlinks, function(d) { return d.source.id + "-" + d.target.id; });
		
		link.enter().insert("svg:line", "g.node")
		      .attr("class", "link")
		      .style("stroke", "gray")
		      .style("stroke-dasharray", "10, 5")
	      
	}
	 	    
	force.start();
    Tnodes.splice(0, Tnodes.length);
    Tlinks.splice(0, Tlinks.length);	
}

function hideChildren(facility){
	/*CLOSE IT */
	//Removing Child links to markets //
	var childNamePass = [];
	var parMarkNamePass = [];
	for(i = 0; i < Fnodes.length; i++){
		if(Fnodes[i]['name'] == [facility]){
			var facNamePass = i;
			for(j = 0; j < Fnodes[i]['children'].length; j++){
				childNamePass.push(Fnodes[i]['children'][j]);
			}
			for(k = 0; k < childNamePass.length; k++){
				for(ii = 0; ii < links.length; ii++){
					if(childNamePass[k]['name'] == links[ii]['source']['name']){
						// Obtaining market links for parent //
						parMarkNamePass.push(links[ii]['target']['name']);
						for(jj = 0; jj < parMarkNamePass.length-1; jj++){
							if(parMarkNamePass[jj] == parMarkNamePass[parMarkNamePass.length-1]){
								parMarkNamePass.pop();
							}
						}
						// Removing child links to markets //
						links.splice(ii, 1);
						ii = ii - 1;
					}
				}
			}
		}
	}		
	/* Finding and removing children nodes */
	for(i = 0; i < Fnodes.length; i++){
		if(Fnodes[i]['name'] == facility){
			for(ii = 0; ii < Fnodes[i]['children'].length; ii++){
				for(j = 0; j < Fnodes.length; j++){
					if(Fnodes[j]['name'] == Fnodes[i]['children'][ii]['name']){
						Fnodes.splice(j, 1);
						j = j-1;
					}
				}
			}
		}
	}
	/* Finding and removing children links*/
	for(i = 0; i < links.length; i++){
		if(links[i]['source']['name'] == facilities[facility]['Name']){
			if(links[i]['target']['call'] == "child"){
				links.splice(i, 1);
				i = i-1;
			}
		}
	}
	// Removing excess links //
	var link = vis.selectAll("line.link")
		.data(links, function(d) { return d.source.id + "-" + d.target.id; });
	link.exit().remove();
	// Removing the nodes //
	Fnode = vis.selectAll("g.node")
  		.data(Fnodes, function(d) {return d.name;});
  	Fnode.exit().remove();
	// Pushing parent to market links //
	for(i = 0; i < parMarkNamePass.length; i++){
		for(j = 0; j < Fnodes.length; j++){
			if(parMarkNamePass[i] == Fnodes[j]['name']){
				links.push({source: Fnodes[facNamePass], target: Fnodes[j]})
			}
		}
	}		
  	// Updating the links //	
	var link = vis.selectAll("line.link")
		.data(links, function(d) { return d.source.id + "-" + d.target.id; });
	link.enter().insert("svg:line", "g.node")
     	.attr("class", "link")
        .style("stroke", "black")
        .style("stroke-width", "1px")
		
	force.start();
	facilities[[facility]]['state'] = "closed";				
}

function showChildren(facility){
	/*OPEN IT */
	// Removing parent node to market links //
	for(i = 0; i < Fnodes.length; i++){
		if(Fnodes[i]['name'] == nameStore[nameStoreBack[facility]]){
			if(Fnodes[i]['children'].length > 0){
				for(j = 0; j < links.length; j++){
					if(links[j].source['name'] == nameStore[nameStoreBack[facility]]){
						if(links[j].target['call'] == "mark"){
							links.splice(j,1);
							j = j-1;
						}
					}
				}				
			}
		}
	}
	var link = vis.selectAll("line.link")
		.data(links, function(d) { return d.source.id + "-" + d.target.id; });
	link.exit().remove();
	// Returning the parent to child links //
	var childNamePass = [];
	Tnodes.splice(0, Tnodes.length);
	for(i = 0; i < Fnodes.length; i++){
		if(Fnodes[i]['name'] == nameStore[nameStoreBack[facility]]){
			for(j = 0; j < Fnodes[i]['children'].length; j++){
				Fnodes.push(Fnodes[i]['children'][j]);
				Tnodes.push(Fnodes[i]['children'][j]);
				links.push({source: Fnodes[i], target: Fnodes[Fnodes.length-1]});
				Tlinks.push({source: Fnodes[i], target: Fnodes[Fnodes.length-1]});
				childNamePass.push(Fnodes[i]['children'][j]['name']);
			}
		}
	}
	var link = vis.selectAll("line.link")
		.data(Tlinks, function(d) { return d.source.id + "-" + d.target.id; });
	link.enter().insert("svg:line", "g.node")
		      .attr("class", "link")
		      .style("stroke", "gray")
		      .style("stroke-dasharray", "10, 5")
		      
	Fnode = vis.selectAll("g.node")
  		.data(Tnodes, function(d) {return d.name;});
	Fnode.enter().append("svg:g")
	     .attr("class", "node")
		 .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
		 .call(force.drag)
		 .on("click", function(d){openFacilityForm(d.name);})
		 .on("mouseover", nodeMouseOver)
		 .on("mouseout", nodeMouseOut)
	Fnode.append("title")
		.text(function(d) {return d.name;})
	Fnode.append("circle")
		.attr("r", function(d){return d.size})
		.style("fill", function() {return window.TYPE;})
		.style("stroke", "white")
		.style("stroke-width", "1.5px")
		
	Fnode.append("text")
		.attr("text-anchor", "middle")
		.attr("dy", "0.2em")
		.attr("font-size", "10")
		.text(function(d) {return nameStoreBack[d.name];})
	force.start();
	
	Tnodes.splice(0, Tnodes.length);
    Tlinks.splice(0, Tlinks.length);
	
	// Returning the markets //
	var marketNamePass = [];
	for(i = 0; i < childNamePass.length; i++){
		marketNamePass = marketArrayBuilder(facilities[childNamePass[i]])
		for(ii = 0; ii < Fnodes.length; ii++){
			if(childNamePass[i] == Fnodes[ii]['name']){
				var facNamePass = ii;
			}
		}
		for(j = 0; j < marketNamePass.length; j++){
			for(k = 0; k < Fnodes.length; k++){
				if(Fnodes[k]['name'] == marketNamePass[j]){
					links.push({source: Fnodes[facNamePass], target: Fnodes[k]});
					Tlinks.push({source: Fnodes[facNamePass], target: Fnodes[k]});
				}				
			}
		}
		marketNamePass.splice(0,marketNamePass.length);	
	}
	var link = vis.selectAll("line.link")
		.data(Tlinks, function(d) { return d.source.id + "-" + d.target.id; });
	link.enter().insert("svg:line", "g.node")
		      .attr("class", "link")
		      .style("stroke", "black")
  	          .style("stroke-width", "1px")
			
	Tnodes.splice(0, Tnodes.length);
    Tlinks.splice(0, Tlinks.length);
    facilities[[facility]]['state'] = "open"
	force.start();
}

function updateLinks(){
	// Removing all of focused node's old links //
	var marketNamePass = []
  	for(i = 0; i < links.length; i ++){
		if(links[i].source.name == window.NAME){
			if(links[i]['target']['call'] == "mark"){
				links.splice(i,1);
				i = i-1;						
			}
		}
	}
	// Removing all of parent node's old links //
	for(i = 0; i < Fnodes.length; i++){
		if(Fnodes[i]['call'] == "fac"){
			if(Fnodes[i]['name'] == window.NAME){
				var parentNamePass = i;
			}
			for(j = 0; j < Fnodes[i]['children'].length; j++){
				if(Fnodes[i]['children'][j]['name'] == window.NAME){
					var parentNamePass = i;
				}
			}
		}
	}
	for(i = 0; i < links.length; i++){
		if(links[i].source['name'] == Fnodes[parentNamePass]['name']){
			if(links[i].target['call'] == "mark"){
				links.splice(i, 1);
				i = i-1;
			}
		}
	}	
	// Pushing new links for focus node //
	marketNamePass = marketArrayBuilder(facilities[window.NAME])
	// Finding Fnode for focus node //
	for( j = 0; j < Fnodes.length; j++){
		if(Fnodes[j].name === window.NAME){
			var facNamePass = j;	
		}
	}
	// Pushing focus node links //
	marketNumPass = [];
	for(n = 0; n < Fnodes.length; n++){
		for(j = 0; j < marketNamePass.length; j++){
			if(Fnodes[n].name === marketNamePass[j]){
				marketNumPass.push(n);
			}
		}
	}
	for(k = 0; k < marketNumPass.length; k++){
		links.push({source: Fnodes[facNamePass], target: Fnodes[marketNumPass[k]]});
	}
	// Updating Force Layout Links //
	var link = vis.selectAll("line.link")
	      .data(links, function(d) { return d.source.id + "-" + d.target.id; });
  	link.exit().remove();
	link.enter().insert("svg:line", "g.node")
	      .attr("class", "link")
	      .style("stroke", "black")
  	      .style("stroke-width", "1px")    
	var link = vis.selectAll("line.link")
	      .data(links, function(d) { return d.source.id + "-" + d.target.id; });
    force.start();
}

function nodeMouseOver() {
	d3.select(this).select("circle").transition()
		.duration(250)
		.attr("r", function(d) {return d.size*1.09} );
}

function nodeMouseOut() {
	d3.select(this).select("circle").transition()
    	.duration(250)
	    .attr("r", function(d) {return d.size} );
}

function hideMarkets(){
	var tempFnodes =[];
	var templinks = [];
	// Collapse the parent nodes //
	for(ij = 0; ij < Fnodes.length; ij++){
		if(Fnodes[ij]['call'] == "fac"){
			hideChildren(Fnodes[ij]['name']);
		}
	}
	// Adding facility to facility nodes //
	for(i = 0; i < Fnodes.length; i++){
		var facPass =[];
		var markPass = [];
		if(Fnodes[i]['call'] == "mark"){
			for(j = 0; j < links.length; j++){
				if(links[j]['target']['name'] == Fnodes[i]['name']){
					facPass.push(links[j]['source']['name']);
				}
			}
			console.log(facPass);
			for(k = 0; k < facPass.length; k++){
				for(ii = 0; ii < Fnodes.length; ii++){
					if(facPass[k] == Fnodes[ii]['name']){
						for(kk = 0; kk < facPass.length; kk++){
							for(jj = 0; jj < Fnodes.length; jj++){
								if(kk != k && ii != jj){
									links.push({source: Fnodes[ii], target: Fnodes[jj]})
								}
							}
						}
					}
				}
			}
		}
		// Getting the market names //
		for(k = 0; k < links.length; k++){
			if(links[k].source['name'] == Fnodes[i]['name']){
				if(links[k].target['call'] == "mark"){
					markPass.push(links[k].target['name']);
					templinks.push(links[k]);
					links.splice(k, 1);
					k = k - 1;
				}
			}
		}
		// splicing the market nodes // 
		for(ii = 0; ii < Fnodes.length; ii++){
			for(j = 0; j < markPass.length; j++){
				if(Fnodes[ii]['name'] == markPass[j]){
					tempFnodes.push(Fnodes[ii])
					Fnodes.splice(ii, 1);
					ii = ii - 1;
				}
			}
		}
	}
	// Updating Force Layout Links //
	var link = vis.selectAll("line.link")
	      .data(links, function(d) { return d.source.id + "-" + d.target.id; });
  	link.exit().remove();
	link.enter().insert("svg:line", "g.node")
	      .attr("class", "link")
	      .style("stroke", "black")
  	      .style("stroke-width", "1px")    
	var link = vis.selectAll("line.link")
	      .data(links, function(d) { return d.source.id + "-" + d.target.id; });
  	Fnode = vis.selectAll("g.node")
  		.data(Fnodes, function(d) {return d.name;});
  	Fnode.exit().remove();
    force.start();
}
