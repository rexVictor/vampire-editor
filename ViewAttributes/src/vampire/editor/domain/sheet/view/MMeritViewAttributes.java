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
import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributes;

/**
 * Provides the attributes for the merit view.
 * @author rex_victor
 *
 */
class MMeritViewAttributes implements FontSettable, MeritViewAttributes {
	
	private Font font;
	
	

	public MMeritViewAttributes() {
		super();
	}
	
	

	public MMeritViewAttributes(Font font) {
		super();
		this.font = font;
	}



	/**
	 * Returns if fonts and sizes are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MMeritViewAttributes)) {
			return false;
		}
		MMeritViewAttributes other = (MMeritViewAttributes) obj;
		if (font == null) {
			if (other.font != null) {
				return false;
			}
		} else if (!font.equals(other.font)) {
			return false;
		}
		return true;
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
	public void setFont(Font font) {
		this.font = font;
	}
	
	@Override
	public MeritViewAttributes clone(){
		return new MMeritViewAttributes(font);
	}
	

}
