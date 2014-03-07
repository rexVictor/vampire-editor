package vampire.editor.plugin.sheetsummary.application;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.sheetsummary.view.CatSummaryView;
import vampire.editor.plugin.sheetsummary.view.SheetSummaryView;
import vampire.editor.plugin.sheetsummary.view.SubCatSummaryView;

public class ControllerFactory {
	
	private final SheetSummaryView sheetSummaryView;

	public ControllerFactory(SheetSummaryView sheetSummaryView) {
		super();
		this.sheetSummaryView = sheetSummaryView;
	}
	
	public SheetPointsController buildSheetPointsController(SheetControllerAPI sheetController){
		CategoryControllerAPI attributesController = sheetController.getCategoriesController().get(0);
		CategoryControllerAPI abilitiesController = sheetController.getCategoriesController().get(1);
		CategoryControllerAPI virtuesCatController = sheetController.getCategoriesController().get(2);
		SubCategoryControllerAPI virtuesController = virtuesCatController.get(2);
		CategoryControllerAPI advantagesController = sheetController.getCategoriesController().get(3);
		SubCategoryControllerAPI disciplinesController = advantagesController.get(0);
		SubCategoryControllerAPI backgroundsController = advantagesController.get(1);
		CategoryPointsController attributes = buildCategoryPointsController(attributesController, sheetSummaryView.getAttributes());
		CategoryPointsController abilities = buildCategoryPointsController(abilitiesController, sheetSummaryView.getAbilities());
		SubCategoryPointsController virtues = buildSubCategoryPointsController(virtuesController, sheetSummaryView.getVirtues());
		SubCategoryPointsController disciplines = buildSubCategoryPointsController(disciplinesController, sheetSummaryView.getDisciplines());
		SubCategoryPointsController backgrounds = buildSubCategoryPointsController(backgroundsController, sheetSummaryView.getBackgrounds());		
		return new SheetPointsController(attributes, abilities, virtues, disciplines, backgrounds);
		
	}
	
	private CategoryPointsController buildCategoryPointsController(CategoryControllerAPI categoryController
										,CatSummaryView view){
		List<SubCatSummaryView> subViews = view.getSubCatSummaryViews();
		List<SubCategoryPointsController> subControllers = new LinkedList<>();
		for (int i = 0; i < categoryController.size(); i++){
			SubCategoryPointsController subController = buildSubCategoryPointsController(categoryController.get(i), subViews.get(i));
			subControllers.add(subController);
		}
		return new CategoryPointsController(view, subControllers);
	}
	
	private SubCategoryPointsController buildSubCategoryPointsController(SubCategoryControllerAPI subCategoryController,
				SubCatSummaryView view){
		SubCategoryPointsCounter counter = new SubCategoryPointsCounter(subCategoryController);
		return new SubCategoryPointsController(view, counter);
	}
	
	
	

}
