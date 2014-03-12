package vampire.editor.gui.swing.view.subcategoryviews;

import java.util.Comparator;

import vampire.editor.gui.swing.view.traitviews.AbstractTraitView;
import vampire.editor.plugin.api.view.sheet.TraitView;

class TraitViewSorter implements Comparator<TraitView>{

	@Override
	public int compare(TraitView o1, TraitView o2) {
		AbstractTraitView tv1 = (AbstractTraitView) o1;
		String s1 = tv1.getField().getText();
		AbstractTraitView tv2 = (AbstractTraitView) o2;
		String s2 = tv2.getField().getText();
		return s1.compareTo(s2);
	}

}
