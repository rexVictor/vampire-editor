package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class TraitViewAttributes implements ITraitViewAttributes{
	
	private boolean editable;
	
	private Orientation orientation = Orientation.HORIZONTAL;
	
	private boolean squares = false;
	
	private Font font;

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
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("editable: ");
		sb.append(editable);
		sb.append(", squares: ");
		sb.append(squares);
		sb.append(", orientation: ");
		sb.append(orientation);
		sb.append(", font: ");
		sb.append(font.toString());
		sb.append("\n");
		return sb.toString();
	}
	
	@Override
	public TraitViewAttributes clone(){
		TraitViewAttributes clone = new TraitViewAttributes();
		clone.editable = editable;
		clone.orientation = orientation;
		clone.squares = squares;
		clone.font	=	font;
		return clone;
	}

	@Override
	public Font getFont() {
		return font;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}
	
	
	
	

}
