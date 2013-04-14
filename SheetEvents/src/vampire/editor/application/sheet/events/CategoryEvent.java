package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.CategoryEventAPI;

public class CategoryEvent implements CategoryEventAPI{
	
	private final CategoryControllerAPI source;
	
	private final SubCategoryControllerAPI reason;
	
	private final int position;
	
	

	public CategoryEvent(CategoryControllerAPI source,
			SubCategoryControllerAPI reason, int position) {
		super();
		this.source = source;
		this.reason = reason;
		this.position = position;
	}
	
	



	public SubCategoryControllerAPI getReason() {
		return reason;
	}





	public int getPosition() {
		return position;
	}





	public CategoryControllerAPI getSource() {
		return source;
	}

}
