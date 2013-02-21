package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public class SubCategoryViewAttributes implements SubCategoryViewAttributesAPI, FontSettable{
	
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

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	@Override
	public boolean isShowTitle() {
		return showTitle;
	}

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
	
	/**
	 * Returns true if and only if both are expandable and both show the Title and both have the same font.
	 */
	@Override
	public boolean equals(Object object){
		if (object == null) return false;
		if (object == this) return true;
		if (object instanceof SubCategoryViewAttributes){
			SubCategoryViewAttributes other = (SubCategoryViewAttributes) object;
			return other.expandable == expandable && other.showTitle == showTitle && font.equals(other.font); 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 11 * System.identityHashCode(expandable) + 13 * System.identityHashCode(showTitle)
				+ 17 * System.identityHashCode(font);
	}
	
	
	
	

}
