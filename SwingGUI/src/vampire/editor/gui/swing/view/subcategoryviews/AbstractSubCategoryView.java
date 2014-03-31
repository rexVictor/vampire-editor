package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import vampire.editor.gui.swing.view.Helper;
import vampire.editor.gui.swing.view.traitviews.AbstractTraitView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.Addable;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public abstract class AbstractSubCategoryView implements SubCategoryView, Addable<TraitView>{
	
	private static final Map<SSubCategoryViewAtts, AbstractSubCategoryView> views = new HashMap<>();
	
	static{
		views.put(new SSubCategoryViewAtts(true, true, true), new ExpTitleSortedSubCatView());
		views.put(new SSubCategoryViewAtts(true, true, false), new ExpTitleSubCatView());
		views.put(new SSubCategoryViewAtts(true, false, true), new ExpSortedSubCatView());
		views.put(new SSubCategoryViewAtts(true, false, false), new ExpSubCatView());
		views.put(new SSubCategoryViewAtts(false, true, true), new TitleSortedSubCatView());
		views.put(new SSubCategoryViewAtts(false, true, false), new TitleSubCatView());
		views.put(new SSubCategoryViewAtts(false, false, true), new SortedSubCatView());
		views.put(new SSubCategoryViewAtts(false, false, false), new DefaultSubCatView());
	}
	
	public static AbstractSubCategoryView buildSubCategoryView(SubCategoryViewAttributes viewAtts
												,DictionaryAPI dictionary
												,String title){
		SSubCategoryViewAtts sViewAtts = new SSubCategoryViewAtts(viewAtts);
		return views.get(sViewAtts).newInstance(viewAtts, dictionary, title);
	}
	
	protected final DictionaryAPI dictionary;
	
	protected final SubCategoryViewAttributes viewAtts;
	
	private final List<DataViewListener<TraitView>> traitViewListeners = new LinkedList<>();
	
	private final List<TraitView> traitViews = new LinkedList<>();
	
	protected final JPanel panel = Helper.createPanel();
	
	protected AbstractSubCategoryView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts) {
		super();
		this.dictionary = dictionary;
		this.viewAtts = viewAtts;
		this.panel.setLayout(new GridLayout(0, 1));
	}
	
	protected AbstractSubCategoryView(){
		this.dictionary = null;
		this.viewAtts = null;
	}

	@Override
	public List<? extends TraitView> getEntries() {
		return traitViews;
	}

	@Override
	public abstract void add(TraitView entry);
	
	public void add0(TraitView entry){
		AbstractTraitView view = (AbstractTraitView) entry;
		panel.add(view.getPanel());
		traitViews.add(view);
		panel.revalidate();
		panel.repaint();
	}

	@Override
	public abstract void remove(TraitView entry);
	
	public void remove0(TraitView entry){
		if (entry instanceof AbstractTraitView){
			AbstractTraitView view = (AbstractTraitView) entry;
			panel.remove(view.getPanel());
			traitViews.remove(view);
			panel.revalidate();
			panel.repaint();
		}		
	}

	@Override
	public abstract void insert(int pos, TraitView entry);

	public void insert0(int pos, TraitView entry){
		AbstractTraitView view = (AbstractTraitView) entry;
		panel.add(view.getPanel(), pos);
		traitViews.add(view);
		panel.revalidate();
		panel.repaint();
	}
	
	@Override
	public void addListener(DataViewListener<TraitView> listener) {
		traitViewListeners.add(listener);
	}

	@Override
	public void removeListener(DataViewListener<TraitView> listener) {
		traitViewListeners.remove(listener);
	}

	public JPanel getPanel() {
		return panel;
	}
	
	public abstract AbstractSubCategoryView newInstance(SubCategoryViewAttributes viewAtts,
														DictionaryAPI dictionary, String title);

}
