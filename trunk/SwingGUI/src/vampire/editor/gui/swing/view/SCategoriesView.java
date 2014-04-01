package vampire.editor.gui.swing.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.plugin.api.view.sheet.CategoriesView;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public class SCategoriesView implements CategoriesView{
	
	private final JPanel panel = Helper.createPanel();
	
	private final List<CategoryView> categoryViews = new LinkedList<>();
	
	public SCategoriesView(){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}

	@Override
	public void add(CategoryView s) {
		add0(s);
	}

	@Override
	public void add0(CategoryView s) {
		SCategoryView view = (SCategoryView) s;
		JPanel subPanel = view.getPanel();
		panel.add(subPanel);
		categoryViews.add(view);
	}

	@Override
	public List<? extends CategoryView> getCategoryViews() {
		return categoryViews;
	}

	public JPanel getPanel() {
		return panel;
	}
	
	

}
