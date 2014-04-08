package vampire.editor.plugin.tooltipadder;

import java.util.ResourceBundle;

import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitEventAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class TraitTooltipAdder implements TraitListener{
	
	private final ResourceBundle bundle;
	
	public TraitTooltipAdder(ResourceBundle bundle) {
		super();
		this.bundle = bundle;
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
		if (bundle.containsKey(newName)){
			traitView.setTooltip(bundle.getString(newName));
		}
		else {
			traitView.setTooltip(null);
		}
		for (int i = 0; i < 10; i++){
			String toGet = newName+(i+1);
			if (bundle.containsKey(toGet)){
				valueView.setToolTip(i, bundle.getString(toGet));
			}
			else {
				valueView.setToolTip(i, null);
			}
			
		}
	}

}
