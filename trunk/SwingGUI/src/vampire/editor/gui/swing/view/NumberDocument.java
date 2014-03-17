package vampire.editor.gui.swing.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class NumberDocument extends LimitedDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6728086844861062860L;
	
	public NumberDocument(int length) {
		super(length);
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null || str.isEmpty()){
	    	return;
	    }
	    String current = getText(0, getLength());
	    if (getLength() == 0){
	    	if ("+".equals(str) || "-".equals(str)){
	    		super.insertString(offset, str, attr);
	    	}
	    }
	    try{
	    	Integer.parseInt(current+str);
	    }
	    catch (NumberFormatException e){
	    	return;
	    }
	      super.insertString(offset, str, attr);
	  }

}
