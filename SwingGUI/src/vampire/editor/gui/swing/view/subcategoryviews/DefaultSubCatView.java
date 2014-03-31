package vampire.editor.gui.swing.view.subcategoryviews;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.TraitView;

class DefaultSubCatView extends AbstractSubCategoryView{

	protected DefaultSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts) {
		super(dictionary, viewAtts);
	}
	
	protected DefaultSubCatView(){
		
	}

	@Override
	public void add(TraitView entry) {}

	@Override
	public void remove(TraitView entry) {}

	@Override
	public void insert(int pos, TraitView entry) {}

	@Override
	public DefaultSubCatView newInstance(
			SubCategoryViewAttributes viewAtts, DictionaryAPI dictionary,
			String title) {
		return new DefaultSubCatView(dictionary, viewAtts);
	}

}
