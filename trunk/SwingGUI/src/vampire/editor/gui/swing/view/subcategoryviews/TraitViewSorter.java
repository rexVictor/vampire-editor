package vampire.editor.gui.swing.view.subcategoryviews;

import java.util.Comparator;

import vampire.editor.gui.swing.view.STraitView;
import vampire.editor.plugin.api.view.sheet.TraitView;

class TraitViewSorter implements Comparator<TraitView>{

	@Override
	public int compare(TraitView o1, TraitView o2) {
		STraitView tv1 = (STraitView) o1;
		String s1 = tv1.getField().getText();
		STraitView tv2 = (STraitView) o2;
		String s2 = tv2.getField().getText();
		return s1.compareTo(s2);
	}

}
