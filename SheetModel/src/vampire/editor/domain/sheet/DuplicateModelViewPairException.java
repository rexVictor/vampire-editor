package vampire.editor.domain.sheet;

class DuplicateModelViewPairException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4668331146948601L;

	public DuplicateModelViewPairException() {
		super();
	}

	public DuplicateModelViewPairException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DuplicateModelViewPairException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateModelViewPairException(String message) {
		super(message);
	}

	public DuplicateModelViewPairException(Throwable cause) {
		super(cause);
	}
	
	

}
