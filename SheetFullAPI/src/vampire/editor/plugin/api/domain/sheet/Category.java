package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface Category extends CategoryAPI, Iterable<SubCategory>{
	
	public int size();
	
	public Iterator<? extends SubCategory> getIterator();
	
	public Category clone();
	
	public void add(SubCategory subCategory);
	
	public void insert(int pos, SubCategory subCat);
	
	public void remove(SubCategory subCat);
	
	public void setName(String name);
}
