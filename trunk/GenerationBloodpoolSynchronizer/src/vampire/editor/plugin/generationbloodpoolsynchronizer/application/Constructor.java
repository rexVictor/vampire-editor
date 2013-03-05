package vampire.editor.plugin.generationbloodpoolsynchronizer.application;

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class Constructor implements DocumentListener, Activator{
	

	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetControllerAPI sheetController = e.getSource();
		MetaEntryControllerAPI generation = sheetController.getMetaController().getMetaEntryController("generation");
		BloodPoolControllerAPI bloodPool = sheetController.getMiscController().getBloodPool();
		new Synchronizer(bloodPool, generation);
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {}

	@Override
	public void documentRemoved(DocumentEventAPI e) {}

	@Override
	public void setManager(ManagerAPI manager) {
		manager.addDocumentListener(this);
	}

}
