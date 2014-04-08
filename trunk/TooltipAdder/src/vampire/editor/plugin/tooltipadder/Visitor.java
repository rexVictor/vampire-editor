package vampire.editor.plugin.tooltipadder;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ControllerAdapter;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class Visitor extends ControllerAdapter{
	
	private final TraitTooltipAdder tooltipAdder;
	
	private final SubCatListener subCatListener;
	
	public Visitor(TraitTooltipAdder tooltipAdder) {
		super();
		this.tooltipAdder = tooltipAdder;
		this.subCatListener = new SubCatListener(tooltipAdder);
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
		controller.addListener(subCatListener);
		controller.visitChildren(this);
	}

	@Override
	public void visit(TraitControllerAPI controller) {
		controller.addListener(tooltipAdder);
		TraitView traitView = controller.getView();
		ValueView valueView = controller.getValueController().getView();
		String name = controller.getModel().getName();
		tooltipAdder.update(traitView, valueView, name);
	}

}
