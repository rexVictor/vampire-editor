package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;

public interface CategoryAPI {

	public CategoryAPI clone();

	public CategoryViewAttributes getAttributes();

}