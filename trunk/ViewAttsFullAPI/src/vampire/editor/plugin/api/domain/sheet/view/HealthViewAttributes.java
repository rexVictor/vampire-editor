package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributesAPI;

public interface HealthViewAttributes extends HealthViewAttributesAPI{

	public Font getFont();

	public void setFont(Font font);

	/**
	 * Returns if the fonts are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object that);

	public HealthViewAttributes clone();

}