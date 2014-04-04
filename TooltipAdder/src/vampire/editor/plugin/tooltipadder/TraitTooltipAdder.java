package vampire.editor.plugin.tooltipadder;

import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitEventAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitListener;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class TraitTooltipAdder implements TraitListener{
	
	private final DictionaryAPI tooltips;
	
	public TraitTooltipAdder(DictionaryAPI tooltips) {
		super();
		this.tooltips = tooltips;
	}

	@Override
	public void traitNameChanged(TraitEventAPI event) {
		TraitControllerAPI traitController = event.getSource();
		ValueControllerAPI valueController = traitController.getValueController();
		TraitView traitView = traitController.getView();
		ValueView valueView = valueController.getView();
		update(traitView, valueView, event.getNewName());
	}
	
	public void update(TraitView traitView, ValueView valueView, String newName){
		traitView.setTooltip(tooltips.getValue(newName));
		for (int i = 0; i < 10; i++){
			valueView.setToolTip(i, tooltips.getValue(newName+(i+1)));
		}
	}

}
