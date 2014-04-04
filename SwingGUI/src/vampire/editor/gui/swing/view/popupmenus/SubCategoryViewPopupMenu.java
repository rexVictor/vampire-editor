package vampire.editor.gui.swing.view.popupmenus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import vampire.editor.gui.swing.view.properties.SubCategoryViewProperties;
import vampire.editor.gui.swing.view.subcategoryviews.ISubCategoryView;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.plugin.view.factories.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SubCategoryViewPopupMenu implements SubCategoryViewFactoryModule, ActionListener{
	
	public SubCategoryViewPopupMenu(){
	}

	@Override
	public void addToChild(SubCategoryAPI m, ModelToViewModelMapperAPI mapper,
			TraitView subView) {}

	@Override
	public void processInitial(SubCategoryAPI m,
			ModelToViewModelMapperAPI mapper) {}

	@Override
	public void processFinal(SubCategoryAPI m,
			ModelToViewModelMapperAPI mapper, SubCategoryView view) {
		ISubCategoryView subCatView = (ISubCategoryView) view;
		JPopupMenu menu = new JPopupMenu();
		JMenuItem properties = new JMenuItem("properties");
		menu.add(properties);
		properties.addActionListener(new PropertiesTrigger(new SubCategoryViewProperties(null, subCatView)));
		subCatView.setPopupMenu(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
