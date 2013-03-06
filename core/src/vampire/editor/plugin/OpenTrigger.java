package vampire.editor.plugin;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.Trigger;

public class OpenTrigger implements Trigger{
	
	private final GUIPlugin plugin;
	
	private final Manager manager;
	
	

	public OpenTrigger(GUIPlugin plugin, Manager manager) {
		super();
		this.plugin = plugin;
		this.manager = manager;
	}

	@Override
	public void leftClicked() {
		String file = plugin.openFileView();
		Path path = Paths.get(file);
		manager.open(path);
	}

}
