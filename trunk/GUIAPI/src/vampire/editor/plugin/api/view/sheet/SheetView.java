package vampire.editor.plugin.api.view.sheet;

import java.util.List;


public interface SheetView {
	
	public void add(CategoryView categoryView);
	
	public List<? extends CategoryView> getCategoryViews();
	
	public MetaView getMetaView();
	
	public MiscView getMiscView();

}
