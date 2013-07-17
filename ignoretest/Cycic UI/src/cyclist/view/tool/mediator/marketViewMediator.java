package cyclist.view.tool.mediator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import cyclist.controller.ApplicationConstants;
import cyclist.view.mediator.CyclistMediator;
import cyclist.view.tool.view.marketView;

public class marketViewMediator extends CyclistMediator {

	public static final String NAME = "cycicMediator";
	
	public marketViewMediator() {
		super(NAME, null);
	}
	

	@Override
	public marketView getViewComponent() {
		return (marketView) super.getViewComponent();
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