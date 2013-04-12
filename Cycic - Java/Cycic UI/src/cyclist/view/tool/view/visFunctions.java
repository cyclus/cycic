package cyclist.view.tool.view;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;

public class visFunctions {
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static ArrayList<Integer> stringToColor(String string){
		string = string.toLowerCase();
		ArrayList<Integer> rgbArray = new ArrayList<Integer>();
		double hashValue = Math.abs((double)string.hashCode()/2147483647);
		while(hashValue*26 < 1){
			hashValue = hashValue * 255.;
		}
		rgbArray.add((int)(hashValue*letterMultipliers.get(string.substring(0, 1))));
		rgbArray.add((int)(hashValue*letterMultipliers.get(string.substring(1, 2))));
		rgbArray.add((int)(hashValue*letterMultipliers.get(string.substring(2, 3))));
		return rgbArray;
	}
	
	/**
	 * 
	 * @param array
	 * @return
	 */
	public static boolean colorTest(ArrayList<Integer> array){
		int tally = 0;
		for(int i = 0; i < array.size(); i++){
			if(array.get(i) < 80){
				tally +=1;
			}
		}
		if(tally > 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public static double colorMultiplierTest(Integer color){
		if(color < 185){
			return 1.3;
		}else{
			return 0.7;
		}
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	static void linkNodes(String source, String target){
		Integer nodeIndex = 0;
		Integer markIndex = 0;
		nodeLink link = new nodeLink();
		link.source = source;
		link.target = target;
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			if(dataArrays.FacilityNodes.get(i).getId() == source){
				nodeIndex = i;
				link.line.setStartX(dataArrays.FacilityNodes.get(i).getCenterX());
				link.line.setStartY(dataArrays.FacilityNodes.get(i).getCenterY());
			}
			if(dataArrays.FacilityNodes.get(i).getId() == target){
				link.line.setEndX(dataArrays.FacilityNodes.get(i).getCenterX());
				link.line.setEndY(dataArrays.FacilityNodes.get(i).getCenterY());
				markIndex = i;
			}
			for(int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size(); ii++){
				if(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getId() == source){
					nodeIndex = dataArrays.FacilityNodes.get(i).childrenList.get(ii).parentIndex;
					link.line.setStartX(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getCenterX());
					link.line.setStartY(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getCenterY());
				}
			}
		}
		// Adding the Parent to childMarket link //
		if(dataArrays.hiddenLinks.size() == 0){
			addHiddenLink(nodeIndex, markIndex);
		}
		for(int n = 0; n < dataArrays.hiddenLinks.size(); n++){
			if(dataArrays.hiddenLinks.get(n).target != target || dataArrays.hiddenLinks.get(n).source != dataArrays.FacilityNodes.get(nodeIndex).getId()){
				addHiddenLink(nodeIndex, markIndex);
			}
		} 
		dataArrays.Links.add(link);
		if(dataArrays.FacilityNodes.get(nodeIndex).childrenShow == true){
			Cycic.pane.getChildren().addAll(link.line);
			link.line.toBack();
		}
	}
	
	/**
	 * 
	 * @param parentInt
	 * @param marketInt
	 */
	static void addHiddenLink(Integer parentInt, Integer marketInt){
		nodeLink hiddenLink = new nodeLink();
		hiddenLink.source = dataArrays.FacilityNodes.get(parentInt).getId();
		hiddenLink.target = dataArrays.FacilityNodes.get(marketInt).getId();
		hiddenLink.line.setStartX(dataArrays.FacilityNodes.get(parentInt).getCenterX());
		hiddenLink.line.setStartY(dataArrays.FacilityNodes.get(parentInt).getCenterY());
		hiddenLink.line.setEndX(dataArrays.FacilityNodes.get(marketInt).getCenterX());
		hiddenLink.line.setEndY(dataArrays.FacilityNodes.get(marketInt).getCenterY());
		dataArrays.hiddenLinks.add(hiddenLink);
		if(dataArrays.FacilityNodes.get(parentInt).childrenShow == false){
			Cycic.pane.getChildren().addAll(hiddenLink.line);
			hiddenLink.line.toBack();
		}
	}
	
	/**
	 * 
	 */
	static void reloadPane(){
		Cycic.pane.getChildren().remove(0, Cycic.pane.getChildren().size());
		for(int i = 0; i < dataArrays.FacilityNodes.size(); i++){
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i));
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).menu);
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).text);
			Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).image);
			if(dataArrays.FacilityNodes.get(i).childrenShow == false){
				for(int ii = 0; ii < dataArrays.hiddenLinks.size(); ii++){
					if(dataArrays.FacilityNodes.get(i).getId() == dataArrays.hiddenLinks.get(ii).source){
						Cycic.pane.getChildren().add(dataArrays.hiddenLinks.get(ii).line);
						dataArrays.hiddenLinks.get(ii).line.toBack();
					}
				}
			}else{
				for(int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size(); ii++){
					Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).childrenList.get(ii));
					Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).childrenList.get(ii).menu);
					Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).childrenList.get(ii).text);
					for(int n = 0; n < dataArrays.Links.size(); n++){
						if(dataArrays.Links.get(n).source == dataArrays.FacilityNodes.get(i).childrenList.get(ii).getId()){
							Cycic.pane.getChildren().add(dataArrays.Links.get(n).line);
							dataArrays.Links.get(n).line.toBack();
						}
					}
				}
				for(int n = 0; n < dataArrays.FacilityNodes.get(i).childrenLinks.size(); n++){
					Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).childrenLinks.get(n).line);
					dataArrays.FacilityNodes.get(i).childrenLinks.get(n).line.toBack();
				}
			}
		}
	}
	
	/**
	 * 
	 */
	static ColorAdjust colorAdjust = new ColorAdjust(){
		{
			setBrightness(0.2);
			setHue(-0.05);
		}
	};
	
	/**
	 * 
	 */
	static Bloom bloom = new Bloom(){
		{
			setThreshold(1.0);
		}
	};
	
	@SuppressWarnings("serial")
	/**
	 * 
	 */
	public static HashMap<String, Integer> letterMultipliers = new HashMap<String, Integer>()
	{
		{
			put("a", 1);
			put("b", 2);
			put("c", 3);
			put("d", 4);
			put("e", 5);
			put("f", 6);
			put("g", 7);
			put("h", 8);
			put("i", 9);
			put("j", 10);
			put("k", 11);
			put("l", 12);
			put("m", 13);
			put("n", 14);
			put("o", 15);
			put("p", 16);
			put("q", 17);
			put("r", 18);
			put("s", 19);
			put("t", 20);
			put("u", 21);
			put("v", 22);
			put("w", 23);
			put("x", 24);
			put("y", 25);
			put("z", 26);
		}
	};
}
