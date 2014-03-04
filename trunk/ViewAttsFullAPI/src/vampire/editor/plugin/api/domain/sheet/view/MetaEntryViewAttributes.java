/*******************************************************************************
 * Vampire Editor View Model API with write methods.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public interface MetaEntryViewAttributes extends MetaEntryViewAttributesAPI{

	public boolean isTranslate();

	public void setTranslate(boolean translate);

	public MetaEntryViewAttributes clone();

	/**
	 * Returns if title fonts, content fonts, linecounts and translates are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object object);

	public Font getContentFont();

	public int getLineCount();

	public Font getTitleFont();

	public void setContentFont(Font contentFont);

	public void setLineCount(int lineCount);

	public void setTitleFont(Font titleFont);

}
