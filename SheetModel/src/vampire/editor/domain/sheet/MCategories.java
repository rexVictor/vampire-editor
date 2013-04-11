package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.Categories;
import vampire.editor.plugin.api.domain.sheet.Category;

class MCategories extends Data<Category> implements Categories{
	
	public MCategories clone(){
		return (MCategories) super.clone();
	}

}
