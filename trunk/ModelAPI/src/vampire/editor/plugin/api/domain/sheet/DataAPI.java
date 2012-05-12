package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.view.DataViewAttributesAPI;

public interface DataAPI<V extends Nameable> extends Nameable, Iterable<V>{

	@Override
	public DataAPI<? extends V> clone();

	public DataViewAttributesAPI getViewAtts();

	public Iterator<? extends V> getIterator();

	@Override
	public String getName();

}