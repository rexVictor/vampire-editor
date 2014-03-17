package vampire.editor.plugin.api.plugin;

import java.util.List;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;

public interface TraitViewFactory {
	
	public TraitView build(TraitAPI trait, ModelToViewModelMapperAPI mapper);
	
	public List<TraitView> build(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper);

}
