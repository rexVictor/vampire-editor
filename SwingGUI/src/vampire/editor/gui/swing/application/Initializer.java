package vampire.editor.gui.swing.application;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class Initializer {
	
	public static void initialize(JPanel panel){
		panel.setBackground(Color.WHITE);
	}
	
	public static void initialize(JTextComponent field){
		field.setBackground(Color.WHITE);
		field.setBorder(null);
	}

}
