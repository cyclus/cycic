package cyclist.view.tool.view;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLtoJava {
	private void init_1() {
		Map<String, ArrayList<Object>> facilities = new HashMap<String, ArrayList<Object>>();
		try{
			File fXmlFile = new File("C:\\RecipeReactor.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("oneOrMore");
			ArrayList<Object> objPass = new ArrayList<Object>();
			/*System.out.println(doc.getFirstChild().getChildNodes().item(1).getChildNodes().item(1));*/
		    treeTraverse(doc.getFirstChild().getChildNodes().item(1), objPass);
			String testing1 = new String();
			for (int temp = 0; temp < nList.getLength(); temp++) {
			   Node nNode = nList.item(temp);
			   NamedNodeMap attributes = nNode.getAttributes();
			   if(nNode.getNodeName() == "define"){
				   testing1 = attributes.item(0).toString();
				   String Cleaner = new String("name=");
				   testing1 = testing1.replaceAll(Cleaner, "");
				   testing1 = testing1.replaceAll("\"", "");
				   ArrayList<Object> testing = new ArrayList<Object>();
				   String testing3 = new String("Awesome");
				   ArrayList<String> testing2 = new ArrayList<String>();
				   testing.add(testing2);
				   testing.add(testing3);
				   
				   facilities.put(testing1, testing);
				   continue;
			   }
			}
			for(Map.Entry<String, ArrayList<Object>> entry : facilities.entrySet()){
				System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			}
			System.out.println(objPass);
			
	    }catch (Exception e){
	    	 e.printStackTrace();
	    }
	}
	private void treeTraverse(Node Mnode, ArrayList<Object> objPass){
		if(Mnode.getChildNodes().getLength() > 4){
			for(int temp = 1; temp < Mnode.getChildNodes().getLength(); temp = temp + 2){
				treeTraverse(Mnode.getChildNodes().item(temp), objPass);
			}
		}else{
			String objString = new String();
			objString = Mnode.getAttributes().item(0).toString();
			String Cleaner = new String("name=");
		    objString = objString.replaceAll(Cleaner, "");
		    objString = objString.replaceAll("\"", "");
		    objPass.add(objString);
		}
	}
}
