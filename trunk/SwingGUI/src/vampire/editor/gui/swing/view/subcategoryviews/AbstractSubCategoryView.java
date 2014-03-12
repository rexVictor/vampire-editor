package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import vampire.editor.gui.swing.view.traitviews.AbstractTraitView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public abstract class AbstractSubCategoryView implements SubCategoryView{
	
	public static AbstractSubCategoryView buildSubCategoryView(SubCategoryViewAttributes viewAtts
												,DictionaryAPI dictionary
												,String title){
		if (viewAtts.isShallSort()){
			if (viewAtts.isShowTitle()){
				if (viewAtts.isExpandable()){
					return new ExpTitleSortedSubCatView(dictionary, viewAtts, title);
				}
				else {
					return new TitleSortedSubCatView(dictionary, viewAtts, title);
				}
			}
			else if (viewAtts.isExpandable()){
					return new ExpSortedSubCatView(dictionary, viewAtts);
			}
			else {
				return new SortedSubCatView(dictionary, viewAtts);
			}
		}
		else if (viewAtts.isShowTitle()){
			if (viewAtts.isExpandable()){
				return new ExpTitleSubCatView(dictionary, viewAtts, title);
			}
			else {
				return new TitleSubCatView(dictionary, viewAtts, title);
			}
		}
		else if (viewAtts.isExpandable()){
			return new ExpSubCatView(dictionary, viewAtts);
		}
		else return new DefaultSubCatView(dictionary, viewAtts);
	}
	
	protected final DictionaryAPI dictionary;
	
	protected final SubCategoryViewAttributes viewAtts;
	
	private final List<DataViewListener<TraitView>> traitViewListeners = new LinkedList<>();
	
	private final List<TraitView> traitViews = new LinkedList<>();
	
	protected final JPanel panel = new JPanel();
	
	public AbstractSubCategoryView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts) {
		super();
		this.dictionary = dictionary;
		this.viewAtts = viewAtts;
		panel.setBackground(Color.WHITE);
		this.panel.setLayout(new GridLayout(0, 1));
		
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

}
