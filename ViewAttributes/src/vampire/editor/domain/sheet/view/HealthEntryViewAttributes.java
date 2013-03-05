package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;

public class HealthEntryViewAttributes implements HealthEntryViewAttributesAPI, FontSettable{
	
	private Font font;
	
	private int size;

	@Override
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	@Override
	public boolean equals(Object that){
		if (that == null) return false;
		if (that == this) return true;
		if (that instanceof HealthEntryViewAttributes){
			HealthEntryViewAttributes other = (HealthEntryViewAttributes) that;
			return font.equals(other.font) && size == other.size;
		}
		return true;
	}
	
	@Override
	public int hashCode(){
		return 11 * font.hashCode() + 13 * size;
	}

}
