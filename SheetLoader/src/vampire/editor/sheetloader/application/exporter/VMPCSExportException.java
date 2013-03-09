package vampire.editor.sheetloader.application.exporter;

import vampire.editor.plugin.api.plugin.DocumentExportException;

public class VMPCSExportException extends DocumentExportException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2378063942931648991L;

	public VMPCSExportException() {
		super();
	}

	public VMPCSExportException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public VMPCSExportException(String message, Throwable cause) {
		super(message, cause);
	}

	public VMPCSExportException(String message) {
		super(message);
	}

	public VMPCSExportException(Throwable cause) {
		super(cause);
	}
	
	

}
