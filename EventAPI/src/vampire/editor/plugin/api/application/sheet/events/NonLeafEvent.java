package vampire.editor.plugin.api.application.sheet.events;

public interface NonLeafEvent<C, SC> {
	
	public C getSource();
	
	public SC getReason();
	
	public int getIndex();

}
