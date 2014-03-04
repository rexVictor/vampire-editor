/*******************************************************************************
 * Vampire Editor Plugin API.
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
package vampire.editor.plugin.api.exporter;

public class DocumentExportException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7428062352104251265L;

	public DocumentExportException() {
		super();
	}

	public DocumentExportException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DocumentExportException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentExportException(String message) {
		super(message);
	}

	public DocumentExportException(Throwable cause) {
		super(cause);
	}
	
	

}
