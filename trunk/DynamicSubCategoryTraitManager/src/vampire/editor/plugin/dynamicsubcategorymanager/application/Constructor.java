package vampire.editor.plugin.dynamicsubcategorymanager.application;

import java.util.Iterator;

import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;
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
		for (Iterator<? extends CategoryControllerAPI> i = sheetController.getCategoryIterator();i.hasNext();){
			CategoryControllerAPI categoryController = i.next();
			for (Iterator<? extends SubCategoryControllerAPI> j = categoryController.getSubCategoryControllerIterator(); j.hasNext();){
				SubCategoryControllerAPI subCategoryController = j.next();
				SubCategoryViewAttributesAPI viewAtts = mapper.getViewAttributes(subCategoryController.getSubCategory());
				if (viewAtts.isExpandable()){
					new TraitAdder(mapper, subCategoryController);
				}
			}
		}
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
