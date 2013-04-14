package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface Categories extends DataAPI<Category>, Iterable<Category>{
	
public int size();
	
	public Iterator<? extends Category> getIterator();
	
	public Categories clone();
	
	public void add(Category category);
	
	public void insert(int pos, Category category);
	
	public void remove(Category category);
	
	public void setName(String name);

}
