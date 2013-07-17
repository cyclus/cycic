package cyclist.view.tool.view;

import java.util.ArrayList;
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
		
		hashCode = Integer.toString(Math.abs(string.hashCode()));
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
	 * Adjusts the color of a node when highlighted
	 */
	static ColorAdjust colorAdjust = new ColorAdjust(){
		{
			setBrightness(0.2);
			setHue(-0.05);
		}
	};
	
	/**
	 * Depricated
	 */
	static Bloom bloom = new Bloom(){
		{
			setThreshold(1.0);
		}
	};
	
	static void linkNodesSimple(facilityCircle source, marketCircle target){
		marketCircle markIndex = null;
		boolean hiddenLinkCheck = false;
		nodeLink link = new nodeLink();
		link.source = source;
		link.target = target;
		link.line.setStartX(source.getCenterX());
		link.line.setStartY(source.getCenterY());
		link.line.setEndX(target.getCenterX());
		link.line.setEndY(target.getCenterY());

		for(int i = 0; i < dataArrays.marketNodes.size(); i++){
			if(dataArrays.marketNodes.get(i) == target){
				markIndex = dataArrays.marketNodes.get(i);
			}
		}
		// Adding the Parent to childMarket link //
		if(dataArrays.hiddenLinks.size() == 0){
			addHiddenLink(dataArrays.FacilityNodes.get(source.parentIndex), markIndex);
		}
		for(int n = 0; n < dataArrays.hiddenLinks.size(); n++){
			if( (marketCircle) dataArrays.hiddenLinks.get(n).target == target && dataArrays.hiddenLinks.get(n).source == dataArrays.FacilityNodes.get(source.parentIndex)){
				hiddenLinkCheck = true;
			}
		} 
		if ( hiddenLinkCheck == false) {
			addHiddenLink(dataArrays.FacilityNodes.get(source.parentIndex), markIndex);
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
	static void addHiddenLink(facilityCircle parent, marketCircle market){
		nodeLink hiddenLink = new nodeLink();
		hiddenLink.source = parent;
		hiddenLink.target = market;
		hiddenLink.line.setStartX(parent.getCenterX());
		hiddenLink.line.setStartY(parent.getCenterY());
		hiddenLink.line.setEndX(market.getCenterX());
		hiddenLink.line.setEndY(market.getCenterY());
		dataArrays.hiddenLinks.add(hiddenLink);
		if(parent.childrenShow == false){
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
					if(dataArrays.FacilityNodes.get(i) == dataArrays.hiddenLinks.get(ii).source){
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
						if(dataArrays.Links.get(n).source == dataArrays.FacilityNodes.get(i).childrenList.get(ii)){
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
	}
	
	
	public static void hiddenLinkTest(facilityCircle parentName, Object oldMarket){
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
	
	public static void hiddenLinkRemoval(facilityCircle parentName, Object oldMarket, Boolean test){

		for (int j = 0; j < dataArrays.hiddenLinks.size(); j++){
			if(dataArrays.hiddenLinks.get(j).source == parentName && dataArrays.hiddenLinks.get(j).target == (marketCircle) oldMarket){
				if (test == false){
					dataArrays.hiddenLinks.remove(j);
					j = j - 1;
				}
			}
		}
	}
}
