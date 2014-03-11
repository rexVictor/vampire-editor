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

import java.nio.file.Path;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.exporter.SheetExporter;
import vampire.editor.plugin.api.importer.SheetImporter;

public interface ManagerAPI {
	
	public GUIPlugin getGUI();
	
	public ResourcesHolderAPI getResourcesHolder();
	
	public void setGUIPlugin(GUIPlugin gui);
	
	public GeneralControllerAPI getGeneralController();
	
	public void closed(SheetControllerAPI controller);
	
	public void selectedSheetChanged(SheetControllerAPI controller);
	
	public void setDefaultImporter(SheetImporter importer);
	
	public void addDocumentListener(DocumentListener listener);
	
	public void removeDocumentListener(DocumentListener listener);
	
	public void addImporter(SheetImporter importer);
	
	public void open(Path path);
	
	public void addExporter(SheetExporter exporter);
	
	public void documentSaved(Path path, VampireDocumentAPI document);
	

}
