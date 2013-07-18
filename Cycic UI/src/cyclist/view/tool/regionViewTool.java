package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.mediator.regionViewMediator;
import cyclist.view.tool.view.regionView;

public class regionViewTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("table");
	}

	@Override
	public String getName() {
		return "Region Builder";
	}

	@Override
	public View getView() {
		View view = new regionView();
		view.setParam("Region Form");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return new regionViewMediator();
	}

}