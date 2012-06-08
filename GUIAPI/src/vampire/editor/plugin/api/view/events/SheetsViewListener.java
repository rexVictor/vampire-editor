package vampire.editor.plugin.api.view.events;

public interface SheetsViewListener {
	
	public void sheetViewClosed(SheetsViewEvent e);
	
	public void sheetViewAdded(SheetsViewEvent e);
	
	public void currentSheetViewChanged(SheetsViewEvent e);

}
