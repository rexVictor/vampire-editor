package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

class TitleSortedSubCatView extends SortedSubCatView{
	
	protected JPanel titledPanel = new JPanel();

	public TitleSortedSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		titledPanel.setBackground(Color.WHITE);
		titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
		TitleInserter.insertTitle(titledPanel, dictionary.getValue(title), viewAtts.getFont());
		titledPanel.add(panel, 1);
	}
	
	@Override
	public JPanel getPanel(){
		return titledPanel;
	}

}
