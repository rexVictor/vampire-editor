package vampire.editor.plugin.api.view.events;


public interface DataViewListener<V> {
	
	public void added(DataViewEvent<V> event);
	
	public void remove(DataViewEvent<V> event);
	
	public void swapped(DataViewEvent<V> event);

}
