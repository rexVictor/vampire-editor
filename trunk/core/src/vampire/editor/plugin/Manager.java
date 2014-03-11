package vampire.editor.plugin;

import java.lang.reflect.InvocationTargetException;
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
import vampire.editor.plugin.api.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.exporter.DocumentExportException;
import vampire.editor.plugin.api.exporter.SheetExporter;
import vampire.editor.plugin.api.importer.DocumentImportException;
import vampire.editor.plugin.api.importer.SheetImporter;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.Facade;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
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
	
	private final Map<String, SheetExporter> sheetExporters = new HashMap<>();
	
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
				SheetImporter sheetImporter 
					= importer.getActivator().
						getConstructor(ResourcesHolderAPI.class).
							newInstance(config.getResourcesHolder());
				sheetImporter.setModelConstructors(config.getModelConstructors(), config.getViewAttConstructors());
				this.importers.add(sheetImporter);
				this.gui.addImportFileExtension(importer.getFormat());
			}
			
			Map<String, Exporter> exporters = config.getExporters();
			for (String s : exporters.keySet()){
				Exporter exporter = exporters.get(s);
				SheetExporter sheetExporter = exporter.getActivator().newInstance();
				addExporter(sheetExporter);
			}
		} catch (InstantiationException | IllegalAccessException |
					IllegalArgumentException | InvocationTargetException | 
					NoSuchMethodException | SecurityException e) {
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
		
		/* Make Save Function */
		
		Trigger saveTrigger = new Trigger() {
			
			@Override
			public void leftClicked() {
				SheetControllerAPI sheetController = controller.getCurrentController();
				if (sheetController == null){
					System.out.println("SheetController is null");
					return;
				}
				VampireDocumentAPI document = sheetController.getDocument();
				Path path = document.getPath();
				int pathNameCount = path.getNameCount();
				if (pathNameCount > 3){
					Path containingFolder = path.getName(pathNameCount - 2);
					Path inFolder = path.getName(pathNameCount - 3);
					if ("defaultsheets".equals(containingFolder.toString()) && "resources".equals(inFolder.toString())){
						System.out.println("Defaultsheets cannot be saved");
						return;
					}
				}
				String fileName = path.getFileName().toString();
				for (String s : sheetExporters.keySet()){
					if (fileName.endsWith(s)){
						SheetExporter exporter = sheetExporters.get(s);
						try {
							exporter.export(document, path);
						} catch (DocumentExportException e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		
		this.gui.addItemToMenuBar(saveTrigger, "file", "save");
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
		Trigger saveTrigger = new SaveTrigger(controller, exporter, gui,
				config.getResourcesHolder().getDictionary("general"));
		gui.addItemToMenuBar(saveTrigger, "file", "saveas", exporter.getFormat());
		sheetExporters.put(exporter.getFormat(), exporter);
	}

	@Override
	public void documentSaved(Path path, VampireDocumentAPI document) {
		if (document instanceof VampireDocument){
			((VampireDocument) document).setPath(path);
		}
	}
	
	

}
