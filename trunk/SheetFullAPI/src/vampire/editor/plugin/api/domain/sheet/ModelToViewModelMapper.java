package vampire.editor.plugin.api.domain.sheet;

import java.util.Set;

import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;

public interface ModelToViewModelMapper extends ModelToViewModelMapperAPI{

	public void putView(Object model, Object view);

	public Object getViewAttributes(Object object);

	public void removeView(MeritAPI merit);

	public void removeView(ValueAPI value);

	public void removeView(TraitAPI trait);

	public Set<Object> keySet();

}