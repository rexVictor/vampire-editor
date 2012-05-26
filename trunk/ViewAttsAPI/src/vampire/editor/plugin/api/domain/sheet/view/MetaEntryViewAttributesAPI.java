package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface MetaEntryViewAttributesAPI {

	public int getLineCount();

	public MetaEntryViewAttributesAPI clone();

	public Font getTitleFont();

	public Font getContentFont();

}