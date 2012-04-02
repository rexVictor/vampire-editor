package plugin.api.domain.sheet;

import plugin.api.domain.sheet.view.CategoryViewAttributes;

public interface CategoryAPI {

	public CategoryAPI clone();

	public CategoryViewAttributes getAttributes();

}