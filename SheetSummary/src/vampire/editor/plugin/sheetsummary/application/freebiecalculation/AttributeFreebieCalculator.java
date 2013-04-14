package vampire.editor.plugin.sheetsummary.application.freebiecalculation;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.sheetsummary.domain.FreebieMap;
import vampire.editor.plugin.sheetsummary.domain.PseudoSubCat;

public class AttributeFreebieCalculator implements ValueListener{
	
	private class Sorter implements Comparator<PseudoSubCat>{

		@Override
		public int compare(PseudoSubCat o1, PseudoSubCat o2) {
			return Integer.compare(o1.sum(), o2.sum());
		}
		
	}
	
	private final FreebieMap freebieMap;
	
	private final CategoryAPI attributes;
	
	private int freebies;
	
	private final List<PseudoSubCat> subCats = new LinkedList<>();
	
	private final Sorter sorter = new Sorter();
	
	public AttributeFreebieCalculator(FreebieMap freebieMap,
			CategoryAPI attributes){ 
		super();
		this.freebieMap = freebieMap;
		this.attributes = attributes;
		for (Iterator<? extends SubCategoryAPI> i = attributes.getIterator(); i.hasNext();){
			SubCategoryAPI subCat = i.next();
			PseudoSubCat pseudoSubCat = new PseudoSubCat();
			subCats.add(pseudoSubCat);
			for (Iterator<? extends TraitAPI> j = subCat.getIterator(); j.hasNext();){
				TraitAPI trait = j.next();
				pseudoSubCat.addValue(trait.getValue());
			}
		}
	}
	
	private void recalculate(){
		Collections.sort(subCats, sorter);
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		recalculate();
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}

	
}
