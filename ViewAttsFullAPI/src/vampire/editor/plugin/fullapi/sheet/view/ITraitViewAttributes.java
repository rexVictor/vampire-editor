package vampire.editor.plugin.fullapi.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;

public interface ITraitViewAttributes extends TraitViewAttributesAPI{

	@Override
	public boolean isEditable();

	public void setEditable(boolean editable);

	@Override
	public Orientation getOrientation();

	public void setOrientation(Orientation orientation);

	@Override
	public boolean isSquares();

	public void setSquares(boolean squares);
	
	@Override
	public ITraitViewAttributes clone();
	
	public void setFont(Font font);

}