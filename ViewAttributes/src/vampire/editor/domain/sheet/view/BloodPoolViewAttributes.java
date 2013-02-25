package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributesAPI;

public class BloodPoolViewAttributes implements BloodPoolViewAttributesAPI, FontSettable {
	
	private Font font;
	
	private int size;
	
	@Override
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
