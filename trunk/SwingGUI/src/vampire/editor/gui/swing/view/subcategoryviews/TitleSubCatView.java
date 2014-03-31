package vampire.editor.gui.swing.view.subcategoryviews;

import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.TraitView;

/**
 * The SubCategoryView for not sorted, not expandable and showTitle
 * @author rex
 *
 */
class TitleSubCatView extends AbstractSubCategoryView{
	
	protected JPanel titledPanel = new JPanel();

	protected TitleSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		TitleMaker.makeTitle(titledPanel, panel, dictionary, viewAtts, title);
	}
	
	protected TitleSubCatView(){
		
	}

	@Override
	public void add(TraitView entry){}
	
	@Override
	public void remove(TraitView entry){}

	@Override
	public void insert(int pos, TraitView entry){}

	@Override
	public JPanel getPanel(){
		return titledPanel;
	}

	@Override
	public AbstractSubCategoryView newInstance(
			SubCategoryViewAttributes viewAtts, DictionaryAPI dictionary,
			String title) {
		return new TitleSubCatView(dictionary, viewAtts, title);
	}
}
