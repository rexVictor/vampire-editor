package vampire.editor.plugin.tooltipadder;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class Constructor implements Activator, DocumentListener, SubCategoryListener {
	
	private TraitTooltipAdder traitTooltipAdder;

	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetControllerAPI sheetController = e.getSource();
		CategoriesControllerAPI catsController = sheetController.getCategoriesController();
		for (CategoryControllerAPI catController : catsController){
			for (SubCategoryControllerAPI subCatController : catController){
				subCatController.addListener(this);
				for (TraitControllerAPI traitController : subCatController){
					traitController.addListener(traitTooltipAdder);
					TraitView traitView = traitController.getView();
					ValueView valueView = traitController.getValueController().getView();
					String name = traitController.getModel().getName();
					traitTooltipAdder.update(traitView, valueView, name);
				}
			}
		}
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {}

	@Override
	public void documentRemoved(DocumentEventAPI e) {}

	@Override
	public void setManager(ManagerAPI manager) {
		DictionaryAPI tooltips = manager.getResourcesHolder().getDictionary("tooltips"); //$NON-NLS-1$
		if (tooltips != null){
			traitTooltipAdder = new TraitTooltipAdder(tooltips);
			manager.addDocumentListener(this);
		}
	}

	@Override
	public void added(SubCategoryEventAPI event) {
		event.getReason().addListener(traitTooltipAdder);
	}

	@Override
	public void removed(SubCategoryEventAPI event) {}

}
