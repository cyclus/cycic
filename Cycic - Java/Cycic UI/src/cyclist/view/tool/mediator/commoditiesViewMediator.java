package cyclist.view.tool.mediator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import cyclist.controller.ApplicationConstants;
import cyclist.view.mediator.CyclistMediator;
import cyclist.view.tool.view.commoditiesView;

public class commoditiesViewMediator extends CyclistMediator {

	public static final String NAME = "cycicMediator";
	
	public commoditiesViewMediator() {
		super(NAME, null);
	}
	

	@Override
	public commoditiesView getViewComponent() {
		return (commoditiesView) super.getViewComponent();
	}

	@Override
	public void setViewComponent(Object component) {
		super.setViewComponent(component);
		
		if (component != null) {
			
			getViewComponent().setOnClose(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					sendNotification(ApplicationConstants.REMOVE_VIEW, getMediatorName());
				}
			});
		}
	}
}