package vampire.editor.gui.swing.view.subcategoryviews;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.TraitView;

class SortedSubCatView extends AbstractSubCategoryView{
	
	private final List<TraitView> sortedTraitViews = new LinkedList<>();
	
	private static final Comparator<TraitView> COMPARATOR = new TraitViewSorter();

	public SortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts) {
		super(dictionary, viewAtts);
	}
	
	protected SortedSubCatView(){
		
	}

	@Override
	public void add(TraitView entry) {}
	
	@Override
	public void add0(TraitView entry){
		sortedTraitViews.add(entry);
		Collections.sort(sortedTraitViews, COMPARATOR);
		int pos = sortedTraitViews.indexOf(entry);
		insert0(pos, entry);
	}

	@Override
	public void remove(TraitView entry) {}

	@Override
	public void insert(int pos, TraitView entry) {}

	@Override
	public SortedSubCatView newInstance(
			SubCategoryViewAttributes viewAtts, DictionaryAPI dictionary,
			String title) {
		return new SortedSubCatView(dictionary, viewAtts);
	}
	
	

}
