package vampire.editor.gui.swing.view.subcategoryviews;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.TraitView;

/**
 * The SubCategoryView for not sorted, not expandable and showTitle
 * @author rex
 *
 */
class TitleSubCatView extends AbstractSubCategoryView{

	public TitleSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		TitleInserter.insertTitle(panel, dictionary.getValue(title), viewAtts.getFont());
	}

	@Override
	public void add(TraitView entry){}
	
	@Override
	public void remove(TraitView entry){}

	@Override
	public void insert(int pos, TraitView entry){}

}
