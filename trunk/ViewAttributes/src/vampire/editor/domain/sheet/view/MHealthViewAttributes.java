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

import vampire.editor.plugin.api.domain.sheet.view.FontSettable;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributes;

/**
 * Provides the attributes for the health view.
 * @author rex_victor
 *
 */
class MHealthViewAttributes implements FontSettable, HealthViewAttributes{
	
	private Font font;
	
	

	public MHealthViewAttributes() {
		super();
	}
	
	

	public MHealthViewAttributes(Font font) {
		super();
		this.font = font;
	}



	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}
	

	/**
	 * Returns if the fonts are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	@Override
	public boolean equals(Object that){
		if (that == null) return false;
		if (that == this) return true;
		if (that instanceof MHealthViewAttributes){
			return this.font.equals(((MHealthViewAttributes) that).font);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return font.hashCode();
	}
	
	@Override
	public HealthViewAttributes clone(){
		return new MHealthViewAttributes(font);
	}
	
	

}
