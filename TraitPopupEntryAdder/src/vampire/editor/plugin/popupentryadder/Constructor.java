package vampire.editor.plugin.popupentryadder;

import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class Constructor implements Activator, DocumentListener, SubCategoryListener{
	
	private final Map<String, List<String>> map;
	
	

	public Constructor() {
		super();
		Loader loader = new Loader();
		this.map = loader.loadFiles();
	}

	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetControllerAPI sheetController = e.getSource();
		CategoriesControllerAPI catsController = sheetController.getCategoriesController();
		for (CategoryControllerAPI catController : catsController){
			for (SubCategoryControllerAPI subCatController : catController){
				subCatController.addListener(this);
				String subCatName = subCatController.getSubCategory().getName();
				for (TraitControllerAPI traitController : subCatController){
					TraitView traitView = traitController.getTraitView();
					List<String> entries = map.get(subCatName);
					addTraitPopupEntries(entries, traitView);
				}
			}
		}
	}
	
	private void addTraitPopupEntries(List<String> entries, TraitView traitView){
		if (entries != null){
			for (String s: entries){
				traitView.addPopupEntry(s);
			}
		}
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {}

	@Override
	public void documentRemoved(DocumentEventAPI e) {}

	@Override
	public void setManager(ManagerAPI manager) {
		manager.addDocumentListener(this);
	}

	@Override
	public void traitAdded(SubCategoryEventAPI event) {
		String subCatName = event.getSource().getSubCategory().getName();
		TraitView traitView = event.getAdded().getTraitView();
		List<String> entries = map.get(subCatName);
		addTraitPopupEntries(entries, traitView);
	}

	@Override
	public void traitRemoved(SubCategoryEventAPI event) {}

}
