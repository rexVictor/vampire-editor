package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class TraitViewAttributesTestImplementation implements ITraitViewAttributes{

	@Override
	public boolean isEditable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setEditable(boolean editable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Orientation getOrientation() {
		// TODO Auto-generated method stub
		return Orientation.HORIZONTAL;
	}

	@Override
	public void setOrientation(Orientation orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSquares() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSquares(boolean squares) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TraitViewAttributesTestImplementation clone(){
		return null;
	}

}
