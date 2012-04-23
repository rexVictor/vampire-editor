package vampire.editor.plugin.fullapi.sheet;

import java.util.Comparator;
import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.DataAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.view.DataViewAttributes;

public interface IData<V extends Nameable, W extends V> extends DataAPI<V>{

	public void add(W v);

	public IData<V, W> clone();

	public DataViewAttributes getAttributes();

	public Iterator<W> getIterator();

	public String getName();

	public void insert(int i, W v);

	public void remove(V v);

	public void setName(String name);

	public void sort(Comparator<String> comparator);

}