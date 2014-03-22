package vampire.editor.gui.swing.view;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class MeritEntryDocument extends LimitedDocument{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JTextField costField;

	public MeritEntryDocument(int length, JTextField costField) {
		super(length);
		this.costField = costField;
	}
	
	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null)
	      return;
	    try {
	    	int cost = Integer.parseInt(str.substring(0,1));
	    	costField.setText(cost+"");
	    	super.insertString(offset, str.substring(1), attr);
	    }
	    catch (NumberFormatException e){
	    	super.insertString(offset, str, attr);
	    }
	  }

	

}
