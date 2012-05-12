package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;

public interface ICategory extends CategoryAPI, IData<ISubCategory>{

	@Override
	public ICategory clone();

	@Override
	public ICategoryViewAttributes getViewAtts();
	
	public void setViewAtts(ICategoryViewAttributes viewAtts);

}