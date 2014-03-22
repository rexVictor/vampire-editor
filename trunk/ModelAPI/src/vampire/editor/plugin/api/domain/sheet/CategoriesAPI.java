package vampire.editor.plugin.api.domain.sheet;

public interface CategoriesAPI extends HasIterator<CategoryAPI>{
	
	/**
	 * This implementation is optional
	 */
	public CategoriesAPI clone();

	/**
	 * @return the count of added elements.
	 */
	public int size();
	
	public CategoryAPI get(int i);


}
