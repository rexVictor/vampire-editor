package vampire.editor.gui.swing.view.traitviews;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;

class VerticalTraitView extends AbstractTraitView{

	private VerticalTraitView(AbstractValueView valueView,
			TraitViewAttributes viewAtts, DictionaryAPI dictionary) {
		super(valueView, viewAtts, dictionary);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(textField);
		textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		valueView.getPanel().setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(valueView.getPanel());
	}
	
	VerticalTraitView(){
	}
	
	@Override
	protected void initializeTextField(){
		super.initializeTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	@Override
	public AbstractTraitView createNewInstance(AbstractValueView vv,
			TraitViewAttributes atts, DictionaryAPI dictionary) {
		return new VerticalTraitView(vv, atts, dictionary);
	}

}
