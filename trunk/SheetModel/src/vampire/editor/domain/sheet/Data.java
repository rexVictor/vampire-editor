package vampire.editor.domain.sheet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.DataAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.fullapi.sheet.view.IDataViewAttributes;

public class Data<W extends Nameable> implements DataAPI<W>, Iterable<W>{
	
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
	
	private IDataViewAttributes viewAtts;
	
	private final List<W> entries = new ArrayList<>();
	
	private String name;
	
	public Data(){
		
	}
	
	public Data(IDataViewAttributes attributes) {
		super();
		this.viewAtts = attributes;
	}
	
	public void add(W v){
		entries.add(v);
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public Data<W> clone(){
		Data<W> data = new Data<>(viewAtts);
		data.name = this.name;
		for (W v : entries){
			data.entries.add((W) v.clone());
		}
		return data;
	}
	

	@Override
	public IDataViewAttributes getViewAtts() {
		return viewAtts;
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
	
	public void remove(W v){
		entries.remove(v);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void sort(Comparator<String> comparator){
		Sorter<W> sorter = new Sorter<>(comparator);
		sorter.sort(entries);
	}
	
	@Override
	public String toString(){
		return name+" : " +entries.toString();
	}

	@Override
	public Iterator<W> iterator() {
		return new DataIterator();
	}

	public void setViewAtts(IDataViewAttributes viewAtts) {
		if (this.viewAtts==null) 
			this.viewAtts = viewAtts;
		
	}
	
	

}
