package cyclist.view.tool.mediator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import cyclist.controller.ApplicationConstants;
import cyclist.view.mediator.CyclistMediator;
import cyclist.view.tool.view.simulationInfo;

public class simulationInfoMediator extends CyclistMediator {

	public static final String NAME = "cycicMediator";
	
	public simulationInfoMediator() {
		super(NAME, null);
	}
	

	@Override
	public simulationInfo getViewComponent() {
		return (simulationInfo) super.getViewComponent();
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