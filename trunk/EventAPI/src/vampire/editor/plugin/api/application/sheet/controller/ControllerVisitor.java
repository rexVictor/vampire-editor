package vampire.editor.plugin.api.application.sheet.controller;

public interface ControllerVisitor {
	
	public void visit(BloodPoolControllerAPI controller);
	
	public void visit(CategoriesControllerAPI controller);
	
	public void visit(CategoryControllerAPI controller);
	
	public void visit(HealthControllerAPI controller);
	
	public void visit(HealthEntryControllerAPI controller);
	
	public void visit(MeritsControllerAPI controller);
	
	public void visit(MeritEntryControllerAPI controller);
	
	public void visit(MetaControllerAPI controller);
	
	public void visit(MetaEntryControllerAPI controller);
	
	public void visit(MiscControllerAPI controller);
	
	public void visit(SheetControllerAPI controller);
	
	public void visit(SubCategoryControllerAPI controller);
	
	public void visit(TraitControllerAPI controller);
	
	public void visit(ValueControllerAPI controller);

}
