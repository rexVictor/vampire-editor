package vampire.editor.plugin.api.plugin;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.view.GUIPlugin;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;

public interface ManagerAPI {
	
	public SheetConstructors getSheetConstructors();
	
	public GUIPlugin getGUI();
	
	public SheetAPI getDefaultSheet();
	
	public Font getFont(String key);

}
