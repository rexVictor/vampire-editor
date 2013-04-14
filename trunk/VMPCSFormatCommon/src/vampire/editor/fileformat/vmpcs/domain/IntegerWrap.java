package vampire.editor.fileformat.vmpcs.domain;

public class IntegerWrap {
	
	private Integer i = 0;

	public IntegerWrap() {
		super();
	}
	
	public Integer getInt(){
		return i++;
	}

}
