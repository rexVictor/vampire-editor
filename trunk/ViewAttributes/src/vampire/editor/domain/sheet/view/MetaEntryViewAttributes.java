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

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

/**
 * Provides the attributes for a meta entry view
 * @author rex_victor
 *
 */
public class MetaEntryViewAttributes implements MetaEntryViewAttributesAPI{
	
	private Font contentFont;
	
	private int lineCount;
	
	private Font titleFont;
	
	private boolean translate = true;

	@Override
	public boolean isTranslate() {
		return translate;
	}

	public void setTranslate(boolean translate) {
		this.translate = translate;
	}

	@Override
	public MetaEntryViewAttributes clone(){
		MetaEntryViewAttributes clone = new MetaEntryViewAttributes();
		clone.lineCount = lineCount;
		clone.titleFont = titleFont;
		clone.contentFont = contentFont;
		return clone;
	}
	
	/**
	 * Returns if title fonts, content fonts, linecounts and translates are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	@Override
	public boolean equals(Object object){
		if (object == null)
			return false;
		if (object instanceof MetaEntryViewAttributes){
			MetaEntryViewAttributes other = (MetaEntryViewAttributes) object;
			return contentFont.equals(other.contentFont) && titleFont.equals(other.contentFont)
					&& lineCount == other.lineCount && translate == other.translate; 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return lineCount*11 + 13 * titleFont.hashCode() + 17 * contentFont.hashCode()
				+ 19 * System.identityHashCode(translate);
	}
	
	@Override
	public Font getContentFont() {
		return contentFont;
	}

	@Override
	public int getLineCount() {
		return lineCount;
	}

	@Override
	public Font getTitleFont() {
		return titleFont;
	}

	public void setContentFont(Font contentFont) {
		this.contentFont = contentFont;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}
	
	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}
	
	

}
