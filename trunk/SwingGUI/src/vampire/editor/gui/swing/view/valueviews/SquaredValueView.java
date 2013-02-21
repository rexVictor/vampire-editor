package vampire.editor.gui.swing.view.valueviews;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.domain.sheet.view.ValueViewAttributes;

class SquaredValueView extends AbstractValueView{
	
	protected FormLayout layout = new FormLayout();
	
	protected List<JLabel> squares = new ArrayList<>();

	SquaredValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		getPanel().setLayout(layout);
		layout.appendRow(RowSpec.decode("pref"));
		layout.appendRow(RowSpec.decode("pref"));
		for (int i = 0; i < viewAtts.getCircles(); i++){
			addCircle0();
		}
	}
	
	@Override
	protected void redrawTempValue(){
		int tempValue = this.tempValue;
		for (int i = 0; i < tempValue; i++) {
			squares.get(i).setText(SQUARE_CROSSED);
		}
		for (int i = tempValue; i < circles.size(); i++){
			squares.get(i).setText(SQUARE_EMPTY);
		}
	}

	@Override
	public void addCircle() {}

	@Override
	protected void addCircle0() {
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.addGroupedColumn(layout.getColumnCount());
		JLabel circle = new JLabel();
		Font font = new Font(Font.SERIF, 0, viewAtts.getSize());
		circle.setFont(font);
		circle.setText(CIRCLE_WHITE);
		circle.addMouseListener(new ValueClickListener(this, circles.size()));
		circles.add(circle);
		
		JLabel square = new JLabel();
		square.setFont(font);
		square.setText(SQUARE_EMPTY);
		square.addMouseListener(new SquareClickListener(this, squares.size()));
		squares.add(square);
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		
		getPanel().add(circle, constraints);
		
		constraints.gridY	=	2;
		
		getPanel().add(square, constraints);
	}

	@Override
	public void removeCircle() {}

	@Override
	protected void removeCircle0() {
		int lastIndex = circles.size()-1;
		JLabel last = circles.remove(lastIndex);
		CellConstraints constraints = layout.getConstraints(last);
		getPanel().remove(last);
		JLabel lastSquare = squares.remove(lastIndex);
		getPanel().remove(lastSquare);
		layout.removeColumn(constraints.gridX);
	}

		

}
