package vampire.editor.gui.swing.domain;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Images {
	
	private static final Map<String, Map<Dimension, Image>> images = new HashMap<>();
	
	public static final String CIRCLE_EMPTY = "circle_empty";
	
	public static final String CIRCLE_FILLED = "circle_filled";
	
	public static final String CIRCLE_TEMP_GREATER = "circle_temp_greater";
	
	public static final String CIRCLE_TEMP_LOWER = "circle_temp_lower";
	
	public static final String SQUARE_EMPTY = "square_empty";
	
	public static final String SQUARE_SLASHED = "square_slashed";
	
	public static final String SQUARE_CROSSED = "square_crossed";
	
	static{
		System.out.println("Images initialized");
		Map<String, Path> imagePaths = new HashMap<>();
		Path p = Paths.get("..", "SwingGUI", "images");
		imagePaths.put(CIRCLE_EMPTY, p.resolve("empty_circle.png"));
		imagePaths.put(CIRCLE_FILLED, p.resolve("filled_circle.png"));
		imagePaths.put(CIRCLE_TEMP_GREATER, p.resolve( "temp_greater_circle.png"));
		imagePaths.put(CIRCLE_TEMP_LOWER, p.resolve("temp_lower_circle.png"));
		imagePaths.put(SQUARE_EMPTY, p.resolve("empty_square.png"));
		imagePaths.put(SQUARE_CROSSED, p.resolve("crossed_square.png"));
		imagePaths.put(SQUARE_SLASHED, p.resolve("slashed_square.png"));
		for (String s : imagePaths.keySet()){
			images.put(s, new HashMap<Dimension, Image>()); 
			loadImage(s, imagePaths.get(s));
		}
	}
	
	private static void loadImage(String key, Path path){
		try {
			path = path.toRealPath();
			Image image = ImageIO.read(path.toFile());
			images.get(key).put(null, image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Image getImage(String key, int width, int height){
		Map<Dimension, Image> map = images.get(key);
		Dimension d = new Dimension(width, height);
		Image image = map.get(d);
		if (image == null){
			image = images.get(key).get(null);
			image = image.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
			images.get(key).put(d, image);
		}
		return image;
	}
	
}
