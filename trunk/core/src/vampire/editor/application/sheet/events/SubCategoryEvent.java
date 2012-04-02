package vampire.editor.application.sheet.events;

import vampire.editor.application.sheet.controller.SubCategoryController;
import vampire.editor.application.sheet.controller.TraitController;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;

public class SubCategoryEvent implements SubCategoryEventAPI{
	
	private final SubCategoryController source;
	
	private final TraitController reason;
	
	private final int position;
	
	

	public SubCategoryEvent(SubCategoryController source,
			TraitController reason, int position) {
		super();
		this.source = source;
		this.reason = reason;
		this.position = position;
	}
	
	



	public TraitController getReason() {
		return reason;
	}





	public int getPosition() {
		return position;
	}





	public SubCategoryController getSource() {
		return source;
	}


	
	
	
	

}
