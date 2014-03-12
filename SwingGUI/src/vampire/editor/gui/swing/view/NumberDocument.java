package vampire.editor.gui.swing.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberDocument extends PlainDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6728086844861062860L;
	
	private final int length;
	
	
	public NumberDocument(int length) {
		super();
		this.length = length;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null || str.isEmpty()){
	    	return;
	    }
	    String current = getText(0, getLength());
	    if ((getLength() + str.length() > length)){
	    	return;
	    }
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
