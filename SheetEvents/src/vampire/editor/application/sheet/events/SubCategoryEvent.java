package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;

public class SubCategoryEvent implements SubCategoryEventAPI{
	
	private final SubCategoryControllerAPI source;
	
	private final TraitControllerAPI added;
	
	private final TraitControllerAPI removed;
	
	private final int position;
	
	

	public SubCategoryEvent(SubCategoryControllerAPI source,
			TraitControllerAPI added, TraitControllerAPI removed, int position) {
		super();
		this.source = source;
		this.position = position;
		this.added = added;
		this.removed = removed;
	}
	
	public int getPosition() {
		return position;
	}
	
	public SubCategoryControllerAPI getSource() {
		return source;
	}

	public TraitControllerAPI getAdded() {
		return added;
	}

	public TraitControllerAPI getRemoved() {
		return removed;
	}
	
	


	
	
	
	

}
