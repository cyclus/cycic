JavaScript Parser
=================

The Relaxed NG schema (RNG), found within many of Cyclus' input files, must be read
into a GUI which has its own input format.  With the differences in format, 
the JavaScript parser was created as an intermediate step to convert the RNG
files into organized, readable JavaScript Object Notation (JSON).  The parser
recursively parses through the RNG object, converting the components and elments
into JSON, thereby allowing communication between the two modules. 