package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.List;

import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SubCategoryController {
	
	private final SubCategory subCategory;
	
	private final SubCategoryView view;
	
	private List<TraitController> traitControllers = new ArrayList<>();

	public SubCategoryController(SubCategory subCategory, SubCategoryView view) {
		super();
		this.subCategory = subCategory;
		this.view = view;
	}
	
	public void addTrait(TraitController traitController){
		subCategory.add(traitController.getTrait());
		view.addTraitView(traitController.getTraitView());
		traitControllers.add(traitController);
	}
	
	public void removeTrait(TraitController traitController){
		subCategory.remove(traitController.getTrait());
	    //TODO view.remove(traitController.getTraitView());
		traitControllers.remove(traitController);
	}
	
	
	
	

}
