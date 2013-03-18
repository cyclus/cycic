package cyclist.view.tool.view;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

import cyclist.view.component.View;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Robert
 */
public class formBuilder extends View {
	public formBuilder(){
		super();
	}
	
	private GridPane grid;
	private Scene scene2;

	public void facilityReader(ArrayList<Object> facArray) {
		for (int i = 0; i < facArray.size(); i++){
			if(facArray.get(i) instanceof ArrayList){
				facilityReader((ArrayList<Object>) facArray.get(i));
			}
			else {
				
			}
		}
	}

	public ArrayList<Object> createForm( ArrayList<Object> Source, int col, int row, final int typeSelected, final int saveNumber, int colOneOrMore, int rowOneOrMore){
		Button add= new Button("Add");            
		int oneOrMorePlacement=0;            
		int colOneOrMore1 = 0;
		final ArrayList<Object> oneOrMore=new ArrayList<Object>();  
		final ArrayList<Object> newString=new ArrayList<>();
		for(int t=0;t<Source.size(); t++){
			newString.add(null);
		}

		for( int x=0; x<Source.size(); x++){ 

			final ArrayList<Object> Cat=(ArrayList<Object>)Source.get(x);
			int number=0;
			int col1=col; 
			colOneOrMore1=colOneOrMore;
			Integer userLevel2= Integer.parseInt((String)Cat.get(6));  
			for(int y=0; y<Cat.size(); y++){

				final int w=x;
				if(y==0 && userLevel2<= userLevelSelected){  // 
					if(saveNumber==0){
						grid2.add(new Label((String)Cat.get(y)), col1, row);
					}else{
						grid3.add(new Label((String)Cat.get(y)), colOneOrMore1, rowOneOrMore);
					}
				}else if(y==1){
				}else if(y==2){
					if(!(Cat.get(2).equals("oneorMore"))  ){// || !(Cat.get(2).equals("zeroorMore"))
						col1++;
						colOneOrMore1++;

						if((String)Cat.get(4)== null && userLevel2<= userLevelSelected ){  // 
							String stringName=((String)Cat.get(y)).toLowerCase();
							switch (stringName) {
							case "string":
							{
								final TextField text = new TextField();

								if(saveNumber==0){
									grid2.add(text, col1 , row);
								}else{
									grid3.add(text, colOneOrMore1 , rowOneOrMore);
								}
								if(!((String)Cat.get(7)== null)){
									final Tooltip tooltip = new Tooltip();
									tooltip.setText((String)Cat.get(7));
									text.setTooltip(tooltip);
								}
								text.promptTextProperty().setValue((String)Cat.get(5));
								text.setText((String)Cat.get(5));
								newString.set(w, text.getText());
								text.textProperty().addListener(new ChangeListener(){         
									@Override
									public void changed(ObservableValue observable,Object oldValue, Object newValue){
										newString.set(w, (String) newValue);
									}
								});
								break;
							}
							case "double":
							{
								TextField text = new TextField();
								if(saveNumber==0){
									grid2.add(text, col1 , row);
								}else{
									grid3.add(text, colOneOrMore1 , rowOneOrMore);
								}
								text.textProperty().addListener(new ChangeListener(){         
									@Override
									public void changed(ObservableValue observable,
											Object oldValue, Object newValue){
										newString.set(w, (String) newValue);
									}
								});
								break;
							}
							case "recipe":
								formDropDown( col1, row, "Add Recipe");
								break;
							case "type":
								formDropDown( col1, row, "NA");
								break;
							}
						}else{
							number=0;
							String Range=(String)Cat.get(4);
							if(Range== null){
							}else if(((String)Cat.get(4))!= null){
								String delims="[.]+";
								String delims1="[,]+";
								String[] continuous= Range.split(delims);
								final String[] discrete=Range.split(delims1);
								if(!(continuous[0].contentEquals(Range))){

									Integer Min = Integer.parseInt(continuous[0]);
									Integer Max =Integer.parseInt(continuous[1]);
									final Slider slider = new Slider();
									slider.setMin(Min);
									slider.setMax(Max);
									if(!((String)Cat.get(5) == null)){
										slider.setValue(Double.parseDouble((String)Cat.get(5)));
										newString.set(w, (String)Cat.get(5));
									}
									slider.setShowTickLabels(true);
									slider.setShowTickMarks(true);
									slider.setMajorTickUnit(Max/5);
									slider.setMinorTickCount((Max/5)/100);
									slider.setBlockIncrement(10);

									final TextField sliderValue = new TextField(Double.toString(slider.getValue()));
									slider.valueProperty().addListener(new ChangeListener<Number>() {
										@Override
										public void changed(ObservableValue<? extends Number> ov,
												Number old_val, Number new_val) {
											slider.setValue(new_val.doubleValue());
											sliderValue.setText(String.format("%.2f", new_val));
										}
									});

									sliderValue.setMaxWidth(70);
									sliderValue.textProperty().addListener(new ChangeListener(){         
										@Override
										public void changed(ObservableValue observable,
												Object oldValue, Object newValue){
											newString.set(w, (String) newValue);
										}
									});
									if(userLevel2 <= userLevelSelected){ //userLevel2 <= userLevelSelected
										if(saveNumber==0){
											grid2.add(sliderValue, col1+1, row);
											grid2.add(slider,col1,row);
										}else{
											grid3.add(sliderValue, colOneOrMore1+1, rowOneOrMore);
											grid3.add(slider,colOneOrMore1,rowOneOrMore);
										}
									}
									number= 0;
									final Tooltip tooltip = new Tooltip();
									tooltip.setText((String)Cat.get(7));

									slider.setTooltip(tooltip);

								}else{
									final ChoiceBox cb = new ChoiceBox();
									cb.setItems(FXCollections.observableArrayList(
											discrete));
									cb.setMaxWidth(150);
									newString.set(w, (String)Cat.get(5));

									cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
										@Override
										public void changed(ObservableValue ov,
												Number value, Number new_value) {
											newString.set(w, discrete[(Integer)new_value]);
										}
									});
									if(userLevel2 <= userLevelSelected){ // 
										if(saveNumber==0){
											grid2.add(cb,col1,row);
										}else{
											grid3.add(cb, colOneOrMore1 , rowOneOrMore);
										}
									}
									number = 1;
									final Tooltip tooltip = new Tooltip();
									tooltip.setText((String)Cat.get(7));
									cb.setTooltip(tooltip);

								}
							}
						}

					}else{
						oneOrMorePlacement=w;
						final int col2=col;
						final int row1=row;
						final int colOneOrMore2=colOneOrMore1;
						final int rowOneOrMore2=rowOneOrMore;
						final ArrayList<Object> newSource=(ArrayList<Object>)Cat.get(1);

						// grid2.add(add,1,row+1);  
						//add.setOnAction(new EventHandler<ActionEvent>() {
							// @Override
						// public void handle(ActionEvent event) {
						//  userLevelSelection.getSelectionModel().selectedIndexProperty().removeListener(userSelection);
						// ArrayList<Object> newSource=(ArrayList<Object>)Cat.get(1);
						for(int p=0; p<2; p++){
							rowOneOrMore+=(newSource.size()+1);
							oneOrMore.add(createForm(newSource,col2,row1,0, saveNumber+1, colOneOrMore, rowOneOrMore));
						}              
						//  }
						// });                     
					}            
				}
				else if (y==3 && userLevel2 <= userLevelSelected ){  //
					if(number==1) {
						col1++;
						colOneOrMore1++;
					}else{
						col1+=2;    
						colOneOrMore1+=2;
					}
					Label label1=new Label((String)Cat.get(y));
					if(saveNumber==0){
						grid2.add(label1,col1,row);
					}else{
						grid3.add(label1, colOneOrMore1 , rowOneOrMore);
					}
					row++;

				}


			}
			row++;
			if(saveNumber!=0){
				rowOneOrMore++;
			}

		}
		if(saveNumber!=0){
			grid3.add(new Separator(),0, rowOneOrMore,3,1);
			rowOneOrMore++;
		}

		Button submit= new Button("Submit");
		grid2.add(submit,1,row+1);
		if(saveNumber==0){
			secondaryStage.show();
		}
		if(oneOrMoreCase){
			grid2.add(grid3, 1, 4);        
		}
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if((saveNumber==0)){
					saveFormData(newString,typeSelected);
				}
				secondaryStage.close();
			}    
		});
		if(!oneOrMore.isEmpty()){
			newString.set(oneOrMorePlacement,oneOrMore);
		} 

		return newString;

	}

	public ArrayList<Object> createForm(final ArrayList<Object> Examine,  ArrayList<Object> Source, int col, int row, final int location, final int typeSelected, final int saveNumber,int colOneOrMore, int rowOneOrMore){

		final Stage secondaryStage = new Stage(StageStyle.DECORATED);
		secondaryStage.setTitle("New Form");
		GridPane grid2=new GridPane();

		grid2.autosize();
		grid2.setAlignment(Pos.BASELINE_CENTER);
		grid2.setVgap(10);
		grid2.setHgap(40);
		grid2.setPadding(new Insets(30, 30, 30, 30));
		grid2.setStyle("-fx-background-color: Orange;");
		grid3.autosize();
		grid3.setAlignment(Pos.BASELINE_CENTER);
		grid3.setVgap(10);
		grid3.setHgap(40);
		grid3.setPadding(new Insets(30, 30, 30, 30));
		grid3.setStyle("-fx-background-color: Blue;");  
		scene2 =new Scene(grid2,800, 800);
		secondaryStage.setScene(scene2);

		// secondaryStage.show();

		final ChangeListener<Number> userSelection=new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue ov,
					Number value, Number new_value) {
				userLevelSelected= (Integer) new_value;
				secondaryStage.close();
				searchSavedForms((String)Examine.get(0));
			}
		};
		userLevelSelection.getSelectionModel().selectedIndexProperty().addListener(userSelection);

		int oneOrMorePlacement=0;            
		int colOneOrMore1 = 0;
		final ArrayList<Object> oneOrMore=new ArrayList<Object>();  

		final ArrayList<Object> newString=new ArrayList<>();
		for(int t=0;t<Source.size(); t++){
			newString.add(null);
		}

		for( int x=0; x<Source.size(); x++){

			ArrayList<Object> Cat=(ArrayList<Object>)Source.get(x);   
			ArrayList<Object> Newest=(ArrayList <Object>)Source.get(x); 
			//Integer userLevel= Integer.parseInt(Source[x][6]);
			final int z=x;
			int number=0;
			int col1=col;
			colOneOrMore1=colOneOrMore;
			for(int y=0; y<Cat.size(); y++){
				final int w=x;
				if(y==0 ){ //&& userLevel<= userLevelSelected                

					if(saveNumber==0){
						grid2.add(new Label((String)Cat.get(y)), col1, row);
					}else{
						grid3.add(new Label((String)Cat.get(y)), colOneOrMore1, rowOneOrMore);
					}
				}else if(y==1){
				}else if(y==2){
					if(!(Cat.get(2).equals("oneorMore"))  ){
						String stringName=((String)Cat.get(y)).toLowerCase();
						col1++;
						colOneOrMore1++;
						if((String)Cat.get(4)== null){ //&& userLevel<= userLevelSelected
							switch (stringName) {
							case "string":
							{
								TextField text = new TextField();
								/*
                            if( userLevel> userLevelSelected){

                                text.setEditable(false);
                            }else{
                                text.setEditable(true);
                            }
								 */
								text.setText((String)Examine.get(w));


								if(saveNumber==0){
									grid2.add(text, col1 , row);
								}else{
									grid3.add(text, colOneOrMore1 , rowOneOrMore);
								}

								if(!(((String)Newest.get(7)== null))){
									final Tooltip tooltip = new Tooltip();
									tooltip.setText((String)Source.get(7));
									text.setTooltip(tooltip);
								}
								text.promptTextProperty().setValue((String)Newest.get(5));
								newString.set(w, text.getText());
								text.textProperty().addListener(new ChangeListener(){         
									@Override
									public void changed(ObservableValue observable,
											Object oldValue, Object newValue){
										newString.set(w, (String) newValue);
									}
								});
								break;
							}
							case "double":
							{
								TextField text = new TextField();
								/*
                        if( userLevel<= userLevelSelected){
                                text.setEditable(false);
                            }else{
                                text.setEditable(true);
                            }
								 */
								text.setText((String)Examine.get(w));
								if(saveNumber==0){
									grid2.add(text, col1 , row);
								}else{
									grid3.add(text, colOneOrMore1 , rowOneOrMore);
								}
								newString.set(w, text.getText());
								text.textProperty().addListener(new ChangeListener(){         
									@Override
									public void changed(ObservableValue observable,
											Object oldValue, Object newValue){
										newString.set(w, (String) newValue);
									}
								});
								break;
							}
							case "recipe":
								formDropDown( col1, row, "Add Recipe");
								break;
							case "type":
								formDropDown( col1, row, "NA");
								break;
							}
						}else{
							// number=rangeSelector(col1, row, Source[x][4],Source[x][7], Source[x][5]);
							number=0;
							String Range=(String)Newest.get(4);
							if(Range == null){
								//whatIsIt(Range,col, row, tooltipText, defaultText);
							}else if(((String)Newest.get(4))!= null ){
								String delims="[.]+";
								String delims1="[,]+";
								String[] continuous= Range.split(delims);
								final String[] discrete=Range.split(delims1);
								if(!(continuous[0].contentEquals(Range))&& !stringName.equals("string")){
									Integer Min = Integer.parseInt(continuous[0]);
									Integer Max =Integer.parseInt(continuous[1]);
									final Slider slider = new Slider();

									slider.setMin(Min);
									slider.setMax(Max);
									if(!(((String)Examine.get(w)) == null)){
										slider.setValue(Double.parseDouble((String)Examine.get(w)));
										newString.set(w, (String)Examine.get(w));
									}
									slider.setShowTickLabels(true);
									slider.setShowTickMarks(true);
									slider.setMajorTickUnit(Max/5);
									slider.setMinorTickCount((Max/5)/100);
									slider.setBlockIncrement(10);

									slider.setValue(Double.parseDouble((String)Examine.get(w)));
									final TextField sliderValue = new TextField(
											Double.toString(slider.getValue()));
									slider.valueProperty().addListener(new ChangeListener<Number>() {
										@Override
										public void changed(ObservableValue<? extends Number> ov,
												Number old_val, Number new_val) {
											slider.setValue(new_val.doubleValue());
											sliderValue.setText(String.format("%.2f", new_val));
										}
									});
									sliderValue.setMaxWidth(70);
									sliderValue.textProperty().addListener(new ChangeListener(){         
										@Override
										public void changed(ObservableValue observable,
												Object oldValue, Object newValue){
											newString.set(w, (String) newValue);
										}
									});
									//  if( userLevel <= userLevelSelected){
										if(saveNumber==0){
											grid2.add(sliderValue, col1+1, row);
											grid2.add(slider,col1,row);
										}else{
											grid3.add(sliderValue, colOneOrMore1+1, rowOneOrMore);
											grid3.add(slider,colOneOrMore1,rowOneOrMore);
										}

										//  }
										number= 0;
										final Tooltip tooltip = new Tooltip();
										tooltip.setText((String)Newest.get(7));

										slider.setTooltip(tooltip);

								}else{
									final ChoiceBox cb = new ChoiceBox();
									for(int t=0; t<discrete.length; t++){
										if(discrete[t].contentEquals((String)Examine.get(w))){
											cb.setValue(FXCollections.observableArrayList(
													discrete).get(t)); 
											newString.set(w, discrete[t]);
										}
									};
									cb.setItems(FXCollections.observableArrayList(
											discrete));

									cb.setMaxWidth(150);        
									cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
										@Override
										public void changed(ObservableValue ov,
												Number value, Number new_value) {
											newString.set(w, discrete[(Integer)new_value]);
										}
									});
									// if( userLevel <= userLevelSelected){

										if(saveNumber==0){
											grid2.add(cb,col1,row);
										}else{
											grid3.add(cb, colOneOrMore1 , rowOneOrMore);
										}
										// }
									number = 1;
									final Tooltip tooltip = new Tooltip();
									tooltip.setText((String)Newest.get(7));
									cb.setTooltip(tooltip);

								}

							}
						}
					}else{
						System.out.println("WHat");
						final ArrayList<Object> newExamine=(ArrayList<Object>)Examine.get(w);
						final ArrayList<Object> newSource=(ArrayList<Object>)Cat.get(1);
						oneOrMoreCase=true;
						final int colOneOrMore2=colOneOrMore1;
						final int rowOneOrMore2=rowOneOrMore;
						System.out.println(newExamine);
						for(int p=0; p<newExamine.size(); p++){
							final ArrayList<Object> newestExamine=(ArrayList<Object>)newExamine.get(p);

							//Button add= new Button("Add");
							//grid2.add(add,1,row+1);  
							//add.setOnAction(new EventHandler<ActionEvent>() {
							//@Override
							//public void handle(ActionEvent event) {
							//userLevelSelection.getSelectionModel().selectedIndexProperty().removeListener(userSelection);
							oneOrMorePlacement=w;            
							rowOneOrMore+=(newSource.size()+1);
							oneOrMore.add(p,createForm( newestExamine,newSource,0,0,0,0,saveNumber+1,colOneOrMore,rowOneOrMore));
							//newString.set(w,Observe);
							//}

							//});  
						}
					}
				}else if (y==3 ){ //&& userLevel <= userLevelSelected
					if(number==1) {
						col1++;
						colOneOrMore1++;
					}else{
						col1+=2;
						colOneOrMore1+=2;
					}
					Label label1=new Label((String)Newest.get(y));
					if(saveNumber==0){
						grid2.add(label1,col1,row);
					}else{
						grid3.add(label1, colOneOrMore1 , rowOneOrMore);
					}
				}
			}

			row++;
			if(saveNumber!=0){
				rowOneOrMore++;
			}

		}
		if(saveNumber!=0){
			grid3.add(new Separator(),0, rowOneOrMore,3,1);
			rowOneOrMore++;
		}

		Button submit= new Button("Submit");
		grid2.add(submit,1,row+2);  
		if(saveNumber==0){
			secondaryStage.show();
		}
		if(oneOrMoreCase){

			grid2.add(grid3, 1, 4);        
		}
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				userLevelSelection.getSelectionModel().selectedIndexProperty().removeListener(userSelection);
				if((saveNumber==0)){
					editFormData(newString, location, typeSelected);
				}
				secondaryStage.close();
			}

		});
		if(!oneOrMore.isEmpty()){
			newString.set(oneOrMorePlacement,oneOrMore);
		}
		return newString;

	}
	public void saveFormData(ArrayList<Object> newString, int NewInteger){
		Forms.add(newString);
		facilitySaved.add(NewInteger);
	}

	public void editFormData(ArrayList<Object> newString, int location, int NewInteger){
		Forms.set(location, newString);
		facilitySaved.add(location,NewInteger);
	}

	public void searchSavedForms(String Name){
		System.out.println(Forms);
		final Stage secondaryStage = new Stage();
		final GridPane grid2=new GridPane();
		Scene scene2 =new Scene(grid2,400, 400);
		secondaryStage.setTitle("Search for Form");
		secondaryStage.setWidth(scene2.getWidth());
		secondaryStage.setHeight(scene2.getHeight());
		secondaryStage.setScene(scene2);
		grid2.setVgap(10);
		grid2.setHgap(10);
		grid2.setPadding(new Insets(30, 30, 30, 30));
		grid2.setStyle("-fx-background-color: Orange;");
		grid2.autosize();

		Label label= new Label("What is the name of the form you are searching for?");
		final TextField text=new TextField();
		Button submitSearch= new Button("Submit");
		grid2.add(label, 0, 1);
		grid2.add(text, 0, 2);
		grid2.add(submitSearch, 0, 3);
		submitSearch.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				secondaryStage.close();
				for(int x=0; x<Forms.size();x++){
					ArrayList<Object> Examine= (ArrayList<Object>) Forms.get(x);
					if(Examine.get(0).equals(text.getText())){
						oneOrMoreCase=false;
						grid3=new GridPane();
						grid3.autosize();
						grid3.setAlignment(Pos.BASELINE_CENTER);
						grid3.setVgap(10);
						grid3.setHgap(10);
						grid3.setPadding(new Insets(30, 30, 30, 30));
						grid3.setStyle("-fx-background-color: Orange;");    
						createForm( Examine,Structures.get(facilitySaved.get(x)),0,0,x,facilitySaved.get(x),0,0,0);    
					}
				}
			}  
		});
		secondaryStage.show();    
	}
	public void formDropDown( int col, int row, String text) {
		ComboBox recipeComboBox = new ComboBox();
		grid.add(recipeComboBox, col + 1, row);
	}


}

