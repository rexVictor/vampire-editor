package vampire.editor.plugin.api.application.sheet.events;

public interface MeritEntryEventAPI {
	
	public MeritEntryControllerAPI getSource();
	
	public int getFormerCost();
	
	public int getNewCost();
	
	public String getFormerName();
	
	public String getNewName();

}
