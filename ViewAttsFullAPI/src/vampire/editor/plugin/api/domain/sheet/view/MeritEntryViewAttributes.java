package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;

public interface MeritEntryViewAttributes extends MeritEntryViewAttibutesAPI{

	public void setFont(Font font);

	public Font getFont();

	/**
	 * Returns if fonts and sizes are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object that);

	public MeritEntryViewAttributes clone();

}