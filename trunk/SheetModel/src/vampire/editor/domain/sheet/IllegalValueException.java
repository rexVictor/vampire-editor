package vampire.editor.domain.sheet;

/**
 * Indicates that a Value was malformed.
 * @author rex
 *
 */
public class IllegalValueException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7746719534201568097L;
	
	public IllegalValueException(){
		super();
	}
	
	public IllegalValueException(String string){
		super(string);		
	}

}
