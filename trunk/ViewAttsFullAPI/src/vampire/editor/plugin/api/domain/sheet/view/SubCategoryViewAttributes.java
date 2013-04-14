package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public interface SubCategoryViewAttributes extends SubCategoryViewAttributesAPI{

	public boolean isExpandable();

	public void setExpandable(boolean expandable);

	public boolean isShowTitle();

	public void setShowTitle(boolean showTitle);

	public Font getFont();

	public void setFont(Font font);

	public SubCategoryViewAttributes clone();

	/**
	 * Returns true if and only if both are expandable and both show the Title and both have the same font.
	 */
	public boolean equals(Object object);

}