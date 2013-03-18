package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.view.Foo;

public class FooTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("unknown");
	}

	@Override
	public String getName() {
		return "Foo";
	}

	@Override
	public View getView() {
		View view = new Foo();
		view.setParam("Fuel Cycle Sandbox");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return null;
	}

}
