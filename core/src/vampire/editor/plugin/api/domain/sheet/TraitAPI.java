package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;

public interface TraitAPI extends Nameable{

	public String getName();

	public void setName(String name);

	public Value getValue();

	public TraitViewAttributes getTraitViewAttributes();

}