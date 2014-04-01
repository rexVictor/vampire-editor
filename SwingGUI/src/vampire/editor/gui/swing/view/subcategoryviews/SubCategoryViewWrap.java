package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Container;
import java.util.List;

import javax.swing.JPanel;

import vampire.editor.gui.swing.view.Helper;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SubCategoryViewWrap implements ISubCategoryView{
	
	private ISubCategoryView view;
	
	public SubCategoryViewWrap(ISubCategoryView subCategoryView) {
		super();
		this.view = subCategoryView;
	}
	
	public ISubCategoryView getSubCategoryView() {
		return view;
	}

	public void setSubCategoryView(ISubCategoryView subCategoryView) {
		this.view = subCategoryView;
		JPanel panel = view.getPanel();
		Container parent = panel.getParent();
		int index = Helper.getComponentIndex(parent, panel);
		parent.remove(index);
		parent.add(subCategoryView.getPanel(), index);
		parent.revalidate();
	}

	@Override
	public List<? extends TraitView> getEntries() {
		return view.getEntries();
	}

	@Override
	public void add(TraitView entry) {
		view.add(entry);
	}

	@Override
	public void remove(TraitView entry) {
		view.remove(entry);
	}

	@Override
	public void insert(int pos, TraitView entry) {
		view.insert(pos, entry);
	}

	@Override
	public void addListener(DataViewListener<TraitView> listener) {
		view.addListener(listener);
	}

	@Override
	public void removeListener(DataViewListener<TraitView> listener) {
		view.removeListener(listener);
	}

	@Override
	public void add0(TraitView s) {
		view.add0(s);
	}

	@Override
	public JPanel getPanel() {
		return view.getPanel();
	}

}
