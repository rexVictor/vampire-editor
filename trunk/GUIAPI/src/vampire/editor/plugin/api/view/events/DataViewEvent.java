package vampire.editor.plugin.api.view.events;

import vampire.editor.plugin.api.domain.sheet.Nameable;

public interface DataViewEvent<V extends Nameable> {
	
	public V getSource();
	
	public V getTarget();
	
	public int getPositionOfSource();
	
	public int getPositionOfTarget();
	
	

}
