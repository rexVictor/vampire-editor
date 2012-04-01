package plugin.api.domain.sheet;

import domain.sheet.Value;
import plugin.api.domain.sheet.view.TraitViewAttributes;

public interface TraitAPI extends Nameable{

	public String getName();

	public void setName(String name);

	public Value getValue();

	public TraitViewAttributes getTraitViewAttributes();

}