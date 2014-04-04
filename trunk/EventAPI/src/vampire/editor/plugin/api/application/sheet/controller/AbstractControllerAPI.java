package vampire.editor.plugin.api.application.sheet.controller;

public interface AbstractControllerAPI<M,V, L> {
	
	public M getModel();
	
	public V getView();
	
	public void addListener(L l);
	
	public void removeListener(L l);

}
