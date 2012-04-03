package vampire.editor.plugin.api.application.sheet.events;

public interface CategoryListener {
	
	public void subCategoryAdded(CategoryEventAPI event);
	
	public void subCategoryRemoved(CategoryEventAPI event);

}
