package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.mediator.formBuilderMediator;
import cyclist.view.tool.view.formBuilder;

public class formBuilderTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("table");
	}

	@Override
	public String getName() {
		return "formBuilder";
	}

	@Override
	public View getView() {
		View view = new formBuilder();
		view.setParam("Facility Form");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return new formBuilderMediator();
	}

}