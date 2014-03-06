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
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

/**
 * Provides the attributes for a subcategory view.
 * @author rex_victor
 *
 */
class MSubCategoryViewAttributes implements FontSettable, SubCategoryViewAttributes{
	
	private boolean expandable;
	
	private boolean showTitle;
	
	private boolean shallSort = false;
	
	public boolean isShallSort() {
		return shallSort;
	}

	public void setShallSort(boolean shallSort) {
		this.shallSort = shallSort;
	}

	private Font font;
	
	public MSubCategoryViewAttributes(){
		
	}
	
	public MSubCategoryViewAttributes(boolean expandable, boolean showTitle) {
		super();
		this.expandable = expandable;
		this.showTitle = showTitle;
	}
	
	public MSubCategoryViewAttributes(boolean expandable, boolean showTitle, Font font) {
		super();
		this.expandable = expandable;
		this.showTitle = showTitle;
		this.font = font;
	}



	@Override
	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	@Override
	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
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
	public String toString(){
		return "expandable: "+expandable+", shallSort: "+shallSort+", showTitle: "+showTitle+", font: "+font+"\n"; 
	}
	
	@Override 
	public SubCategoryViewAttributes clone(){
		MSubCategoryViewAttributes clone = new MSubCategoryViewAttributes();
		clone.expandable = expandable;
		clone.font	=	font;
		clone.showTitle	=	showTitle;
		clone.shallSort = shallSort;
		return clone;
	}
	
	/**
	 * Returns true if and only if both are expandable and both show the Title and both have the same font.
	 */
	@Override
	public boolean equals(Object object){
		if (object == null) return false;
		if (object == this) return true;
		if (object instanceof MSubCategoryViewAttributes){
			MSubCategoryViewAttributes other = (MSubCategoryViewAttributes) object;
			return other.expandable == expandable && other.showTitle == showTitle
					&& font.equals(other.font) && other.shallSort == shallSort; 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 11 * System.identityHashCode(expandable) + 13 * System.identityHashCode(showTitle)
				+ 17 * System.identityHashCode(font) + 19 * System.identityHashCode(shallSort);
	}
	
	
	
	

}
