package vampire.editor.plugin.api.view;

public interface GUIPlugin {
	
	public void addItemToMenuBar(Trigger trigger, String... menus);
	
	public String openFileView();
	
	public void createErrorMessage(String message);
	
	
	
	

}
