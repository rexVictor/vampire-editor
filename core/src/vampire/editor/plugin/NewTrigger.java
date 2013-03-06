package vampire.editor.plugin;

import java.nio.file.Path;

import vampire.editor.plugin.api.plugin.Trigger;

public class NewTrigger implements Trigger{
	
	private final Path path;
	
	private final Manager manager;
	
	

	public NewTrigger(Path path, Manager manager) {
		super();
		this.path = path;
		this.manager = manager;
	}



	@Override
	public void leftClicked() {
		manager.open(path);
	}

}
