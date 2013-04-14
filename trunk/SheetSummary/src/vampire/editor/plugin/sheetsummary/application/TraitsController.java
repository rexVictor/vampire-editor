package vampire.editor.plugin.sheetsummary.application;

import java.util.Iterator;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.sheetsummary.view.TraitsView;

public class TraitsController implements ValueListener, SubCategoryListener{
	
	private final TraitsView view;
	
	private final int index;
	
	private int value = 0; 
	
	public TraitsController(TraitsView view, SubCategoryControllerAPI subCatController, int index) {
		super();
		this.view = view;
		this.index = index;
		initialize(subCatController);
	}
	
	private void initialize(SubCategoryControllerAPI subCatController){
		subCatController.addListener(this);
		for (Iterator<? extends TraitControllerAPI> i = subCatController.iterator(); i.hasNext();){
			TraitControllerAPI traitController = i.next();
			ValueControllerAPI valueController = traitController.getValueController();
			value += valueController.getValue().getValue();
		}
		view.setValue(index, value);
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		this.value = this.value - event.getFormerValue() + event.getNewValue();
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}

	@Override
	public void traitAdded(SubCategoryEventAPI event) {
		ValueControllerAPI controller = event.getAdded().getValueController();
		controller.addListener(this);
		int value = controller.getValue().getValue();
		this.value += value;
		view.setValue(index, this.value);
	}

	@Override
	public void traitRemoved(SubCategoryEventAPI event) {
		ValueControllerAPI controller = event.getRemoved().getValueController();
		controller.removeListener(this);
		int value = controller.getValue().getValue();
		this.value -= value;
		view.setValue(index, this.value);
	}

}
