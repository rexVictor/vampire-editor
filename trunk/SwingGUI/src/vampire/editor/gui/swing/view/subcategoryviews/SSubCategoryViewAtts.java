package vampire.editor.gui.swing.view.subcategoryviews;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public class SSubCategoryViewAtts {
	
	private final boolean expandable;
	
	private final boolean showTitle;
	
	private final boolean shallSort;
	
	public SSubCategoryViewAtts(boolean expandable, boolean showTitle,
			boolean shallSort) {
		super();
		this.expandable = expandable;
		this.showTitle = showTitle;
		this.shallSort = shallSort;
	}


	public SSubCategoryViewAtts(SubCategoryViewAttributes viewAtts){
		expandable = viewAtts.isExpandable();
		showTitle = viewAtts.isShowTitle();
		shallSort = viewAtts.isShallSort();
	}


	public boolean isExpandable() {
		return expandable;
	}


	public boolean isShowTitle() {
		return showTitle;
	}


	public boolean isShallSort() {
		return shallSort;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (expandable ? 1231 : 1237);
		result = prime * result + (shallSort ? 1231 : 1237);
		result = prime * result + (showTitle ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SSubCategoryViewAtts other = (SSubCategoryViewAtts) obj;
		if (expandable != other.expandable)
			return false;
		if (shallSort != other.shallSort)
			return false;
		if (showTitle != other.showTitle)
			return false;
		return true;
	}
	
	

}
