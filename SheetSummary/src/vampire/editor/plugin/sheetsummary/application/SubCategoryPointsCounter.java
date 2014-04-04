package vampire.editor.plugin.sheetsummary.application;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;

public class SubCategoryPointsCounter implements ValueListener, SubCategoryListener{
	
	private int sum = 0;
	
	private SubCategoryPointsController controller;
	
	public SubCategoryPointsCounter(SubCategoryControllerAPI subCatController){
		subCatController.addListener(this);
		for (TraitControllerAPI traitController : subCatController){
			ValueControllerAPI valueController = traitController.getValueController();
			ValueAPI value = valueController.getModel();
			sum+=value.getValue();
			valueController.addListener(this);
			sumChanged(sum);
		}
	}
	
	public void setListener(SubCategoryPointsController controller){
		this.controller = controller;
		sumChanged(sum);
	}
	
	private void sumChanged(int sum){
		if (controller != null){
			controller.sumChanged(sum);
		}
	}
	
	public int getSum(){
		return sum;
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		sum-=event.getFormerValue();
		sum+=event.getNewValue();
		sumChanged(sum);
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}

	@Override
	public void added(SubCategoryEventAPI event) {
		event.getReason().getValueController().addListener(this);
	}

	@Override
	public void removed(SubCategoryEventAPI event) {
		event.getReason().getValueController().removeListener(this);
	}

}
