// Facility View Functions //

function displayingFacility(facility){
	// Clearing the View //
	Fnodes_f.splice(0, Fnodes_f.length);
	links_f.splice(0, links_f.length);
	// Removing excess links //
	var link_f = vis2.selectAll("line.link")
		.data(links_f, function(d){return d.source.id + "-" + d.target.id; });
	link_f.exit().remove();
	// Removing the nodes //
	Fnode_f = vis2.selectAll("g.node")
  		.data(Fnodes_f, function(d) {return d.name;});
  	Fnode_f.exit().remove();
  	
	Fnodes_f.push($.extend(true, {}, facilities[facility]['circle']));
	Fnodes_f[0]['x'] = w/2;
	Fnodes_f[0]['y'] = h/2;
	for(i = 0; i < Fnodes_f[0]['children'].length; i++){
		Fnodes_f.push(Fnodes_f[0]['children'][i]);
	}
	for(i = 1; i < Fnodes_f.length; i++){
		links_f.push({source: Fnodes_f[0], target: Fnodes_f[i]})
	}
	Fnode_f = vis2.selectAll("g.node")
  		.data(Fnodes_f, function(d) {return d.name;});
	Fnode_f.enter().append("svg:g")
	     .attr("class", "node")
		 .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; })
		 .call(force.drag)
		 .on("click", function(d){if(d['call'] == "child"){openFacilityForm(d.name);}})
 		 .on("mouseover", function(d){if(d['call'] == "child"){nodeMouseOver;}})
		 .on("mouseout", function(d){if(d['call'] == "child"){nodeMouseOut;}})
	Fnode_f.append("title")
		.text(function(d) {return nameStoreBack[d.name];})
	Fnode_f.append("circle")
		.attr("r", function(d){return d.size})
		.style("fill", function(){return window.TYPE;})
		.style("stroke", "white")
		.style("stroke-width", "1.5px")
		
	Fnode_f.append("text")
		.attr("text-anchor", "middle")
		.attr("dy", "0.2em")
		.attr("font-size", "10")
		.text(function(d) {return nameStoreBack[d.name];})
  	
  	var link_f = vis2.selectAll("line.link")
	      .data(links_f, function(d) { return d.source.id + "-" + d.target.id; });
	
	link_f.enter().insert("svg:line", "g.node")
	      .attr("class", "link")
	      .style("stroke", "gray")
	      .style("stroke-dasharray", "10, 5")
	      
    force2.start();  
}
 	    

