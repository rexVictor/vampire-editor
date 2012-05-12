package vampire.editor.plugin.fullapi.sheet;

import java.util.Comparator;
import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.DataAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.fullapi.sheet.view.IDataViewAttributes;

public interface IData<V extends Nameable> extends DataAPI<V>{

	public void add(V v);

	@Override
	public IData<V> clone();

	@Override
	public IDataViewAttributes getViewAtts();
	
	public void setViewAtts(IDataViewAttributes viewAtts);

	@Override
	public Iterator<V> getIterator();

	@Override
	public String getName();

	public void insert(int i, V v);

	public void remove(V v);

	public void setName(String name);

	public void sort(Comparator<String> comparator);

}