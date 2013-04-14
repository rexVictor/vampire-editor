package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface BloodPoolViewAttributes extends BloodPoolViewAttributesAPI{

	public Font getFont();

	public void setFont(Font font);

	public int getSize();

	public void setSize(int size);

	/**
	 * Returns if this and that have the same size and their fonts are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object that);

	public BloodPoolViewAttributes clone();

}