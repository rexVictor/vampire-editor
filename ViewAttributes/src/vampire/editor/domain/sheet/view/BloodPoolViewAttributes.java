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

import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributesAPI;

/**
 * The class providing bloodpool view attributes.
 * @author rex_victor
 */
public class BloodPoolViewAttributes implements BloodPoolViewAttributesAPI, FontSettable {

	private Font font;
	
	private int size;
	
	
	
	public BloodPoolViewAttributes() {
		super();
	}
	
	

	public BloodPoolViewAttributes(Font font, int size) {
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

	/**
	 * Returns if this and that have the same size and their fonts are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (!(that instanceof BloodPoolViewAttributes)) {
			return false;
		}
		BloodPoolViewAttributes other = (BloodPoolViewAttributes) that;
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
	
	public BloodPoolViewAttributes clone(){
		return new BloodPoolViewAttributes(font, size);
	}
	
}
