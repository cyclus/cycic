package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.mediator.instituteViewMediator;
import cyclist.view.tool.view.instituteView;

public class instituteViewTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("table");
	}

	@Override
	public String getName() {
		return "Institution Builder";
	}

	@Override
	public View getView() {
		View view = new instituteView();
		view.setParam("Institution Form");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return new instituteViewMediator();
	}

}