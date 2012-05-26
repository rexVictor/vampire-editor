package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.SheetAPI;

public interface ISheet extends SheetAPI{
	
	@Override
	public ISheet clone();
	
	public void setCategories(IData<ICategory> categories);
	
	public void setMeta(IData<IMetaEntry> metaEntries);
	
	

}