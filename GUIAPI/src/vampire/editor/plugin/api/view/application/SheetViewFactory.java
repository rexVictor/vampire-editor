package vampire.editor.plugin.api.view.application;

import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;

public interface SheetViewFactory {
	
	public SheetView buildSheetView(SheetAPI sheet);

}
