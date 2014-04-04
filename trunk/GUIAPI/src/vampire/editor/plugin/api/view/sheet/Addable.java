package vampire.editor.plugin.api.view.sheet;

public interface Addable<S> {
	
	public void add(S s);
	
	public void add0(S s);
	
	public void remove(S s);
	
	public void insert(int index, S s);
	
}
