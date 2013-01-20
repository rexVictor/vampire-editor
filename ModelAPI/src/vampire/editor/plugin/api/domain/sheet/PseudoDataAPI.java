package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface PseudoDataAPI<V extends Nameable> extends Nameable{

	public Iterator<? extends V> getIterator();

	@Override
	public String getName();

}