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
		String hashCode;
		int red=0;
		int blue=0;
		int green=0;
		int p=0;
		ArrayList<Integer> rgbArray = new ArrayList<Integer>();
		
		hashCode= Integer.toString(Math.abs(string.hashCode()));
		if(hashCode.length()>(p+3)){
			red=Integer.parseInt(hashCode.substring(p,p+3));
			p+=3;
			if(hashCode.length()>(p+3)){
				green=Integer.parseInt(hashCode.substring(p,p+3));
				p+=3;
				if(hashCode.length()>(p+3)){
					blue=Integer.parseInt(hashCode.substring(p,p+3));
					p+=3;
				}else{
					blue=Integer.parseInt(hashCode.substring(p,hashCode.length()));
				}	
			}else{
				green=Integer.parseInt(hashCode.substring(p,hashCode.length()));
			}
		}else{
			red=Integer.parseInt(hashCode);
		}
		
		while (red>255) {
			red-=256;
		}
		while (green>255) {
			green-=256;
		}
		while (blue>255) {
			blue-=256;
		}
		
		rgbArray.add(red);
		rgbArray.add(green);
		rgbArray.add(blue);
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
			for(int ii = 0; ii < dataArrays.FacilityNodes.get(i).childrenList.size(); ii++){
				if(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getId() == source){
					nodeIndex = dataArrays.FacilityNodes.get(i).childrenList.get(ii).parentIndex;
					link.line.setStartX(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getCenterX());
					link.line.setStartY(dataArrays.FacilityNodes.get(i).childrenList.get(ii).getCenterY());
				}
			}
		}
		for(int i = 0; i < dataArrays.marketNodes.size(); i++){
			if(dataArrays.marketNodes.get(i).getId() == target){
				link.line.setEndX(dataArrays.marketNodes.get(i).getCenterX());
				link.line.setEndY(dataArrays.marketNodes.get(i).getCenterY());
				markIndex = i;
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
	
	static void linkNodesSimple(facilityCircle source, marketCircle target){
		Integer nodeIndex = 0;
		Integer markIndex = 0;
		nodeLink link = new nodeLink();
		link.source = (String) source.name;
		link.target = target.name;
		link.line.setStartX(source.getCenterX());
		link.line.setStartY(source.getCenterY());
		link.line.setEndX(target.getCenterX());
		link.line.setEndY(target.getCenterY());

		for(int i = 0; i < dataArrays.marketNodes.size(); i++){
			if(dataArrays.marketNodes.get(i).getId() == target.name){
				markIndex = i;
			}
		}
		// Adding the Parent to childMarket link //
		if(dataArrays.hiddenLinks.size() == 0){
			addHiddenLink(source.parentIndex, markIndex);
		}
		for(int n = 0; n < dataArrays.hiddenLinks.size(); n++){
			if(dataArrays.hiddenLinks.get(n).target != target.name && dataArrays.hiddenLinks.get(n).source != dataArrays.FacilityNodes.get(source.parentIndex).getId()){
				addHiddenLink(nodeIndex, markIndex);
			}
		} 
		dataArrays.Links.add(link);
		Cycic.pane.getChildren().addAll(link.line);
		link.line.toBack();
	}
	/**
	 * 
	 * @param parentInt
	 * @param marketInt
	 */
	static void addHiddenLink(Integer parentInt, Integer marketInt){
		nodeLink hiddenLink = new nodeLink();
		hiddenLink.source = dataArrays.FacilityNodes.get(parentInt).getId();
		hiddenLink.target = dataArrays.marketNodes.get(marketInt).getId();
		hiddenLink.line.setStartX(dataArrays.FacilityNodes.get(parentInt).getCenterX());
		hiddenLink.line.setStartY(dataArrays.FacilityNodes.get(parentInt).getCenterY());
		hiddenLink.line.setEndX(dataArrays.marketNodes.get(marketInt).getCenterX());
		hiddenLink.line.setEndY(dataArrays.marketNodes.get(marketInt).getCenterY());
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
					Cycic.pane.getChildren().add(dataArrays.FacilityNodes.get(i).childrenList.get(ii).image);
					for(int n = 0; n < dataArrays.Links.size(); n++){
						if(dataArrays.Links.get(n).source == dataArrays.FacilityNodes.get(i).childrenList.get(ii).name){
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
		for(int i = 0; i < dataArrays.marketNodes.size(); i++){
			Cycic.pane.getChildren().add(dataArrays.marketNodes.get(i));
			Cycic.pane.getChildren().add(dataArrays.marketNodes.get(i).menu);
			Cycic.pane.getChildren().add(dataArrays.marketNodes.get(i).text);			
		}
		for (nodeLink link : dataArrays.Links) {
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
	
	public static void hiddenLinkTest(String parentName, String oldMarket){
		int hiddenLinkCount = 0;
		for (int j = 0; j < dataArrays.hiddenLinks.size(); j++){
			if(dataArrays.hiddenLinks.get(j).source == parentName && dataArrays.hiddenLinks.get(j).target == oldMarket){
				hiddenLinkCount += 1;
			}
			if (hiddenLinkCount > 1){
				dataArrays.hiddenLinks.remove(j);
				j = j - 1;
			}
		}
	}
	
	public static void hiddenLinkRemoval(String parentName, String oldMarket, Boolean test){
		int hiddenLinkCount = 0;
		for (int j = 0; j < dataArrays.hiddenLinks.size(); j++){
			if(dataArrays.hiddenLinks.get(j).source == parentName && dataArrays.hiddenLinks.get(j).target == oldMarket){
				hiddenLinkCount += 1;
			}
			if (test == false){
				dataArrays.hiddenLinks.remove(j);
				j = j - 1;
			}
		}
	}
}
