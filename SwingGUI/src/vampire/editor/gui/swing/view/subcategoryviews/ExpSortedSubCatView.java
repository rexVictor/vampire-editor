package vampire.editor.gui.swing.view.subcategoryviews;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.TraitView;

class ExpSortedSubCatView extends SortedSubCatView{

	public ExpSortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts) {
		super(dictionary, viewAtts);
	}
	
	@Override
	public void add(TraitView entry){
		add0(entry);
	}
	
	@Override
	public void remove(TraitView entry){
		remove0(entry);
	}
	
	@Override
	public void insert(int pos, TraitView entry){
		insert0(pos, entry);
	}

}
