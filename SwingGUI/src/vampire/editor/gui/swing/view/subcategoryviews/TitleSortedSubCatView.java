package vampire.editor.gui.swing.view.subcategoryviews;

import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

class TitleSortedSubCatView extends SortedSubCatView{
	
	protected JPanel titledPanel = new JPanel();

	public TitleSortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		TitleMaker.makeTitle(titledPanel, panel, dictionary, viewAtts, title);
	}
	
	@Override
	public JPanel getPanel(){
		return titledPanel;
	}

}
