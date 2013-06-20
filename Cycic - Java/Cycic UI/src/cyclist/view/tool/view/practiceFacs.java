package cyclist.view.tool.view;

import java.util.ArrayList;

/**
 *
 * @author Robert
 */
public class practiceFacs{
    public static ArrayList<ArrayList<Object>> Structures=new ArrayList<>();
    public static ArrayList<String> structureNames = new ArrayList<String>();
    private static String[][] Enrichment_Facility_Structure= {
                {"Name", "string", "string", null, null, "Reactor 1", "0", null}, 
                {"incommodity", "commodity", "string", null, null, null, "0", null}, 
                {"outcommodity", "commodity", "string", null, null, null, "0", null},
                {"inventorysize", "double", "double", "kg", "20000...100000", "20000", "1", null}, 
                {"capacity", "double", "double", "SWU", "20000,30000,40000,50000", "30000", "1", "The amount of separation done by an enrichment process"}, 
                {"tailsassay", "commodity", "string", null, null, null, "0", null}
        };
    private static String[][] Source_Facility_Structure = {
                {"Name", "string", "string", null, null, null, "0", null}, 
                {"recipe", "commodity", "string", null, null, null, "0", null}, 
                {"inventorysize", "double", "double", "kg", "472...682",  "500", "0", "Total amount of material in the facility to start"},
                {"capacity", "double", "double", "kg/yr", "20,30,40,50", "20", "0", "Rate of material produced"}
        };
    private static String[][] Reactor_Structure={
                {"Name", "String", "String", null, null, null, "0", null, null},
                {"Recipe", "commodity", "string", null, null, null, "0", null, null},
                {"Power Level", "double", "double", "kWh", "1000...9001", "9001", "0", "Power of the reactor", null}      
        };
    private static String[][] Research_Group_Structure={
        {"Name", "String", "string", null, null, null, "0", null, null},
        {"Knowledge", "commodity", "string", null, null, null, "0", null, null}
     
    };
    private static ArrayList<Object> Enrichment_Facility=new ArrayList<>();
    private static ArrayList<Object> Student=new ArrayList<>();
    private static ArrayList<Object> Source_Facility=new ArrayList<>();
    private static ArrayList<Object> Reactor=new ArrayList<>();
    
    /**
     * This function is a filler function to build the test Facilities for the GUI
     * Until the RNG schema is finalized this will produce the functions.	
     */
    public static void start() {
    	// Clearing out the old data //
    	Enrichment_Facility.clear();
    	Student.clear();
    	Source_Facility.clear();
    	Reactor.clear();
    	
    	// Build the Enrichment Facility
        for(int x=0; x<Enrichment_Facility_Structure.length; x++){  
            ArrayList<Object> Categories=new ArrayList<>();
            for(int y=0; y<Enrichment_Facility_Structure[x].length; y++){
                Categories.add(Enrichment_Facility_Structure[x][y]);
            }
            Enrichment_Facility.add(Categories);
        }
        
        // Building the Source Facility
        for(int x=0; x<Source_Facility_Structure.length; x++){ 
            ArrayList<Object> Categories=new ArrayList<>();
            for(int y=0; y<Source_Facility_Structure[x].length; y++){
                Categories.add(Source_Facility_Structure[x][y]);
            }
            Source_Facility.add(Categories);
        }
        // Building the Reactor Facility
        for(int x=0; x<Reactor_Structure.length; x++){
            ArrayList<Object> Categories=new ArrayList<>();
            for(int y=0; y<Reactor_Structure[x].length; y++){ 
                
                if(x==2 && y ==0) {
                   ArrayList<Object> zeroOrMore = new ArrayList<Object>();
                   zeroOrMore.add("Name");
                   zeroOrMore.add("String");
                   zeroOrMore.add("String");
                   zeroOrMore.add(null);
                   zeroOrMore.add(null);
                   zeroOrMore.add(null);
                   zeroOrMore.add("0");
                   zeroOrMore.add(null);
                   zeroOrMore.add("Operator Name");
                   ArrayList<Object> Operator=new ArrayList<>();
                   Operator.add("Operators");
                   Operator.add(zeroOrMore);
                   Operator.add("zeroOrMore");
                   Operator.add("Students");
                   Operator.add(null);
                   Operator.add(null);
                   Operator.add("0");
                   Operator.add(null);
                   Operator.add(null);
                   Reactor.add(Operator);
                   Categories.add(Reactor_Structure[x][y]);
                }else{
                    Categories.add(Reactor_Structure[x][y]);
                }
            }
         Reactor.add(Categories);
        }
        // Building the research group //
        for(int x=0; x<Research_Group_Structure.length; x++){
            ArrayList<Object> Categories=new ArrayList<>();
            for(int y=0; y<Research_Group_Structure[x].length; y++){ 
                if(x==2 && y==1) {   
                   
                }
                else{
                    Categories.add(Research_Group_Structure[x][y]);
                }
                
            }
           Student.add(Categories);
        }
        ArrayList<Object> Student_Info=new ArrayList<>();
        String[] Student_Inf={ "Name", "string", "string", null, null, null, "0", null, "Name" };
        String[] Research={ "Research Topic", "string", "string", null, null, null, "0", null, null};
        String[] Status={"Status", "string", "string", null, "Robert, Hostile, Hated, Loathed, Disliked", "Hated", "1", "Reputation with adviser", null};
        Student_Info.add("Student");
        ArrayList<Object> Student_Info1=new ArrayList<>();
        ArrayList<Object> Student_Info2=new ArrayList<>();
        ArrayList<Object> Student_Info3=new ArrayList<>();
        ArrayList<Object> Student_Info4=new ArrayList<>();
        for(int z=0; z<Student_Inf.length; z++){ 
        	Student_Info1.add(Student_Inf[z]);
        	Student_Info2.add(Research[z]);
        	Student_Info3.add(Status[z]);
        }
        Student_Info4.add(Student_Info1);
        Student_Info4.add(Student_Info2);
        Student_Info4.add(Student_Info3);
        Student_Info.add(Student_Info4);
        Student_Info.add("oneOrMore");
        Student_Info.add(null);
        Student_Info.add(null);
        Student_Info.add(null);
        Student_Info.add("0");
        Student_Info.add(null);
        Student_Info.add(null);

        Student.add(Student_Info);

        for(int x=0; x<Research_Group_Structure.length; x++){
        	ArrayList<Object> Categories=new ArrayList<>();
        	for(int y=0; y<Research_Group_Structure[x].length; y++){ 
        		if(x==2 && y==1) {   

        		}
        		else{
        			Categories.add(Research_Group_Structure[x][y]);
        		}

        	}
        	Student.add(Categories);
        }
        
        Structures.add(Enrichment_Facility);
        Structures.add(Source_Facility);
        Structures.add(Reactor);
        Structures.add(Student);
        
        structureNames.add("Enrichment Facility");
        structureNames.add("Source Facility");
        structureNames.add("Reactor");
        structureNames.add("Research Group");
        
        
    } 
}
    
