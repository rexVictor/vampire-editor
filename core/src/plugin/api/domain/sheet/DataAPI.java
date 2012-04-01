package plugin.api.domain.sheet;

import java.util.Iterator;

import plugin.api.domain.sheet.view.DataViewAttributes;

public interface DataAPI<V extends Nameable> {

	@SuppressWarnings("unchecked")
	public DataAPI<V> clone();

	public DataViewAttributes getAttributes();

	public Iterator<? extends V> getIterator();

	public String getName();

}