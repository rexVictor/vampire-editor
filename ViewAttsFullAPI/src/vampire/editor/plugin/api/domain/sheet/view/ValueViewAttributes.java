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

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public interface ValueViewAttributes extends ValueViewAttributesAPI{

	public void setShowSpace(boolean showSpace);

	public void setDynamic(boolean dynamic);

	public void setCircles(int circles);

	public void setTempSquared(boolean tempSquared);

	public void setSize(int size);

	@Override
	public ValueViewAttributes clone();

	/**
	 * Returns if dynamics, showSpaces, circles, sizes and tempSquareds are equal.
	 * @param that
	 * @return if this equals that
	 */
	@Override
	public boolean equals(Object that);

}
