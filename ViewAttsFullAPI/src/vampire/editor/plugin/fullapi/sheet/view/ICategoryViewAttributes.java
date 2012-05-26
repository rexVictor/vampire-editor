package vampire.editor.plugin.fullapi.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public interface ICategoryViewAttributes extends CategoryViewAttributesAPI, IDataViewAttributes{
	
	public void setShowLine(boolean showLine);
	
	public void setTitle(String title);
	
	public void setImage(String image);
	
	public void setFont(Font font);
	
	public ICategoryViewAttributes clone();

}
