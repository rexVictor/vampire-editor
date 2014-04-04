package vampire.editor.plugin.api.domain.sheet;

public interface AbstractNonLeafModel<SM> {
	
	public void add(SM subModel);
	
	public void remove(SM subModel);
	
	public void insert(int i, SM subModel);

}
