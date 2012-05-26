package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface CategoryViewAttributesAPI extends DataViewAttributesAPI{
	
	public boolean isShowLine();
	
	public String getTitle();
	
	public String getImage();
	
	public Font getFont();

}
