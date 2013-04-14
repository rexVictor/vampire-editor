package vampire.editor.plugin;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import vampire.editor.application.GeneralController;

import vampire.editor.domain.config.Config;
import vampire.editor.domain.config.Exporter;
import vampire.editor.domain.config.Importer;
import vampire.editor.domain.config.Plugin;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.importer.DocumentImportException;
import vampire.editor.plugin.api.importer.SheetImporter;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.Facade;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.SheetExporter;
import vampire.editor.plugin.api.plugin.Trigger;

public class Manager implements ManagerAPI{
	
	private final Config config;
	
	private final Map<String, Facade> facades = new HashMap<>();
	
	private final GeneralController controller;
	
	private final List<Plugin> plugins = new LinkedList<>();
	
	private final List<SheetImporter> importers = new LinkedList<>();
	
	private /*final*/ GUIPlugin gui;
	
	private SheetImporter defaultImporter;
	
	private final List<DocumentListener> documentListeners = new LinkedList<>();
	
	public Manager(Config config, GeneralController controller){
		this.config = config;
		this.controller = controller;
		initialize();
		gui.setVisible();
	}
	
	private void initialize(){
		try {
			Activator gui = config.getGUI().newInstance();
			gui.setManager(this);
			this.gui.addItemToMenuBar(new OpenTrigger(this.gui, this), "file", "open");
			Map<String, Importer> importers = config.getImporters();
			for (String s : importers.keySet()){
				Importer importer = importers.get(s);
				Activator importerActivator = importer.getActivator().newInstance();
				importerActivator.setManager(this);
				this.gui.addImportFileExtension(importer.getFormat());
			}
			
			Map<String, Exporter> exporters = config.getExporters();
			for (String s : exporters.keySet()){
				Exporter exporter = exporters.get(s);
				Activator exporterActivator = exporter.getActivator().newInstance();
				exporterActivator.setManager(this);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);			
		}
		Map<String, Plugin> plugins = config.getPlugins();
		List<String> keys = new ArrayList<>(plugins.keySet());
		while (!keys.isEmpty()) {
			for (int i = 0; i < keys.size(); i++){
				String key = keys.get(i);
				Plugin plugin = plugins.get(key);
				List<Plugin> dependencies = plugin.getDependencies();
				if (dependencies.isEmpty() || this.plugins.containsAll(dependencies)) {
					try {
						Activator activator = plugin.getActivator().newInstance();
						activator.setManager(this);
						plugins.put(key, plugin);
						keys.remove(i);
						i--;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		
		Map<String, Path> defaultSheets = getResourcesHolder().getDefaultSheets();
		for (String s : defaultSheets.keySet()){
			this.gui.addItemToMenuBar(new NewTrigger(defaultSheets.get(s), this), "file", "new", s);
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
	
	
	
	public GeneralController getController(){
		return controller;
	}

	@Override
	public GUIPlugin getGUI() {
		return gui;
	}

	
	
	
	
	

	@Override
	public ResourcesHolderAPI getResourcesHolder() {
		return config.getResourcesHolder();
	}

	@Override
	public void setGUIPlugin(GUIPlugin gui) {
		if (this.gui == null)
			this.gui = gui;
	}

	@Override
	public GeneralControllerAPI getGeneralController() {
		return controller;		
	}

	@Override
	public void closed(SheetControllerAPI controller) {
		
	}

	@Override
	public void selectedSheetChanged(SheetControllerAPI controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefaultImporter(SheetImporter importer) {
		this.defaultImporter = importer;
	}
	
	public SheetImporter getDefaultImporter(){
		return defaultImporter;
	}

	@Override
	public void addDocumentListener(DocumentListener listener) {
		documentListeners.add(listener);
	}

	@Override
	public void removeDocumentListener(DocumentListener listener) {
		documentListeners.remove(listener);
	}
	
	public void documentOpened(SheetControllerAPI controller){
		final DocumentEvent e = new DocumentEvent(controller);
		for (DocumentListener l : documentListeners){
			final DocumentListener listener = l;
			new Thread(){
				@Override
				public void run(){
					listener.documentAdded(e);
				}
			}.start();
		}
	}
	
	public void addImporter(SheetImporter importer){
		importers.add(importer);
	}
	
	public void open(Path path){
		for (SheetImporter importer : importers){
			if (importer.canHandle(path)){
				VampireDocumentAPI document;
				try {
					document = importer.loadDocument(path);
					controller.open(document);
				} catch (DocumentImportException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void addExporter(SheetExporter exporter) {
		Trigger saveTrigger = new SaveTrigger(controller, exporter, gui);
		gui.addItemToMenuBar(saveTrigger, "file", "save", exporter.getFormat());
	}
	
	

}
