package vampire.editor.plugin.meritpopupentryadder.application;

import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.meritpopupentryadder.persistency.Loader;

public class Constructor implements Activator, DocumentListener{
	
	private Map<String, Map<Integer, List<String>>> map;

	@Override
	public void setManager(ManagerAPI manager) {
		Loader loader = new Loader(manager.getResourcesHolder().getDictionary("sheet"));
		map = loader.loadFiles();
		manager.addDocumentListener(this);
		
	}
	
	private void addToMeritView(MeritEntryView view){
		Map<Integer, List<String>> map = this.map.get("merits");
		for (int cost : map.keySet()){
			List<String> entries = map.get(cost);
			for (String s : entries){
				view.addPopupEntry(cost+"", s);
			}
		}
	}
	
	private void addToFlawView(MeritEntryView view){
		Map<Integer, List<String>> map = this.map.get("flaws");
		for (int cost : map.keySet()){
			List<String> entries = map.get(cost);
			for (String s : entries){
				view.addPopupEntry(cost+"", s);
			}
		}
	}
			
	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetControllerAPI sheetController = e.getSource();
		MeritsControllerAPI merits = sheetController.getMiscController().getMerits();
		merits.addListener(new MeritsListener() {
			
			@Override
			public void meritRemoved(MeritsEventAPI e) {}
			
			@Override
			public void meritAdded(MeritsEventAPI e) {
				addToMeritView(e.getAdded().getView());
			}
		});
		for (MeritEntryControllerAPI c : merits){
			addToMeritView(c.getView());
		}
		MeritsControllerAPI flaws = sheetController.getMiscController().getFlaws();
		flaws.addListener(new MeritsListener() {
			
			@Override
			public void meritRemoved(MeritsEventAPI e) {}
			
			@Override
			public void meritAdded(MeritsEventAPI e) {
				addToFlawView(e.getAdded().getView());
			}
		});
		for (MeritEntryControllerAPI c : flaws){
			addToFlawView(c.getView());
		}
		
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {}

	@Override
	public void documentRemoved(DocumentEventAPI e) {}

}

