package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface CategoriesAPI{
	
	/**
	 * This implementation is optional
	 */
	public CategoriesAPI clone();

	/**
	 * @return an {@link Iterator} over the parametrized children of type V
	 */
	public Iterator<? extends CategoryAPI> getIterator();
	
	/**
	 * @return the count of added elements.
	 */
	public int size();
	
	public CategoryAPI get(int i);


}
