package vampire.editor.gui.swing.view.valueviews;

import java.awt.Color;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;

import vampire.editor.domain.sheet.view.ValueViewAttributes;

/**
 * The ValueView for showSpace, dynamic and no Squares
 * @author rex_victor
 *
 */
class SpacedValueView extends DefaultValueView{
	
	private final JLabel space = new JLabel(" ");
	
	/**
	 * The ValueView for showSpace, dynamic and no Squares
	 */
	SpacedValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		getPanel().setBackground(Color.WHITE);
	}
	
	@Override
	protected void addCircle0(){
		if (circles.size() == 5){
			layout.appendColumn(ColumnSpec.decode("pref"));
			CellConstraints constraints = new CellConstraints();
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.gridX		=	layout.getColumnCount();
			constraints.gridY		=	1;
			
			getPanel().add(space, constraints);
		}
		super.addCircle0();
	}

	@Override
	public void removeCircle0(){
		if (circles.size()==6){
			CellConstraints constraints = layout.getConstraints(space);
			getPanel().remove(space);
			layout.removeColumn(constraints.gridX);
		}
		super.removeCircle0();
	}
	
}
