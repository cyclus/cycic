package cyclist.view.tool;

import javafx.scene.image.Image;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import cyclist.view.component.View;
import cyclist.view.tool.mediator.cycicMediator;
import cyclist.view.tool.view.Cycic;

public class cycicTool implements Tool {

	@Override
	public Image getIcon() {
		return Resources.getIcon("unknown");
	}

	@Override
	public String getName() {
		return "Cycic";
	}

	@Override
	public View getView() {
		View view = new Cycic();
		view.setParam("Fuel Cycle Sandbox");
		//view.getStylesheets().add("testing.css");
		view.setStyle("-fx-border-radius: 10;");
		return view;
	}
	
	@Override
	public Mediator getMediator() {
		return new cycicMediator();
	}

}
