package vampire.editor.plugin;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.application.GeneralController;
import vampire.editor.domain.Dictionary;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.Facade;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.GUIPlugin;
import vampire.editor.plugin.api.view.events.SheetEventAPI;
import vampire.editor.plugin.api.view.events.SheetListener;
import vampire.editor.plugin.api.view.sheet.ViewConstructors;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;

public class Manager implements SheetListener, ManagerAPI{
	
	private final Map<String, Facade> facades = new HashMap<>();
	
	private final Map<String, Dictionary> dictionaries = new HashMap<>();
	
	private final Map<String, Font> fonts = new HashMap<>();
	
	private final GUIPlugin guiplugin = null;
	
	private final SheetLoaders sheetLoaders = new SheetLoaders();
	
	private GeneralController controller;
	
	public Manager(){
		//TODO das ist nur ein TestFall!
		Path path = Paths.get("resources", "CAS_ANTN.TTF");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, path.toFile());
			fonts.put("cas_antn", font);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void offer(Facade facade){
		facades.put(facade.getClass().getName(), facade);
		
	}
	
	public Facade demand(String name){
		return facades.get(name);
	}
	
	
	
	public void registerPlugin(Activator activator){
		activator.setManager(this);
	}
	
	public void registerSheetLoader(SheetLoader loader){
		
	}
	
	public void addDictionary(Dictionary dictionary){
		
	}
	
	public void setGeneralController(GeneralController controller){
		this.controller = controller;
	}
	
	public GeneralController getController(){
		return controller;
	}

	@Override
	public void sheetConstructed(SheetEventAPI event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public SheetConstructors getSheetConstructors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GUIPlugin getGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SheetAPI getDefaultSheet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ViewConstructors getViewConstructors(){
		return null;
	}
	
	@Override
	public Font getFont(String key){
		Font toReturn = fonts.get(key);
		if (toReturn == null)
			toReturn = new Font(key, 0, 10);
		return toReturn;
	}
	
	

}
