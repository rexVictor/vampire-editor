package vampire.editor.plugin.sheetsummary.view;

public class ViewFactory {
	
	public SheetSummaryView buildSheetSummaryView(){
		CatSummaryView attributes = buildCatSummaryView();
		CatSummaryView abilities = buildCatSummaryView();
		SubCatSummaryView virtues = buildSubCatSummaryView();
		SubCatSummaryView disciplines = buildSubCatSummaryView();
		SubCatSummaryView backgrounds = buildSubCatSummaryView();
		return new SheetSummaryView(attributes, abilities, virtues, disciplines, backgrounds);
	}
	
	public CatSummaryView buildCatSummaryView(){
		return new CatSummaryView(buildSubCatSummaryView(),
				buildSubCatSummaryView(), buildSubCatSummaryView());
	}
	
	public SubCatSummaryView buildSubCatSummaryView(){
		return new SubCatSummaryView();
	}

}
