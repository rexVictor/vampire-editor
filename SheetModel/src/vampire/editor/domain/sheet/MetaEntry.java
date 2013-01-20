package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;

public class MetaEntry implements MetaEntryAPI {
	
	private String name;
	
	private String value;
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public MetaEntryAPI clone(){
		MetaEntry clone = new MetaEntry();
		clone.name = name;
		clone.value = value;
		return clone;
	}
	
	@Override
	public String toString(){
		return name + " : " + value;
	}
}
