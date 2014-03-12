package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.gui.swing.view.Helper;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

class TitleSortedSubCatView extends SortedSubCatView{
	
	protected JPanel titledPanel = new JPanel();

	public TitleSortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		titledPanel.setBackground(Color.WHITE);
		titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
		JTextField field = Helper.getTitle(dictionary.getValue(title), viewAtts.getFont());
		titledPanel.add(field);
		titledPanel.add(panel);
		titledPanel.add(Box.createGlue());
	}
	
	@Override
	public JPanel getPanel(){
		return titledPanel;
	}

}
