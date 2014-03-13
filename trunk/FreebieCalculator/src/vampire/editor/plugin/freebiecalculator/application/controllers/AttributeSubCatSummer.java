package vampire.editor.plugin.freebiecalculator.application.controllers;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;

public class AttributeSubCatSummer extends SubCategorySummer{

	public AttributeSubCatSummer(int defaultSum,
			SubCategoryControllerAPI subCatController) {
		super(defaultSum, subCatController);
	}
	
	@Override
	public void valueChanged(ValueEventAPI event){
		if (event.getNewValue() == 0){
			defaultSum--;
		}
		else if (event.getFormerValue() == 0){
			defaultSum++;
		}
		super.valueChanged(event);
	}

}
