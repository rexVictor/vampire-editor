package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;

public class TraitViewAttributes implements TraitViewAttributesAPI, FontSettable{
	
	private boolean editable;
	
	private Orientation orientation = Orientation.HORIZONTAL;
	
	private boolean squares = false;
	
	private Font font;

	public TraitViewAttributes() {
		super();
	}
	
	public TraitViewAttributes(boolean editable, Orientation orientation,
			boolean squares, Font font) {
		super();
		this.editable = editable;
		this.orientation = orientation;
		this.squares = squares;
		this.font = font;
	}



	@Override
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public boolean isSquares() {
		return squares;
	}

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
		sb.append(font);
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
	
	@Override
	public boolean equals(Object object){
		if (object == null) return false;
		if (object == this) return true;
		if (object instanceof TraitViewAttributes){
			TraitViewAttributes other = (TraitViewAttributes) object;
			return editable == other.editable && squares == other.squares && orientation.equals(other.orientation)
					&& font.equals(other.font);
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 11 * System.identityHashCode(editable) + 13 * System.identityHashCode(squares)
				+ 17 * orientation.hashCode() + 19 * font.hashCode();
	}
	
	
	

}
