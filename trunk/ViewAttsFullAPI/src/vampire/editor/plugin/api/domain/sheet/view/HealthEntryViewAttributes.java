package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;

public interface HealthEntryViewAttributes extends HealthEntryViewAttributesAPI{

	public Font getFont();

	public void setFont(Font font);

	public int getSize();

	public void setSize(int size);

	/**
	 * Returns if fonts and sizes are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object that);

	public HealthEntryViewAttributes clone();

}