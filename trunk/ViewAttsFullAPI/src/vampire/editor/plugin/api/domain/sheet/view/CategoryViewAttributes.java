package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public interface CategoryViewAttributes extends CategoryViewAttributesAPI{

	public boolean isShowLine();

	public void setShowLine(boolean showLine);

	public String getImage();

	public void setImage(String image);

	public String getTitle();

	public void setTitle(String title);

	public Font getFont();

	public void setFont(Font font);

	public CategoryViewAttributes clone();

	/**
	 * Returns if the fonts, images, the showLines and the titles are equal.
	 * @param that
	 * @return if this and that are equal
	 */
	public boolean equals(Object that);

}