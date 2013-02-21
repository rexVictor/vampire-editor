package vampire.editor.gui.swing.view.valueviews;

import vampire.editor.domain.sheet.view.ValueViewAttributes;

class StaticSpacedValueView extends SpacedValueView{

	/**
	 * The ValueView for static, showSpace and no Squares
	 * @param viewAtts
	 */
	StaticSpacedValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
	}
	
	@Override
	public void addCircle(){}
	
	@Override
	public void removeCircle(){}


}
