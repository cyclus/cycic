package cyclist.view.tool.mediator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import cyclist.controller.ApplicationConstants;
import cyclist.view.mediator.CyclistMediator;
import cyclist.view.tool.view.instituteView;

public class instituteViewMediator extends CyclistMediator {

	public static final String NAME = "cycicMediator";
	
	public instituteViewMediator() {
		super(NAME, null);
	}
	

	@Override
	public instituteView getViewComponent() {
		return (instituteView) super.getViewComponent();
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