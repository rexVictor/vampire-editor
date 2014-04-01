/*******************************************************************************
 * Vampire Editor Model Implementation.
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
package vampire.editor.domain.sheet;

import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.domain.sheet.Merits;

/**
 * The Merits is specializing the {@link Data} class. <br> 
 * It provides an {@link Iterator} to get its attached Merits ({@link MMerit}).<br>
 * @author rex_victor
 *
 */
class MMerits extends Data<Merit> implements Merits{
	
	@Override
	public MMerits clone(){
		return (MMerits) super.clone();
	}

}
