package vampire.editor.plugin.api.view.sheet;

import java.util.List;

import vampire.editor.plugin.api.view.events.DataViewListener;

public interface DataView<V> {
	
	public List<? extends V> getEntries();
	
	public void add(V entry);
	
	public void remove(V entry);
	
	public void insert(int pos, V entry);
	
	public void addListener(DataViewListener<V> listener);
	
	public void removeListener(DataViewListener<V> listener);

}
