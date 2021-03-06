package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.mediator.simulationInfoMediator;
import cyclist.view.tool.view.simulationInfo;

public class simulationInfoTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("table");
	}

	@Override
	public String getName() {
		return "Simulation Info";
	}

	@Override
	public View getView() {
		View view = new simulationInfo();
		view.setParam("Simulation Data");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return new simulationInfoMediator();
	}

}