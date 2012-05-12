package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class TraitViewAttributes implements ITraitViewAttributes{
	
	private boolean editable;
	
	private Orientation orientation = Orientation.HORIZONTAL;
	
	private boolean squares = false;

	public TraitViewAttributes() {
		super();
	}

	@Override
	public boolean isEditable() {
		return editable;
	}

	@Override
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public boolean isSquares() {
		return squares;
	}

	@Override
	public void setSquares(boolean squares) {
		this.squares = squares;
	}
	
	

}
