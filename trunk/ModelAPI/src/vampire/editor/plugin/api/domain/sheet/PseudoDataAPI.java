package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.view.DataViewAttributesAPI;

public interface PseudoDataAPI<V extends Nameable> extends Nameable{

	public DataViewAttributesAPI getAttributes();

	public Iterator<? extends V> getIterator();

	public String getName();

}