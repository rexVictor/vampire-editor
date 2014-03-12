package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;

import javax.swing.BoxLayout;
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

	public TitleSubCatView(DictionaryAPI dictionary,
			SubCategoryViewAttributes viewAtts, String title) {
		super(dictionary, viewAtts);
		titledPanel.setBackground(Color.WHITE);
		titledPanel.setLayout(new BoxLayout(titledPanel, BoxLayout.Y_AXIS));
		TitleInserter.insertTitle(titledPanel, dictionary.getValue(title), viewAtts.getFont());
		titledPanel.add(panel, 1);
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
