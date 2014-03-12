package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.gui.swing.view.Helper;
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

	public TitleSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		titledPanel.setBackground(Color.WHITE);
		JTextField field = Helper.getTitle(dictionary.getValue(title), viewAtts.getFont());
		titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
		titledPanel.add(field);
		panel.setMaximumSize(new Dimension(panel.getMaximumSize().width, panel.getPreferredSize().height));
		titledPanel.add(panel);
		titledPanel.add(Box.createGlue());
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
}
