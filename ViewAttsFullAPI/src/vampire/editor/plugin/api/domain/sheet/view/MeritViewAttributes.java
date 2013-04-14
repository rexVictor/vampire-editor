package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributesAPI;

public interface MeritViewAttributes extends MeritViewAttributesAPI{

	/**
	 * Returns if fonts and sizes are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object obj);

	public Font getFont();

	public void setFont(Font font);

	public MeritViewAttributes clone();

}