package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

class TitleInserter {
	
	static void insertTitle(JComponent panel, String title, Font font){
		FormLayout layout = (FormLayout) panel.getLayout();
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight		=	1;
		constraints.gridWidth		=	1;
		constraints.gridX			=	1;
		constraints.gridY			=	1;
		constraints.hAlign			=	CellConstraints.CENTER;
		
		JTextField textField = new JTextField();
		textField.setFont(font);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setFocusable(false);
		textField.setBorder(null);
		textField.setText(title);		
		
		panel.add(textField, constraints);
	}

}
