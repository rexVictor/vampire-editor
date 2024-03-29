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
package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.view.factories.SheetViewFactory;

public interface GUIPlugin {

	public String openFileView();
	
	public void addItemToMenuBar(Trigger trigger, String... items);
	
	public void setVisible();
	
	public SheetViewFactory getFactory();
	
	public void sheetLoaded(SheetControllerAPI controller);
	
	public void addImportFileExtension(String extension);
	
	public String saveFileView(String format);
	
	public boolean demandUserChoise(String message);
	
	public <V> void addPluginComponent(V component);
	
	public void createErrorMessage(String e);
	
	public void createErrorMessage(Throwable e);
}
