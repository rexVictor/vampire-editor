package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttibutesAPI;

public class HealthViewAttributes implements HealthViewAttibutesAPI, FontSettable{
	
	private Font font;

	@Override
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	@Override 
	public boolean equals(Object that){
		if (that == null) return false;
		if (that == this) return true;
		if (that instanceof HealthViewAttributes){
			return this.font.equals(((HealthViewAttributes) that).font);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return font.hashCode();
	}
	
	

}
