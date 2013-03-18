package cyclist.view.tool;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Resources {

	private static Map<String, Image> _icons = new HashMap<String, Image>();
	
	public static Image getIcon(String name) {
		return getIcon(name, -1, -1);
	}
	
	public static Image getIcon(String name, double width, double height) {
		String fullname =  name.contains(".") ? name : name+".png";
		Image image = _icons.get(fullname);
		if (image == null) {
			InputStream is = Resources.class.getResourceAsStream("icons/"+fullname);
			if (is == null)
				is = Resources.class.getResourceAsStream("icons/unknown.png");
			if (width > 0)
				image = new Image(is, width, height, true, true);
			else
				image = new Image(is);
			_icons.put(fullname, image);
		}
		return image;
	}
	
	public static void clean() {
		_icons = new HashMap<String, Image>();
	}
}
