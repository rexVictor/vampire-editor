package vampire.editor.gui.swing.view.subcategoryviews;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

/**
 * The {@link SubCategoryView} for not sorted, expandable and showTitle
 * @author rex
 *
 */
class ExpTitleSubCatView extends TitleSubCatView{

	protected ExpTitleSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts, title);
	}
	
	protected ExpTitleSubCatView(){
		
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
	
	@Override
	public ExpTitleSubCatView newInstance(SubCategoryViewAttributes viewAtts, DictionaryAPI dictionary, String title){
		return new ExpTitleSubCatView(dictionary, viewAtts, title);
	}

}
