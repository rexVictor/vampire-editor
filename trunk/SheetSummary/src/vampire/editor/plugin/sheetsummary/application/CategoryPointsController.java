package vampire.editor.plugin.sheetsummary.application;

import java.util.List;

import vampire.editor.plugin.sheetsummary.view.CatSummaryView;

public class CategoryPointsController {
	
	private final List<SubCategoryPointsController> subCatControllers;
	
	private final CatSummaryView catSummaryView;

	public CategoryPointsController(CatSummaryView catSummaryView, List<SubCategoryPointsController> subCatControllers) {
		super();
		this.catSummaryView = catSummaryView;
		this.subCatControllers = subCatControllers;
	}
	
	public void update(){
		for (SubCategoryPointsController controller : subCatControllers){
			controller.update();
		}
	}

	public List<SubCategoryPointsController> getSubCatControllers() {
		return subCatControllers;
	}

	public CatSummaryView getCatSummaryView() {
		return catSummaryView;
	}
	
	

}
