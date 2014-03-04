package vampire.editor.plugin.freebiecalculator.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.freebiecalculator.application.controllers.FSheetController;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;
import vampire.editor.plugin.freebiecalculator.domain.FreebieConfig;
import vampire.editor.plugin.freebiecalculator.persistency.ConfigsImporter;


public class Activator implements vampire.editor.plugin.api.plugin.Activator, DocumentListener{
	
	private FreebieConfig config;
	
	public Activator(){
		URLClassLoader loader = (URLClassLoader) this.getClass().getClassLoader();
		try {
			Path path = Paths.get("..", "FreebieCalculator", "freebiepoints.json");
			System.out.println(path);
			ConfigsImporter importer = new ConfigsImporter();
			config = importer.loadConfig(path).get("masquerade");
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setManager(ManagerAPI manager) {
		manager.addDocumentListener(this);
	}

	@Override
	public void documentAdded(DocumentEventAPI e) {
		ControllerFactory factory = new ControllerFactory();
		FSheetController controller = factory.buildSheetController(e.getSource(), config);
		controller.addListener(new FreebieChangeListener() {
			
			@Override
			public void freebieChanged(FreebieChangeEvent event) {
				System.out.println(event.getNewFreebies());
			}
		});
		
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {
		
	}

	@Override
	public void documentRemoved(DocumentEventAPI e) {
		
	}

}
