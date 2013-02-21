package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public class CategoryViewAttributes implements CategoryViewAttributesAPI, FontSettable{
	
	private boolean showLine;
	
	private String image;
	
	private String title = "";
	
	private Font font;

	@Override
	public boolean isShowLine() {
		return showLine;
	}

	public void setShowLine(boolean showLine) {
		this.showLine = showLine;
	}

	@Override
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}
	
	@Override
	public CategoryViewAttributes clone(){
		CategoryViewAttributes clone = new CategoryViewAttributes();
		clone.font		=	font;
		clone.image		=	image;
		clone.showLine	=	showLine;
		clone.title		=	title;
		return clone;
	}
	
	@Override
	public boolean equals(Object object){
		if (object == null) return false;
		if (object == this) return true;
		if (object instanceof CategoryViewAttributes){
			CategoryViewAttributes other = (CategoryViewAttributes) object;
			return other.showLine == showLine && image.equals(other.image) && title.equals(other.title)
					&& font.equals(other.font); 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 11 * System.identityHashCode(showLine) + 13 * image.hashCode() + 17 * System.identityHashCode(title)
				+19 * font.hashCode();
	}

}
