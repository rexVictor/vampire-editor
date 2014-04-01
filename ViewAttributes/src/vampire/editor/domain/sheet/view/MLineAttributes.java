package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.LineAttributes;

public class MLineAttributes implements LineAttributes{
	
	private String key;
	
	private int width;
	
	private int height;
	
	public MLineAttributes() {
		super();
	}

	public MLineAttributes(String key, int width, int height) {
		super();
		this.key = key;
		this.width = width;
		this.height = height;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public int hashCode(){
		return 31*key.hashCode() + 37 * width + 41 * height;
	}
	
	@Override
	public boolean equals(Object that){
		if (that == null){
			return false;
		}
		if (that == this){
			return true;
		}
		if (that instanceof MLineAttributes){
			MLineAttributes other = (MLineAttributes) that;
			return key.equals(other.key) && width == other.width && height == other.height;
		}
		return false;
	}
	
	@Override
	public MLineAttributes clone(){
		return new MLineAttributes(key, width, height);
	}

}
