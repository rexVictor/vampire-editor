/*******************************************************************************
 * An importer for the vampire editor handling vmpcs format.
 * Copyright (C) 2013 Matthias Reimchen
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
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.importer.vmpcs.application;

import vampire.editor.plugin.api.importer.DocumentImportException;

public class VMPCSImportException extends DocumentImportException{

	private static final long serialVersionUID = 2290076954079924384L;

	public VMPCSImportException() {
		super();
	}

	public VMPCSImportException(String message, Throwable cause) {
		super(message, cause);
	}

	public VMPCSImportException(String message) {
		super(message);
	}

	public VMPCSImportException(Throwable cause) {
		super(cause);
	}
	
	

}
