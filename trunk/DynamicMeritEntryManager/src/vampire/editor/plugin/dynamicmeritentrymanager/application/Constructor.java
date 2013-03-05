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
		public void selectedDocumentChanged(DocumentEventAPI e) {
		}

		@Override
		public void documentRemoved(DocumentEventAPI e) {
		}

		@Override
		public void setManager(ManagerAPI manager) {
			manager.addDocumentListener(this);
		}


}
