package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface MetaEntryViewAttributesAPI extends PublicCloneable{

	public int getLineCount();

	@Override
	public MetaEntryViewAttributesAPI clone();

	public Font getTitleFont();

	public Font getContentFont();

}