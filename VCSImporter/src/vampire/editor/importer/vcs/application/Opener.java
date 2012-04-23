package vampire.editor.importer.vcs.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import vampire.editor.importer.vcs.persistency.Loader;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.GUIPlugin;
import vampire.editor.plugin.api.view.Trigger;

public class Opener implements Trigger{
	
	private final ManagerAPI manager;
	
	

	public Opener(ManagerAPI manager) {
		super();
		this.manager = manager;
	}



	@Override
	public void leftClicked() {
		GUIPlugin plugin = manager.getGUI();
		String selectedPath = plugin.openFileView();
		if (selectedPath != null) {
			Loader loader = new Loader();
			Path path = Paths.get(selectedPath);
			List<Byte> vcsDoc = null; 
			try{
				vcsDoc = loader.load(path);
			}
			catch(IOException exception){
				plugin.createErrorMessage("Error during parsing!");
				return;
			}
			Parser parser = new Parser(vcsDoc, manager.getDefaultSheet(), manager.getSheetConstructors());
			parser.parse();
		}
		
	}

}
