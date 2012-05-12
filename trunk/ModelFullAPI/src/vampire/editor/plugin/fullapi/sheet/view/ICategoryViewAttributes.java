package vampire.editor.plugin.fullapi.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public interface ICategoryViewAttributes extends CategoryViewAttributesAPI{
	
	public void setShowLine(boolean showLine);
	
	public void setTitle(String title);
	
	public void setImage(String image);

}
