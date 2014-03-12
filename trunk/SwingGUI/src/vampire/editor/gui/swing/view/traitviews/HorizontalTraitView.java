package vampire.editor.gui.swing.view.traitviews;

import java.awt.BorderLayout;

import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;

public class HorizontalTraitView extends AbstractTraitView{

	public HorizontalTraitView(AbstractValueView valueView,
			TraitViewAttributes viewAtts, DictionaryAPI dictionary) {
		super(valueView, viewAtts, dictionary);
		panel.setLayout(new BorderLayout());
		panel.add(textField, BorderLayout.CENTER);
		panel.add(valueView.getPanel(), BorderLayout.EAST);
	}

}
