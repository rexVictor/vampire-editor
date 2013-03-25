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

import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;

/**
 * Provides the attributes for the merit entry view.
 * @author rex_victor
 *
 */
public class MeritEntryViewAttributes implements FontSettable, MeritEntryViewAttibutesAPI{
	
	private Font font;
	
	public MeritEntryViewAttributes() {
		super();
	}
	
	

	public MeritEntryViewAttributes(Font font) {
		super();
		this.font = font;
	}



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

	/**
	 * Returns if fonts and sizes are equal.
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
		if (!(that instanceof MeritEntryViewAttributes)) {
			return false;
		}
		MeritEntryViewAttributes other = (MeritEntryViewAttributes) that;
		if (font == null) {
			if (other.font != null) {
				return false;
			}
		} else if (!font.equals(other.font)) {
			return false;
		}
		return true;
	}
	
	public MeritEntryViewAttributes clone(){
		return new MeritEntryViewAttributes(font);
	}
	
	
	
}
