package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public interface MetaEntryViewAttributes extends MetaEntryViewAttributesAPI{

	public boolean isTranslate();

	public void setTranslate(boolean translate);

	public MetaEntryViewAttributes clone();

	/**
	 * Returns if title fonts, content fonts, linecounts and translates are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object object);

	public Font getContentFont();

	public int getLineCount();

	public Font getTitleFont();

	public void setContentFont(Font contentFont);

	public void setLineCount(int lineCount);

	public void setTitleFont(Font titleFont);

}