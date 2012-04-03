package vampire.editor.application.sheet.events;

import vampire.editor.application.sheet.controller.CategoryController;
import vampire.editor.application.sheet.controller.SubCategoryController;
import vampire.editor.plugin.api.application.sheet.events.CategoryEventAPI;

public class CategoryEvent implements CategoryEventAPI{
	
	private final CategoryController source;
	
	private final SubCategoryController reason;
	
	private final int position;

	public CategoryEvent(CategoryController source,
			SubCategoryController reason, int position) {
		super();
		this.source = source;
		this.reason = reason;
		this.position = position;
	}

	public CategoryController getSource() {
		return source;
	}

	public SubCategoryController getReason() {
		return reason;
	}

	public int getPosition() {
		return position;
	}
	
	
	
	

}
