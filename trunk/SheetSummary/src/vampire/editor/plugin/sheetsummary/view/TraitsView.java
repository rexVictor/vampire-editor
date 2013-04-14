package vampire.editor.plugin.sheetsummary.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

public class TraitsView {
	
	private final JTextField textField = new JTextField();
	
	private final List<Integer> values = new ArrayList<>();
	
	public TraitsView(){
		textField.setBorder(null);
		textField.setEditable(false);
	}
	
	public void setValue(int pos, int value){
		values.set(pos, value);
		refreshTextField();
	}
	
	private void refreshTextField(){
		StringBuilder sb = new StringBuilder("(");
		for (int i : values){
			sb.append(i);
			sb.append("|");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
	}
	
	public JTextField getField(){
		return textField;
	}

}
