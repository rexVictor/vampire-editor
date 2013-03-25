/*******************************************************************************
 * Vampire Editor View Attributes Implementation.
 * Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;

/**
 * Provides the attributes of a health entry view
 * @author rex_victor
 *
 */
public class HealthEntryViewAttributes implements HealthEntryViewAttributesAPI, FontSettable{
	
	private Font font;
	
	private int size;
	
	

	public HealthEntryViewAttributes() {
		super();
	}

	public HealthEntryViewAttributes(Font font, int size) {
		super();
		this.font = font;
		this.size = size;
	}

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
	
	/**
	 * Returns if fonts and sizes are equal.
	 * @param that
	 * @return if this and that are equal
	 */
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
	
	@Override
	public HealthEntryViewAttributes clone(){
		return new HealthEntryViewAttributes(font, size);
	}

}
