package application.sheet.controller;

import plugin.api.view.sheet.SubCategoryView;
import domain.sheet.SubCategory;

public class SubCategoryController {
	
	private final SubCategory subCategory;
	
	private final SubCategoryView view;

	public SubCategoryController(SubCategory subCategory, SubCategoryView view) {
		super();
		this.subCategory = subCategory;
		this.view = view;
	}
	
	public void addTrait(TraitController traitController){
		subCategory.add(traitController.getTrait());
		view.addTraitView(traitController.getTraitView());
	}
	
	
	
	

}
