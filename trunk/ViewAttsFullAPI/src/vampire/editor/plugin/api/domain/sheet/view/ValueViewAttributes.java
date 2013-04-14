package vampire.editor.plugin.api.domain.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public interface ValueViewAttributes extends ValueViewAttributesAPI{

	public boolean isShowSpace();

	public void setShowSpace(boolean showSpace);

	public boolean isDynamic();

	public void setDynamic(boolean dynamic);

	public int getCircles();

	public void setCircles(int circles);

	public boolean isTempSquared();

	public void setTempSquared(boolean tempSquared);

	public int getSize();

	public void setSize(int size);

	public ValueViewAttributes clone();

	/**
	 * Returns if dynamics, showSpaces, circles, sizes and tempSquareds are equal.
	 * @param that
	 * @return if this equals that
	 */
	public boolean equals(Object that);

}