package vampire.editor.plugin.tooltipadder;

import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;

public class SubCatListener implements SubCategoryListener{
	
	private final TraitTooltipAdder traitTooltipAdder;
	
	public SubCatListener(TraitTooltipAdder traitTooltipAdder) {
		super();
		this.traitTooltipAdder = traitTooltipAdder;
	}

	@Override
	public void added(SubCategoryEventAPI e) {
		e.getReason().addListener(traitTooltipAdder);
	}

	@Override
	public void removed(SubCategoryEventAPI e) {}

}
