package vampire.editor.gui.swing.view.subcategoryviews;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

class TitleSortedSubCatView extends SortedSubCatView{

	public TitleSortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		TitleInserter.insertTitle(panel, dictionary.getValue(title), viewAtts.getFont());
		shift+=1;
	}

}
