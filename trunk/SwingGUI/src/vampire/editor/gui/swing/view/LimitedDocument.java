package vampire.editor.gui.swing.view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedDocument extends PlainDocument{

	/**
	 * 
	 */
	private static final long serialVersionUID = -98746429705528845L;
	
	private final int length;

	public LimitedDocument(int length) {
		super();
		this.length = length;
	}
	
	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
	    if (str == null)
	      return;

	    if ((getLength() + str.length()) <= length) {
	      super.insertString(offset, str, attr);
	    }
	  }

}
