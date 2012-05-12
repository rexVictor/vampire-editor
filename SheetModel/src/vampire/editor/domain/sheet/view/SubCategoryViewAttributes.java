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
	
	

}
