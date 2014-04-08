package vampire.editor.plugin.dynamicsubcategorymanager.application;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ControllerAdapter;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public class Visitor extends ControllerAdapter{
	
	private final ModelToViewModelMapperAPI mapper;
	
	public Visitor(ModelToViewModelMapperAPI mapper) {
		super();
		this.mapper = mapper;
	}

	@Override
	public void visit(CategoriesControllerAPI controller) {
		controller.visitChildren(this);
	}

	@Override
	public void visit(CategoryControllerAPI controller) {
		controller.visitChildren(this);
	}

	@Override
	public void visit(SubCategoryControllerAPI controller) {
		SubCategoryViewAttributesAPI viewAtts = (SubCategoryViewAttributesAPI) mapper.getViewAttributes(controller.getModel());
		if (viewAtts.isExpandable()){
			TraitAdder adder = new TraitAdder(mapper, controller);
			controller.addListener(adder);
			for (TraitControllerAPI traitControllerAPI : controller){
				traitControllerAPI.addListener(adder);
			}
		}
	}

}
