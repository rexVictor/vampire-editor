package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface SubCategoryViewAttributesAPI extends DataViewAttributesAPI, PublicCloneable{
	
	public boolean isShowTitle();
	
	public boolean isExpandable();
	
	public Font getFont();
	
	@Override
	public SubCategoryViewAttributesAPI clone();

}
