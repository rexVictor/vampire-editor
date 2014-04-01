package vampire.editor.plugin.sheetsummary.view;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class SubCatSummaryView {
	
	private final JLabel label;
	
	public SubCatSummaryView() {
		super();
		label = new JLabel();
		label.setBackground(Color.WHITE);
	}
	
	public void setSum(int sum){
		label.setText(Integer.toString(sum));
		label.invalidate();
	}
	
	public JComponent getComponent(){
		return label;
	}
	
}
