package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;

public interface ISheet{

	public ISheet clone();

	public IData<MetaEntryAPI, IMetaEntry> getMeta();

	public IData<CategoryAPI, ICategory> getCategories();

}