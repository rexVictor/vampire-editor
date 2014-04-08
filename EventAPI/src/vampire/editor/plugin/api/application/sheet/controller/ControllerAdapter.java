package vampire.editor.plugin.api.application.sheet.controller;

public abstract class ControllerAdapter implements ControllerVisitor{

	@Override
	public void visit(BloodPoolControllerAPI controller) {}

	@Override
	public void visit(CategoriesControllerAPI controller) {}

	@Override
	public void visit(CategoryControllerAPI controller) {}

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
	public void visit(SubCategoryControllerAPI controller) {}

	@Override
	public void visit(TraitControllerAPI controller) {}

	@Override
	public void visit(ValueControllerAPI controller) {}

}
