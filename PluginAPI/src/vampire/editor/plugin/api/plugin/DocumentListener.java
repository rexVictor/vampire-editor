package vampire.editor.plugin.api.plugin;

public interface DocumentListener {
	
	public void documentAdded(DocumentEventAPI e);
	
	public void selectedDocumentChanged(DocumentEventAPI e);
	
	public void documentRemoved(DocumentEventAPI e);
}
