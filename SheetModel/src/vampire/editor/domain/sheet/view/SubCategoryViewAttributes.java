package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;

public class SubCategoryViewAttributes implements ISubCategoryViewAttributes{
	
	private boolean expandable;
	
	private boolean showTitle;
	
	public SubCategoryViewAttributes(){
		
	}
	
	public SubCategoryViewAttributes(boolean expandable, boolean showTitle) {
		super();
		this.expandable = expandable;
		this.showTitle = showTitle;
	}



	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}
	
	

}
