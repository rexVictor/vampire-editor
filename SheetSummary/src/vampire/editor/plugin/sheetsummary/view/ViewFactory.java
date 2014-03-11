package vampire.editor.plugin.sheetsummary.view;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class ViewFactory {
	
	private final DictionaryAPI dictionary;
	
	
	
	public ViewFactory(DictionaryAPI dictionary) {
		super();
		this.dictionary = dictionary;
	}

	public SheetSummaryView buildSheetSummaryView(){
		CatSummaryView attributes = buildCatSummaryView();
		CatSummaryView abilities = buildCatSummaryView();
		SubCatSummaryView virtues = buildSubCatSummaryView();
		SubCatSummaryView disciplines = buildSubCatSummaryView();
		SubCatSummaryView backgrounds = buildSubCatSummaryView();
		return new SheetSummaryView(attributes, abilities, virtues, disciplines, backgrounds, dictionary);
	}
	
	public CatSummaryView buildCatSummaryView(){
		return new CatSummaryView(buildSubCatSummaryView(),
				buildSubCatSummaryView(), buildSubCatSummaryView());
	}
	
	public SubCatSummaryView buildSubCatSummaryView(){
		return new SubCatSummaryView();
	}

}
