package vampire.editor.plugin.fullapi.sheet;

import java.util.Comparator;
import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.DataAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.view.DataViewAttributesAPI;

public interface IData<V extends Nameable> extends DataAPI<V>{

	public void add(V v);

	public IData<V> clone();

	public DataViewAttributesAPI getAttributes();

	public Iterator<V> getIterator();

	public String getName();

	public void insert(int i, V v);

	public void remove(V v);

	public void setName(String name);

	public void sort(Comparator<String> comparator);

}