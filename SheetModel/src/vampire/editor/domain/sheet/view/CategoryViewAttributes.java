package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;

public class CategoryViewAttributes implements ICategoryViewAttributes{
	
	private boolean showLine;
	
	private String image;
	
	private String title;

	public boolean isShowLine() {
		return showLine;
	}

	public void setShowLine(boolean showLine) {
		this.showLine = showLine;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
