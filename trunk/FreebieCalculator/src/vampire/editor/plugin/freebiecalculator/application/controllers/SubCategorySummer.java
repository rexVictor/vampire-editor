package vampire.editor.plugin.freebiecalculator.application.controllers;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;

public class SubCategorySummer implements ValueListener{
	
	protected int defaultSum;
	
	protected int sum = 0;
	
	public SubCategorySummer(int defaultSum, SubCategoryControllerAPI subCatController){
		for (TraitControllerAPI traitController : subCatController){
			ValueControllerAPI controller = traitController.getValueController();
			sum += controller.getValue().getValue();
			controller.addListener(this);
		}
	}
	
	public void setDefaultSum(int defaultSum){
		this.defaultSum = defaultSum;
		update();
	}
	
	private void update(){
		
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		sum-=event.getFormerValue();
		sum+=event.getNewValue();
		update();
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}
	
	public int getDifference(){
		return sum - defaultSum;
	}

}
