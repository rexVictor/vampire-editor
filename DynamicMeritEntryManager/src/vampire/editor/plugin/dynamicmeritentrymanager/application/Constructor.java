/*******************************************************************************
 * Vampire Editor Plugin: Dynamic Merit Entry Manager.
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
package vampire.editor.plugin.dynamicmeritentrymanager.application;

import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class Constructor implements Activator, DocumentListener{
	
		@SuppressWarnings("unused")
		@Override
		public void documentAdded(DocumentEventAPI e) {
			SheetControllerAPI sheetController = e.getSource();
			VampireDocumentAPI document = sheetController.getDocument();
			ModelToViewModelMapperAPI mapper = document.getModelToViewModelMapper();
			MeritsControllerAPI flaws = sheetController.getMiscController().getFlaws();
			MeritsControllerAPI merits = sheetController.getMiscController().getMerits();
			new MeritAdder(merits, mapper);
			new MeritAdder(flaws, mapper);
		}

		@Override
		public void selectedDocumentChanged(DocumentEventAPI e) {/***/}

		@Override
		public void documentRemoved(DocumentEventAPI e) {/***/}

		@Override
		public void setManager(ManagerAPI manager) {
			manager.addDocumentListener(this);
		}


}
