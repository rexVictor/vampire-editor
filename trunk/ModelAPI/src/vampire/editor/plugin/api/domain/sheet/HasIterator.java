package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface HasIterator<V> {
	
	public Iterator<? extends V> getIterator();

}
