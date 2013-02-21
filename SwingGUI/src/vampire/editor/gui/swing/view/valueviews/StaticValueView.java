package vampire.editor.gui.swing.view.valueviews;

import vampire.editor.domain.sheet.view.ValueViewAttributes;

/**
 * The ValueView for noSpace, static and no Squares
 * @author rex_victor
 *
 */
class StaticValueView extends DefaultValueView{
	
	/**
	 * The ValueView for noSpace, static and no Squares
	 */
	StaticValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
	}
	
	@Override
	public void addCircle(){}
	
	@Override
	public void removeCircle(){}

}
