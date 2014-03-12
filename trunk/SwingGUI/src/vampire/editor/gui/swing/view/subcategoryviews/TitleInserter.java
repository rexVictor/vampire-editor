package vampire.editor.gui.swing.view.subcategoryviews;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JTextField;

class TitleInserter {
	
	static void insertTitle(JComponent panel, String title, Font font){
		JTextField textField = new JTextField();
		textField.setFont(font);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setFocusable(false);
		textField.setBorder(null);
		textField.setText(title);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		panel.add(textField,0);
	}

}
