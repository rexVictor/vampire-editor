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

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;

/**
 * Provides the attributes for a trait view.
 * @author rex_victor
 *
 */
public class TraitViewAttributes implements TraitViewAttributesAPI, FontSettable{
	
	private boolean editable;
	
	private Orientation orientation = Orientation.HORIZONTAL;
	
	private boolean squares = false;
	
	private Font font;

	public TraitViewAttributes() {
		super();
	}
	
	public TraitViewAttributes(boolean editable, Orientation orientation,
			boolean squares, Font font) {
		super();
		this.editable = editable;
		this.orientation = orientation;
		this.squares = squares;
		this.font = font;
	}



	@Override
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setSquares(boolean squares) {
		this.squares = squares;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("editable: ");
		sb.append(editable);
		sb.append(", squares: ");
		sb.append(squares);
		sb.append(", orientation: ");
		sb.append(orientation);
		sb.append(", font: ");
		sb.append(font);
		sb.append("\n");
		return sb.toString();
	}
	
	@Override
	public TraitViewAttributes clone(){
		TraitViewAttributes clone = new TraitViewAttributes();
		clone.editable = editable;
		clone.orientation = orientation;
		clone.squares = squares;
		clone.font	=	font;
		return clone;
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
	 * Returns if editables, squares, orientations and fonts are equal
	 * @param that
	 * @return if this equals that
	 */
	@Override
	public boolean equals(Object that){
		if (that == null) return false;
		if (that == this) return true;
		if (that instanceof TraitViewAttributes){
			TraitViewAttributes other = (TraitViewAttributes) that;
			return editable == other.editable && squares == other.squares && orientation.equals(other.orientation)
					&& font.equals(other.font);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 11 * System.identityHashCode(editable) + 13 * System.identityHashCode(squares)
				+ 17 * orientation.hashCode() + 19 * font.hashCode();
	}
	
	
	

}
