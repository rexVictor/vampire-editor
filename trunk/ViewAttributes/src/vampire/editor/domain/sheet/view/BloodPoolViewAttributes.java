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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + size;
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
		if (!(obj instanceof BloodPoolViewAttributes)) {
			return false;
		}
		BloodPoolViewAttributes other = (BloodPoolViewAttributes) obj;
		if (font == null) {
			if (other.font != null) {
				return false;
			}
		} else if (!font.equals(other.font)) {
			return false;
		}
		if (size != other.size) {
			return false;
		}
		return true;
	}
	
}
