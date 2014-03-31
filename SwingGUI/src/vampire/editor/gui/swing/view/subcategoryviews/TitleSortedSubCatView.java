package vampire.editor.gui.swing.view.subcategoryviews;

import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

class TitleSortedSubCatView extends SortedSubCatView{
	
	protected JPanel titledPanel = new JPanel();

	protected TitleSortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		TitleMaker.makeTitle(titledPanel, panel, dictionary, viewAtts, title);
	}
	
	protected TitleSortedSubCatView(){
		
	}
	
	@Override
	public JPanel getPanel(){
		return titledPanel;
	}
	
	@Override
	public TitleSortedSubCatView newInstance(SubCategoryViewAttributes viewAtts, DictionaryAPI dictionary, String title){
		return new TitleSortedSubCatView(dictionary, viewAtts, title);
	}

}
