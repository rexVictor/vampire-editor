package vampire.editor.plugin.api.domain.sheet;

public interface CategoryAPI extends PseudoDataAPI<SubCategoryAPI>, Nameable{

	@Override
	public CategoryAPI clone();
}