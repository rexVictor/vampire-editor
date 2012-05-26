package vampire.editor.plugin.fullapi.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public interface IMetaEntryViewAttributes extends MetaEntryViewAttributesAPI {
	
	public void setLineCount(int lineCount);
	
	public void setTitleFont(Font font);
	
	public void setContentFont(Font font);
	
	@Override
	public IMetaEntryViewAttributes clone();

}
