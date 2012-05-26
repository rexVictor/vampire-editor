package vampire.editor.gui.swing.domain;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Line {
	
	private final Image image;
	
	
	
	public Line(String path){
		Image temp = null;
		try {
			temp = ImageIO.read(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		image = temp;
		System.out.println(image.getHeight(null));
	}
	
	public Image getImage(){
		return image;
	}
	
	public Image getImage(int width){
		return image.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
	}
	
	public static Line getDefault(){
		Line line = new Line("lines/default.gif");
		return line;
	}
	
	

}
