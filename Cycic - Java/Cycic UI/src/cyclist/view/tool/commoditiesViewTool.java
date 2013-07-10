package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.mediator.commoditiesViewMediator;
import cyclist.view.tool.view.commoditiesView;

public class commoditiesViewTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("table");
	}

	@Override
	public String getName() {
		return "Commodity Builder";
	}

	@Override
	public View getView() {
		View view = new commoditiesView();
		view.setParam("Commodity Builder");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return new commoditiesViewMediator();
	}

}