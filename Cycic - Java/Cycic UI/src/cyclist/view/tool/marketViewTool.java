package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.view.formBuilder;
import cyclist.view.tool.view.marketView;

public class marketViewTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("table");
	}

	@Override
	public String getName() {
		return "marketView";
	}

	@Override
	public View getView() {
		View view = new marketView();
		view.setParam("Market Form");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return null;
	}

}