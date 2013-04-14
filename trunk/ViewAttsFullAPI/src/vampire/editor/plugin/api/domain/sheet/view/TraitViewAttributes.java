package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;

public interface TraitViewAttributes extends TraitViewAttributesAPI{

	public boolean isEditable();

	public void setEditable(boolean editable);

	public Orientation getOrientation();

	public void setOrientation(Orientation orientation);

	public TraitViewAttributes clone();

	public Font getFont();

	public void setFont(Font font);

	/**
	 * Returns if editables, squares, orientations and fonts are equal
	 * @param that
	 * @return if this equals that
	 */
	public boolean equals(Object that);

}