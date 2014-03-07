package vampire.editor.plugin.sheetsummary.application;

import vampire.editor.plugin.sheetsummary.view.SubCatSummaryView;

public class SubCategoryPointsController {
	
	private final SubCatSummaryView subCatSummaryView;
	
	private final SubCategoryPointsCounter subCategoryPointsCounter;

	public SubCategoryPointsController(SubCatSummaryView subCatSummaryView,
			SubCategoryPointsCounter subCategoryPointsCounter) {
		super();
		this.subCatSummaryView = subCatSummaryView;
		this.subCategoryPointsCounter = subCategoryPointsCounter;
		this.subCategoryPointsCounter.setListener(this);
	}
	
	public void update(){
		subCatSummaryView.setSum(subCategoryPointsCounter.getSum());
	}
	
	public void sumChanged(int sum){
		subCatSummaryView.setSum(sum);
	}
	
	

}
