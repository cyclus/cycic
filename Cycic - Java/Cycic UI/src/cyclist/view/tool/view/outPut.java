package cyclist.view.tool.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class outPut {
	
	public static void output(){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder= docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Simulation");
			doc.appendChild(rootElement);
			
			Element control = doc.createElement("control");
			rootElement.appendChild(control);
			
			// General Simulation Information
			controlSetup(doc, control);
			
			// Commodities
			for(Label commod: dataArrays.CommoditiesList){
				commodityBuilder(doc, rootElement, commod);
			}
			
			// Markets
			
			// Facilities
			for(facilityCircle facility : dataArrays.FacilityNodes){
				if (facility.childrenList.size() > 0) {
					for (int i = 0; i < facility.childrenList.size(); i++){
						Element facID = doc.createElement("facility");
						facilityBuilder(doc, facID, realFacs.alfredStructs.get(facility.facTypeIndex), facility.childrenList.get(i).facilityData, realFacs.alfredStructsNames.get(facility.facTypeIndex));
						rootElement.appendChild(facID);
					}
				} else {
					Element facID = doc.createElement("facility");
					facilityBuilder(doc, facID, realFacs.alfredStructs.get(facility.facTypeIndex), facility.facilityData, realFacs.alfredStructsNames.get(facility.facTypeIndex));
					rootElement.appendChild(facID);
				}
				
			}
			
			// Regions
			for(regionNode region : dataArrays.regionNodes) {
				Element regionID = doc.createElement("region");
				rootElement.appendChild(regionID);
				
				Element regionName = doc.createElement(region.name);
				regionID.appendChild(regionName);
				
				for(String facility: region.availFacilities){
					Element allowedFac = doc.createElement("allowedfacility");
					allowedFac.appendChild(doc.createTextNode(facility));
					regionID.appendChild(allowedFac);
				}
				
				regionBuilder(doc, regionID, region.regionStruct, region.regionData, "growthregion");
				
				Element institID = doc.createElement("institution");
				regionID.appendChild(institID);

				for(String facility: region.availFacilities){
					Element allowedFac = doc.createElement("availableprototype");
					allowedFac.appendChild(doc.createTextNode(facility));
					institID.appendChild(allowedFac);
				}

				Element initialFacilities = doc.createElement("initialfacilitylist");
				for(String facility: region.availFacilities){
					Element entry = doc.createElement("entry");

					Element prototype = doc.createElement("prototype");
					entry.appendChild(prototype);

					prototype.appendChild(doc.createTextNode(facility));

					Element number = doc.createElement("number");
					/*if(facility.childrenList.size() >= 2){
						number.appendChild(doc.createTextNode(String.format("%f", facility.childrenList.size())));
					} else {
						number.appendChild(doc.createTextNode("1"));
					}
					entry.appendChild(number);
					initialFacilities.appendChild(entry);*/
				}
				institID.appendChild(initialFacilities);

				for (instituteNode institute: dataArrays.institNodes) {
					regionBuilder(doc, institID, institute.institStruct, institute.institData, institute.type);
				}
			}
			// Institutions
			

			
			
			//Recipes
			for(Nrecipe recipe : dataArrays.Recipes){
				recipeBuilder(doc, rootElement, recipe);
			}
			
			// Writing out the xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\XMLs/file.xml"));
			
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce){
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
	/**
	 * Sets up the control information for the system
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param control 
	 */
	private static void controlSetup(Document doc, Element control){
		
		Element simDuration = doc.createElement("duration");
		simDuration.appendChild(doc.createTextNode(dataArrays.simInfor.get(0)));
		control.appendChild(simDuration);
		
		Element simStartMon = doc.createElement("startmonth");
		simStartMon.appendChild(doc.createTextNode(dataArrays.simInfor.get(1)));
		control.appendChild(simStartMon);
		
		Element simStartYear = doc.createElement("startyear");
		simStartYear.appendChild(doc.createTextNode(dataArrays.simInfor.get(2)));
		control.appendChild(simStartYear);
		
		Element simStart = doc.createElement("simstart");
		simStart.appendChild(doc.createTextNode(dataArrays.simInfor.get(3)));
		control.appendChild(simStart);
		
		Element decay = doc.createElement("decay");
		decay.appendChild(doc.createTextNode(dataArrays.simInfor.get(4)));
		control.appendChild(decay);
	}
	
	/**
	 * 
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param rootElement The element that will serve as the heading for substructures built in this function.
	 * @param commodity Label containing the commodity name.
	 */
	private static void commodityBuilder(Document doc, Element rootElement, Label commodity){
		Element commod = doc.createElement("commodity");
		Element commodName = doc.createElement("name");
		commodName.appendChild(doc.createTextNode(commodity.getText()));
		commod.appendChild(commodName);
		
		rootElement.appendChild(commod);
	}
	
	/**
	 * 
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param rootElement The element that will serve as the heading for substructures built in this function.
	 * @param recipe
	 */
	private static void recipeBuilder(Document doc, Element rootElement, Nrecipe recipe){
		Element recipeEle = doc.createElement("recipe");
		rootElement.appendChild(recipeEle);
		
		Element recipeName = doc.createElement("name");
		recipeName.appendChild(doc.createTextNode(recipe.Name));
		recipeEle.appendChild(recipeName);
		
		Element recipeBasis = doc.createElement("basis");
		recipeBasis.appendChild(doc.createTextNode(recipe.Basis));
		recipeEle.appendChild(recipeBasis);
		
		for(isotopeData iso : recipe.Composition){
			Element isotope = doc.createElement("isotope");
			recipeEle.appendChild(isotope);
			
			Element isoID = doc.createElement("id");
			isoID.appendChild(doc.createTextNode(iso.Name));
			isotope.appendChild(isoID);
			
			Element isoComp = doc.createElement("comp");
			isoComp.appendChild(doc.createTextNode(String.format("%f2", iso.weightFrac)));
			isotope.appendChild(isoComp);
		}
	}
	
	/**
	 * 
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param rootElement The element that will serve as the heading for substructures built in this function.
	 * @param facArray
	 * @param dataArray
	 * @param facType
	 */
	private static void facilityBuilder(Document doc, Element rootElement, ArrayList<Object> facArray, ArrayList<Object> dataArray, String facType){
		rootElement.appendChild(facilityNameElement(doc, (ArrayList<Object>)dataArray.get(0)));
		
		Element model = doc.createElement("model");
		rootElement.appendChild(model);
		
		Element modelType = doc.createElement(facType.replace(" ", "").toString());
		model.appendChild(modelType);
		
		for(int i = 1; i < dataArray.size(); i++){
			if (dataArray.get(i) instanceof ArrayList){
				facilityDataElement(doc, modelType, (ArrayList<Object>) facArray.get(i), (ArrayList<Object>) dataArray.get(i));
			} else {
				// Adding the label
				Element heading = doc.createElement((String) facArray.get(0));
				heading.appendChild(doc.createTextNode((String) dataArray.get(0)));
				modelType.appendChild(heading);
			}
		}
	}
	
	/**
	 * 
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param dataArray
	 * @return
	 */
	private static Element facilityNameElement(Document doc, ArrayList<Object> dataArray){
		Element nameElement = doc.createElement("name");
		nameElement.appendChild(doc.createTextNode((String) dataArray.get(0)));
		return nameElement;
	}
	
	/**
	 * 
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param rootElement The element that will serve as the heading for substructures built in this function.
	 * @param structArray
	 * @param dataArray
	 */
	private static void facilityDataElement(Document doc, Element rootElement, ArrayList<Object> structArray, ArrayList<Object> dataArray){
		for (int i = 0; i < dataArray.size(); i++){
			if (dataArray.get(i) instanceof ArrayList){
				if (structArray.size() > 2 && !(structArray.get(2) instanceof ArrayList)){ 
					if (indentCheck((String) structArray.get(2))){
						Element tempElement = doc.createElement((String) structArray.get(0).toString().replace(" ", ""));
						facilityDataElement(doc, tempElement, (ArrayList<Object>) structArray.get(1), (ArrayList<Object>) dataArray.get(i));
						rootElement.appendChild(tempElement);
					}
				} else {
					facilityDataElement(doc, rootElement, (ArrayList<Object>) structArray.get(i), (ArrayList<Object>) dataArray.get(i));
				}
			} else {
				Element name = doc.createElement((String) structArray.get(0).toString().replace(" ", ""));
				name.appendChild(doc.createTextNode((String)dataArray.get(0)));
				rootElement.appendChild(name);
			}
		}
	}
	
	/**
	 * 
	 * @param doc The xml.parser document that controls the cyclus input document.
	 * @param structArray
	 * @return
	 */
	private static Element facilityElementHeading(Document doc, ArrayList<Object> structArray){
		Element heading = doc.createElement((String)structArray.get(0).toString().replace(" ", ""));
		return heading;
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	private static boolean indentCheck(String string){
		if(string == "oneOrMore" || string == "zeroOrMore" || string == "input" || string == "output"){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param doc
	 * @param rootElement
	 * @param regionArray
	 * @param dataArray
	 * @param facType
	 */
	private static void regionBuilder(Document doc, Element rootElement, ArrayList<Object> regionArray, ArrayList<Object> dataArray, String facType){
		Element model = doc.createElement("model");
		rootElement.appendChild(model);
		
		Element modelType = doc.createElement(facType.replace(" ", "").toString());
		model.appendChild(modelType);
		
		for(int i = 1; i < dataArray.size(); i++){
			if (dataArray.get(i) instanceof ArrayList){
				facilityDataElement(doc, modelType, (ArrayList<Object>) regionArray.get(i), (ArrayList<Object>) dataArray.get(i));
			} else {
				// Adding the label
				Element heading = doc.createElement((String) regionArray.get(0));
				heading.appendChild(doc.createTextNode((String) dataArray.get(0)));
				modelType.appendChild(heading);
			}
		}
	}
}

