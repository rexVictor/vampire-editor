package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface Health extends HealthAPI, Iterable<HealthEntry>{
	
	public int size();
	
	public Iterator<? extends HealthEntry> getIterator();
	
	public Health clone();
	
	public void add(HealthEntry subCategory);
	
	public void insert(int pos, HealthEntry subCat);
	
	public void remove(HealthEntry subCat);
	
	public HealthEntry get(int i);

}