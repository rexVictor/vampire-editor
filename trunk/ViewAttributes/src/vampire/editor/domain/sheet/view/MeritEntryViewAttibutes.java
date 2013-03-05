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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MeritEntryViewAttibutes)) {
			return false;
		}
		MeritEntryViewAttibutes other = (MeritEntryViewAttibutes) obj;
		if (font == null) {
			if (other.font != null) {
				return false;
			}
		} else if (!font.equals(other.font)) {
			return false;
		}
		return true;
	}
	
	
	
}
