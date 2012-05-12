package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;

public interface ITrait extends TraitAPI{

	public String getName();

	public void setName(String name);

	public IValue getValue();

	public TraitViewAttributesAPI getTraitViewAttributes();

	public void addSpecialty(ISpecialty specialty);

	public void removeSpecialty(ISpecialty specialty);

	public ITrait clone();

}