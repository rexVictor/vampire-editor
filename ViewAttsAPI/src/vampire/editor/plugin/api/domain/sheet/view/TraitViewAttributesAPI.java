package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;

public interface TraitViewAttributesAPI {
	
	public Orientation getOrientation();
	
	public boolean isSquares();
	
	public boolean isEditable();
	
	public TraitViewAttributesAPI clone();
	
	public Font getFont();

}
