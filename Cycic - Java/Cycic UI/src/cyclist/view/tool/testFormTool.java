package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.view.testForm;

public class testFormTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("unknown");
	}

	@Override
	public String getName() {
		return "testForm";
	}

	@Override
	public View getView() {
		View view = new testForm();
		view.setParam("Fuel Cycle Sandbox");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return null;
	}

}
