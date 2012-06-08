package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;

public class SubCategoryEvent implements SubCategoryEventAPI{
	
	private final SubCategoryControllerAPI source;
	
	private final TraitControllerAPI reason;
	
	private final int position;
	
	

	public SubCategoryEvent(SubCategoryControllerAPI source,
			TraitControllerAPI reason, int position) {
		super();
		this.source = source;
		this.reason = reason;
		this.position = position;
	}
	
	



	public TraitControllerAPI getReason() {
		return reason;
	}





	public int getPosition() {
		return position;
	}





	public SubCategoryControllerAPI getSource() {
		return source;
	}


	
	
	
	

}
