package vampire.editor.domain.sheet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.DataAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.view.DataViewAttributes;

public class Data<V extends Nameable, W extends V> implements Nameable, DataAPI<V>{
	
	private class DataIterator implements Iterator<W>{
		
		private Iterator<W> iterator = entries.iterator();
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public W next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	private final DataViewAttributes attributes;
	
	private final List<W> entries = new ArrayList<>();
	
	private String name;
	
	
	
	public Data(DataViewAttributes attributes) {
		super();
		this.attributes = attributes;
	}

	public void add(W v){
		entries.add(v);
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public Data<V,W> clone(){
		Data<V, W> data = new Data<V, W>(attributes);
		data.name = this.name;
		for (W v : entries){
			data.entries.add((W) v.clone());
		}
		return data;
	}
	

	@Override
	public DataViewAttributes getAttributes() {
		return attributes;
	}
	
	
	@Override
	public Iterator<W> getIterator(){
		return new DataIterator();
	}
	
	
	
	@Override
	public String getName() {
		return name;
	}
	
	public void insert(int i, W v){
		entries.add(i, v);
	}
	
	public void remove(V v){
		entries.remove(v);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sort(Comparator<String> comparator){
		Sorter<V> sorter = new Sorter<>(comparator);
		sorter.sort(entries);
	}
	
	

}
