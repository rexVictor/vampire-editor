package vampire.editor.plugin.tooltipadder;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class Constructor implements Activator, DocumentListener{
	
	private Visitor visitor;
	
	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetControllerAPI sheetController = e.getSource();
		CategoriesControllerAPI catsController = sheetController.getCategoriesController();
		catsController.visitChildren(visitor);
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {}

	@Override
	public void documentRemoved(DocumentEventAPI e) {}

	@Override
	public void setManager(ManagerAPI manager) {
		DictionaryAPI tooltips = manager.getResourcesHolder().getDictionary("tooltips"); //$NON-NLS-1$
		if (tooltips != null){
			TraitTooltipAdder traitTooltipAdder = new TraitTooltipAdder(Loader.load());
			visitor = new Visitor(traitTooltipAdder);
			manager.addDocumentListener(this);
		}
	}

}
