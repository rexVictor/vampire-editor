package vampire.editor.plugin.api.view.events;

import vampire.editor.plugin.api.domain.sheet.Nameable;

public interface DataViewListener<V extends Nameable> {
	
	public void added(DataViewEvent<V> event);
	
	public void remove(DataViewEvent<V> event);
	
	public void swapped(DataViewEvent<V> event);

}
