package vampire.editor.gui.swing.view.traitviews;

import java.awt.BorderLayout;

import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;

class HorizontalTraitView extends AbstractTraitView{

	private HorizontalTraitView(AbstractValueView valueView,
			TraitViewAttributes viewAtts, DictionaryAPI dictionary) {
		super(valueView, viewAtts, dictionary);
		panel.setLayout(new BorderLayout());
		panel.add(textField, BorderLayout.CENTER);
		panel.add(valueView.getPanel(), BorderLayout.EAST);
	}
	
	HorizontalTraitView(){
	}

	@Override
	public AbstractTraitView createNewInstance(AbstractValueView vv,
			TraitViewAttributes atts, DictionaryAPI dictionary) {
		return new HorizontalTraitView(vv, atts, dictionary);
	}

}
