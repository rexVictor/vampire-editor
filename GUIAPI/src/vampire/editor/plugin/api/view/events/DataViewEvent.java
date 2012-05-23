package vampire.editor.plugin.api.view.events;


public interface DataViewEvent<V> {
	
	public V getSource();
	
	public V getTarget();
	
	public int getPositionOfSource();
	
	public int getPositionOfTarget();
	
	

}
