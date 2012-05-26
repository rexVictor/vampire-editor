package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;

public class SubCategoryViewAttributes implements ISubCategoryViewAttributes{
	
	private boolean expandable;
	
	private boolean showTitle;
	
	private Font font;
	
	public SubCategoryViewAttributes(){
		
	}
	
	public SubCategoryViewAttributes(boolean expandable, boolean showTitle) {
		super();
		this.expandable = expandable;
		this.showTitle = showTitle;
	}



	@Override
	public boolean isExpandable() {
		return expandable;
	}

	@Override
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	@Override
	public boolean isShowTitle() {
		return showTitle;
	}

	@Override
	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
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
	public String toString(){
		return "expandable: "+expandable+", showTitle: "+showTitle+", font: "+font+"\n"; 
	}
	
	@Override 
	public SubCategoryViewAttributes clone(){
		SubCategoryViewAttributes clone = new SubCategoryViewAttributes();
		clone.expandable = expandable;
		clone.font	=	font;
		clone.showTitle	=	showTitle;
		return clone;
	}
	
	
	
	

}
