package vampire.editor.gui.swing.view.traitviews;

import javax.swing.BoxLayout;
import javax.swing.JTextField;

import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;

public class VerticalTraitView extends AbstractTraitView{

	public VerticalTraitView(AbstractValueView valueView,
			TraitViewAttributes viewAtts, DictionaryAPI dictionary) {
		super(valueView, viewAtts, dictionary);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(textField);
		panel.add(valueView.getPanel());
	}
	
	@Override
	protected void initializeTextField(){
		super.initializeTextField();
		textField.setHorizontalAlignment(JTextField.CENTER);
	}

}
