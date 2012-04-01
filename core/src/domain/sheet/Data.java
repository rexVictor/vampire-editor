package domain.sheet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import plugin.api.domain.sheet.Nameable;
import plugin.api.domain.sheet.view.DataViewAttributes;

public class Data<V extends Nameable> implements Nameable{
	
	private class DataIterator implements Iterator<V>{
		
		private Iterator<V> iterator = entries.iterator();
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public V next() {
			return iterator.next();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	private final DataViewAttributes attributes;
	
	private final List<V> entries = new ArrayList<>();
	
	private String name;
	
	
	
	public Data(DataViewAttributes attributes) {
		super();
		this.attributes = attributes;
	}

	public void add(V v){
		entries.add(v);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Data<V> clone(){
		Data<V> data = new Data<V>(attributes);
		data.name = this.name;
		for (V v : entries){
			data.entries.add((V) v.clone());
		}
		return data;
	}
	
	public DataViewAttributes getAttributes() {
		return attributes;
	}
	
	public Iterator<V> getIterator(){
		return new DataIterator();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void insert(int i, V v){
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
