package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface SubCategory extends SubCategoryAPI, Iterable<Trait>{
	
	public int size();
	
	public Iterator<? extends Trait> getIterator();
	
	public SubCategory clone();
	
	public void add(Trait trait);
	
	public void insert(int pos, Trait trait);
	
	public void remove(Trait trait);
	
	public void setName(String string);

}
