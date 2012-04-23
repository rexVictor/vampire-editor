package vampire.editor.domain.sheet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.Nameable;

public class Sorter<V extends Nameable> {
	
	private final Comparator<String> stringComparator;
	
	private class DataComparator implements Comparator<V>{

		@Override
		public int compare(V o1, V o2) {
			return stringComparator.compare(o1.getName(), o2.getName());
		}
		
	}
	
	private final Comparator<V> comparator = new DataComparator();
	

	public Sorter(Comparator<String> stringComparator) {
		super();
		this.stringComparator = stringComparator;
	}
	
	public void sort(List<? extends V> list){
		Collections.sort(list, comparator);
	}
	
	

}
