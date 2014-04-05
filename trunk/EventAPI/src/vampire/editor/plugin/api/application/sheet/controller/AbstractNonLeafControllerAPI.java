package vampire.editor.plugin.api.application.sheet.controller;

public interface AbstractNonLeafControllerAPI<M, V, L, SC>	extends AbstractControllerAPI<M, V, L>, Iterable<SC>{
	
	public void add(SC subController);
	
	public void remove(SC subController);
	
	public void insert(int index, SC subController);
	
	public int size();
	
	public SC get(int i);
	
	public int indexOf(SC subController);
	
	public void visitChildren(ControllerVisitor visitor);
	
}
