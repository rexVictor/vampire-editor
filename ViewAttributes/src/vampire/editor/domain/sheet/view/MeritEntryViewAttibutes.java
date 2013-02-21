package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;

public class MeritEntryViewAttibutes implements FontSettable, MeritEntryViewAttibutesAPI{
	
	private Font font;

	@Override
	public void setFont(Font font) {
		this.font = font;
		
	}

	@Override
	public Font getFont() {
		return font;
	}
	
}
