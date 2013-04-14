package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface Meta extends MetaAPI, Iterable<MetaEntry>{
	
	public Iterator<? extends MetaEntry> getIterator();
	
	public Meta clone();
	
	public void add(MetaEntry metaEntry);
	
	public void insert(int pos, MetaEntry metaEntry);
	
	public void remove(MetaEntry metaEntry);
	
	public MetaEntry get(int i);

}