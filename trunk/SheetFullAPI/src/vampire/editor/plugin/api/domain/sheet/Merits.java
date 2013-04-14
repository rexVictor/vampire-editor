package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface Merits extends MeritsAPI, Iterable<Merit>{
	
	public int size();
	
	public Iterator<? extends Merit> getIterator();
	
	public Merits clone();
	
	public void add(Merit merit);
	
	public void insert(int pos, Merit merit);
	
	public void remove(Merit merit);
	
	public Merit get(int i);
}