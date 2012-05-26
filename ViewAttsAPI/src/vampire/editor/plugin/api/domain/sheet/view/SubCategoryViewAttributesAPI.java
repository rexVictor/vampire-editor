package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface SubCategoryViewAttributesAPI extends DataViewAttributesAPI{
	
	public boolean isShowTitle();
	
	public boolean isExpandable();
	
	public Font getFont();

}
