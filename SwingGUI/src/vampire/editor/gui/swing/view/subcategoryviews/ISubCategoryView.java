package vampire.editor.gui.swing.view.subcategoryviews;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public interface ISubCategoryView extends SubCategoryView{
	
	public JPanel getPanel();
	
	public SubCategoryViewAttributes getViewAtts();
	
	public void setPopupMenu(JPopupMenu popupMenu);

}
