package vampire.editor.plugin.api.view.events;

import vampire.editor.plugin.api.view.sheet.SheetView;

public interface SheetsViewEvent {
	
	public SheetView getCurrentSelected();
	
	public SheetView getFormerSelected();
	
	public SheetView getClosed();
	
	public SheetView getAdded();

}
