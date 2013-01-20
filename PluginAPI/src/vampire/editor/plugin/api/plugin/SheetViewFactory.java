package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;

public interface SheetViewFactory {
	
	public SheetView buildSheetView(VampireDocumentAPI document);

}
