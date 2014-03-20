package vampire.editor.domain;

import java.awt.Image;

import vampire.editor.plugin.api.domain.LineAPI;

public class Line implements LineAPI{
	
	private final Image line;
	
	private final int width;
	
	private final int height;

	public Line(Image line, int width, int height) {
		super();
		this.line = line;
		this.width = width;
		this.height = height;
	}

	public Image getImage() {
		return line;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	

}
