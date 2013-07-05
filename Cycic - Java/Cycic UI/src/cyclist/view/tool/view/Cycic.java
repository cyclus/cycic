	package cyclist.view.tool.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import cyclist.model.vo.DnD;
import cyclist.view.component.AccordionPane;
import cyclist.view.component.View;

public class Cycic extends View{
	public Cycic(){
		super();
		setMinHeight(600);
		setMinWidth(700);
		init();
	}
	
	static Pane pane = new Pane();
	static facilityCircle workingNode = null;
	static marketCircle workingMarket = null;
	
	private void init(){
		practiceRegions.init();
		realFacs.init();
		practiceInstitute.init();
		
		VBox accordionBox = new VBox();
		HBox cycicBox = new HBox();
		Label text1 = new Label("USA!");
		Label text2 = new Label("UT:Austin!");
		dataArrays.Regions.add(text1);
		dataArrays.Regions.add(text2);
		Cycic.pane.setPrefSize(600, 550);
		Cycic.pane.setId("cycicPane");
		
		//Temporary simulation data
		dataArrays.simInfor.add("12");
		dataArrays.simInfor.add("1");
		dataArrays.simInfor.add("2000");
		dataArrays.simInfor.add("0");
		dataArrays.simInfor.add("2");
		
		AccordionPane regions = new AccordionPane();
		regions.builder("Regions", dataArrays.Regions);
;
		AccordionPane institutions = new AccordionPane();
		institutions.builder("Institutes", dataArrays.Institutions);
		
		AccordionPane markets = new AccordionPane();
		markets.builder("Markets", dataArrays.Markets);
		
		AccordionPane commodities = new AccordionPane();
		commodities.builder("Commodities", dataArrays.CommoditiesList);
		
		AccordionPane recipes = new AccordionPane();
		recipes.builder("Recipes", dataArrays.RecipesList);
		
		AccordionPane facilityTypes = new AccordionPane();
		commodities.builder("Facility Types", dataArrays.FacilityTypes);
		
		// Temp Toolbar //
		final GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #d6d6d6;");
		final Button newNode = new Button();
		newNode.setText("Control Box");

		newNode.setOnDragDetected(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				Dragboard db = newNode.startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.put( DnD.TOOL_FORMAT, "testForm");
				db.setContent(content);
				event.consume();
			}
		});
		grid.add(newNode, 0, 0);

		pane.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event){
				for(int i = 0; i < pane.getChildren().size(); i ++){
					if(event.getButton().equals(MouseButton.PRIMARY)){
						if(pane.getChildren().get(i).getStyleClass().toString() == "menu-bar"){
							pane.getChildren().get(i).setVisible(false);
						}
					}
				}				
			}
		});
		setContent(grid);
		accordionBox.getChildren().add(regions);
		cycicBox.getChildren().addAll(accordionBox, pane);
		setContent(cycicBox);

	}
}
