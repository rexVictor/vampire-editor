package vampire.editor.plugin.fullapi.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;

public interface ITraitViewAttributes extends TraitViewAttributesAPI{

	public boolean isEditable();

	public void setEditable(boolean editable);

	public Orientation getOrientation();

	public void setOrientation(Orientation orientation);

	public boolean isSquares();

	public void setSquares(boolean squares);

}