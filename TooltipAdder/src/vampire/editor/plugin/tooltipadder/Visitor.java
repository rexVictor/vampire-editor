package vampire.editor.plugin.tooltipadder;

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.controller.HealthControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MiscControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class Visitor implements ControllerVisitor{
	
	private final TraitTooltipAdder tooltipAdder;
	
	private final SubCatListener subCatListener;
	
	public Visitor(TraitTooltipAdder tooltipAdder) {
		super();
		this.tooltipAdder = tooltipAdder;
		this.subCatListener = new SubCatListener(tooltipAdder);
	}

	@Override
	public void visit(BloodPoolControllerAPI controller) {}

	@Override
	public void visit(CategoriesControllerAPI controller) {
		controller.visitChildren(this);
	}

	@Override
	public void visit(CategoryControllerAPI controller) {
		controller.visitChildren(this);
	}

	@Override
	public void visit(HealthControllerAPI controller) {}

	@Override
	public void visit(HealthEntryControllerAPI controller) {}

	@Override
	public void visit(MeritsControllerAPI controller) {}

	@Override
	public void visit(MeritEntryControllerAPI controller) {}

	@Override
	public void visit(MetaControllerAPI controller) {}

	@Override
	public void visit(MetaEntryControllerAPI controller) {}

	@Override
	public void visit(MiscControllerAPI controller) {}

	@Override
	public void visit(SheetControllerAPI controller) {}

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

	@Override
	public void visit(ValueControllerAPI controller) {}

}
