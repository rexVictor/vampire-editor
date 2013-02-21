package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.HeathEntryViewAttributesAPI;

public class HealthEntryViewAttributes implements HeathEntryViewAttributesAPI, FontSettable{
	
	private Font font;

	@Override
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}