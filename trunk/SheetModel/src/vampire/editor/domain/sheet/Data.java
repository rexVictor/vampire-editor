package vampire.editor.domain.sheet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.view.DataViewAttributesAPI;
import vampire.editor.plugin.fullapi.sheet.IData;

public class Data<W extends Nameable> implements IData<W>{
	
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
	
	private final DataViewAttributesAPI attributes;
	
	private final List<W> entries = new ArrayList<>();
	
	private String name;
	
	
	
	public Data(DataViewAttributesAPI attributes) {
		super();
		this.attributes = attributes;
	}
	
	@Override
	public void add(W v){
		entries.add(v);
	}

	
	
	@Override
	@SuppressWarnings("unchecked")
	public Data<W> clone(){
		Data<W> data = new Data<W>(attributes);
		data.name = this.name;
		for (W v : entries){
			data.entries.add((W) v.clone());
		}
		return data;
	}
	

	@Override
	public DataViewAttributesAPI getAttributes() {
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
	
	@Override	
	public void insert(int i, W v){
		entries.add(i, v);
	}
	
	@Override
	public void remove(W v){
		entries.remove(v);
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void sort(Comparator<String> comparator){
		Sorter<W> sorter = new Sorter<>(comparator);
		sorter.sort(entries);
	}
	
	@Override
	public String toString(){
		return name+" : " +entries.toString();
	}
	
	

}
