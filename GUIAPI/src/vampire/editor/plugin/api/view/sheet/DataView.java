package vampire.editor.plugin.api.view.sheet;

import java.util.List;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.view.events.DataViewListener;

public interface DataView<V, M extends Nameable> {
	
	public List<V> getEntries();
	
	public void add(V entry);
	
	public V add(M entry);
	
	public void remove(V entry);
	
	public void insert(int pos, V entry);
	
	public void addListener(DataViewListener<?> listener);
	
	public void removeListener(DataViewListener<?> listener);

}
