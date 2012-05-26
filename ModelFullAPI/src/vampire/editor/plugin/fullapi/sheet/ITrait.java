package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public interface ITrait extends TraitAPI{

	@Override
	public String getName();

	@Override
	public void setName(String name);

	@Override
	public IValue getValue();

	@Override
	public ITraitViewAttributes getViewAtts();
	
	public void setViewAtts(ITraitViewAttributes traitViewAttributes);

	public void addSpecialty(ISpecialty specialty);

	public void removeSpecialty(ISpecialty specialty);

	@Override
	public ITrait clone();
	
	public void setValue(IValue value);

}