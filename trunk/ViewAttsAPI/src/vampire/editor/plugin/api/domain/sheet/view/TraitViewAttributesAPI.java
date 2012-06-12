package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface TraitViewAttributesAPI extends PublicCloneable{
	
	public Orientation getOrientation();
	
	public boolean isSquares();
	
	public boolean isEditable();
	
	@Override
	public TraitViewAttributesAPI clone();
	
	public Font getFont();

}
