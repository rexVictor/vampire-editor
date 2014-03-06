package vampire.editor.plugin.metapopupentryadder;

import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public class Constructor implements Activator, DocumentListener{
	
	private final Map<String, List<String>> map;
	
	

	public Constructor() {
		super();
		Loader loader = new Loader();
		this.map = loader.loadFiles();
	}

	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetControllerAPI sheetController = e.getSource();
		MetaControllerAPI metas = sheetController.getMetaController();
		for (String key : map.keySet()){
			MetaEntryControllerAPI metaEntryController = metas.getMetaEntryController(key);
			MetaEntryView view = metaEntryController.getMetaEntryView();
			List<String> entries = map.get(key);
			for (String s : entries){
				view.addPopupEntry(s);
			}
		}
	}
	
	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {}

	@Override
	public void documentRemoved(DocumentEventAPI e) {}

	@Override
	public void setManager(ManagerAPI manager) {
		manager.addDocumentListener(this);
	}

}
