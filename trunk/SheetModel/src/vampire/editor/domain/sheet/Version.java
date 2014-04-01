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

import vampire.editor.plugin.api.domain.sheet.VersionAPI;

public class Version implements VersionAPI{
	
	private final byte[] version = new byte[]{1,0,0,0};
	
	private final String description = "Default model"; //$NON-NLS-1$

	@Override
	public byte[] getVersion() {
		return version;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(description + " - "); //$NON-NLS-1$
		for (byte b : version){
			sb.append(b+"."); //$NON-NLS-1$
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();		
	}

}
