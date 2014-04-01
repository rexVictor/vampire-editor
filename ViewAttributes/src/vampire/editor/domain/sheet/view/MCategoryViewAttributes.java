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

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.FontSettable;
import vampire.editor.plugin.api.domain.sheet.view.LineAttributes;


/**
 * Provides the attributes of the category view.
 * @author rex_victor
 *
 */
class MCategoryViewAttributes implements FontSettable, CategoryViewAttributes{
	
	private boolean showLine;
	
	private LineAttributes image;
	
	private String title = ""; //$NON-NLS-1$
	
	private Font font;
	
	

	public MCategoryViewAttributes() {
		super();
	}
	
	

	public MCategoryViewAttributes(boolean showLine, LineAttributes image, String title,
			Font font) {
		super();
		this.showLine = showLine;
		this.image = image;
		this.title = title;
		this.font = font;
	}



	@Override
	public boolean isShowLine() {
		return showLine;
	}

	@Override
	public void setShowLine(boolean showLine) {
		this.showLine = showLine;
	}

	@Override
	public LineAttributes getLine() {
		return image;
	}

	@Override
	public void setLine(LineAttributes image) {
		this.image = image;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}
	
	@Override
	public MCategoryViewAttributes clone(){
		MCategoryViewAttributes clone = new MCategoryViewAttributes();
		clone.font		=	font;
		clone.image		=	image;
		clone.showLine	=	showLine;
		clone.title		=	title;
		return clone;
	}
	
	/**
	 * Returns if the fonts, images, the showLines and the titles are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	@Override
	public boolean equals(Object that){
		if (that == null) return false;
		if (that == this) return true;
		if (that instanceof MCategoryViewAttributes){
			MCategoryViewAttributes other = (MCategoryViewAttributes) that;
			return other.showLine == showLine && image.equals(other.image) && title.equals(other.title)
					&& font.equals(other.font); 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 11 * System.identityHashCode(showLine) + 13 * image.hashCode() + 17 * System.identityHashCode(title)
				+19 * font.hashCode();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("showLine: ").append(showLine); //$NON-NLS-1$
		sb.append(" image: ").append(image); //$NON-NLS-1$
		sb.append(" title: ").append(title); //$NON-NLS-1$
		sb.append(" font: ").append(font); //$NON-NLS-1$
		return sb.toString();
	}

}
