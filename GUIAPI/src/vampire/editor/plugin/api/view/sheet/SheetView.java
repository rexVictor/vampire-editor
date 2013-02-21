package vampire.editor.plugin.api.view.sheet;

import java.util.List;


public interface SheetView {
	
	public void add(CategoryView categoryView);
	
	public List<? extends CategoryView> getCategoryViews();
	
	public List<? extends MetaEntryView> getMetaViews();

}
