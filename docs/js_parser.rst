JavaScript Parser
=================

The RELAX NG schema (RNG), found within many of Cyclus' input files, must be read
into a GUI which has its own input format.  With the differences in format, 
the JavaScript parser was created as an intermediate step to convert the RNG
files into organized, readable JavaScript Object Notation (JSON).  The parser
recursively parses through the RNG object, converting the components and elments
into JSON, thereby allowing communication between the two modules. 

How It Works
============

The parse first takes in the RNG object, storing it.  When its internal method, \
"parse_obj" is called, it does the following:

1.  Get the primary name of the object (source facility, storage facility, etc.).

2.  Take the first child node of the object.
	
    2a.  Find the name of the current node and append it onto the object's internal JSON object.
	
    2b.  Does the current node have any children?  If so, go call "parse_obj" again and use the current node as the new primary node, and repeat step 2.  If not, move on to the next child node of the current primary node.

As you can see, the parser simply recursively searches through the RNG object
for nodes, adding them to a JSON object that is built into the parser object
upon instantiation.

Example: How To Use It
======================

Parsing
-------

To use the JS Parser, you must first know which RNG document you will be using.
For our example, we will use a source facility RNG object:: 

    sourcefacility.rng

First, we instantiate the parser object (assuming that the file is in the same
directory as the JS Parser code)::

    var parser = new Parser("sourcefacility.rng");
	
This will create the parser in memory.  We now tell the parser to parse the RNG object::

    var parsedObj = parser.parse_obj();
	
NOTICE: Please keep in mind that "parse_obj()" returns an object, which is the JSON object.

From there, the variable "parsedObj" now contains the JSON object, which can thus be
further processed by the GUI.

Printing
--------

If a visual check is needed for confirmation that the parser has indeed done its job,
call the method "printObject."  Going back to our example, to print parsedObj, we do::

    parser.printObject();
	
NOTICE: This will print the entire JSON-mapped object to the screen or console.  Please 
notice that the parser will only print out the JSON version of the RNG object that it was 
assigned to upon instantiation.

Reversing The Process: JSON to RNG
==================================

With the communication between both Cyclus and our GUI, we must also account for 
backwards compatibility.  That is, the ability to take in a JSON file and output 
an RNG / XML file must be considered.  This feature has only been partially implemented 
and thus, will not fully work.  Its usage is very similar to the JavaScript parser.  We 
instantiate it by calling::

    var parser = JsonToXmlParser(RNG_OR_XML_OBJECT);
	
To parse the RNG / XML object, we call "parse_obj," just like the JS parser::

    var parsedJSONObject = parser.parse_obj();
	
The Json to XML / RNG parser also contains a printing method.  Due to its incompleteness, 
however, we cannot truly make use of it.  Nonetheless, to call it, simply do::

    parser.print_obj();

This parser was modeled after the JS parser, so please keep in mind all of the aforementioned 
notices.