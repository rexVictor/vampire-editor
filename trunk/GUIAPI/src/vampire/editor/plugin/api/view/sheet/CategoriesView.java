package vampire.editor.plugin.api.view.sheet;

import java.util.List;

public interface CategoriesView extends Addable<CategoryView>{
	
	public List<? extends CategoryView> getCategoryViews();

}
