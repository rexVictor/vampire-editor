package vampire.editor.plugin.fullapi.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public interface ISubCategoryViewAttributes extends SubCategoryViewAttributesAPI, IDataViewAttributes{
	
	public void setShowTitle(boolean showTitle);
	
	public void setExpandable(boolean expandable);

}
