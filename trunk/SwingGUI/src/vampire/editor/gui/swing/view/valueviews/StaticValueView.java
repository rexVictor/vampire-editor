package vampire.editor.gui.swing.view.valueviews;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;

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
	protected void addCircle0(){
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.addGroupedColumn(layout.getColumnCount());
		JLabel circle = new JLabel();
		circle.addMouseListener(new ValueClickListener(this, circles.size()));
		circles.add(circle);
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		
		getPanel().add(circle, constraints);
		
	}
	
	@Override
	public void addCircle(){}
	
	@Override
	public void removeCircle(){}

}
