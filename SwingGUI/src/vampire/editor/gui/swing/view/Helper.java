package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Helper {
	
	public static void initializePanel(JPanel panel){
		panel.setBackground(Color.WHITE);
		setMaximumHeightToPreferredHeight(panel);
	}
	
	public static JPanel createPanel(){
		JPanel panel = new JPanel();
		initializePanel(panel);
		return panel;
	}
	
	public static void initializeTextField(JTextField textField){
		textField.setBackground(Color.WHITE);
		textField.setBorder(null);
		setMaximumHeightToPreferredHeight(textField);
	}
	
	public static JTextField createTextField(){
		JTextField textField = new JTextField();
		initializeTextField(textField);
		return textField;
	}
	
	public static JTextField createLimitedTextField(int length){
		JTextField textField = createTextField();
		textField.setDocument(new LimitedDocument(length));
		return textField;
	}
	
	public static JTextField createNumberOnlyTextField(int length){
		JTextField textField = createTextField();
		textField.setDocument(new NumberDocument(length));
		return textField;
	}
	
	public static void setMaximumHeightToPreferredHeight(JComponent component){
		component.setMaximumSize(new Dimension(component.getMaximumSize().width, component.getPreferredSize().height));
	}
	
	public static JTextField getTitle(String title, Font font){
		JTextField textField = Helper.createTextField();
		textField.setFont(font);
		textField.setEditable(false);
		textField.setFocusable(false);
		textField.setText(title);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		return textField;
	}
	
	public static int getComponentIndex(Container container, Component component){
		for (int i = 0; i < container.getComponentCount(); i++){
			Component component2 = container.getComponent(i);
			if (component == component2){
				return i;
			}
		}
		return -1;
	}
	

}
