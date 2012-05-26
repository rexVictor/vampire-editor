package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;

public class MetaEntry implements IMetaEntry {
	
	private String name;
	
	private String value;
	
	private MetaEntryViewAttributesAPI viewAtts;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
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

	@Override
	public MetaEntryViewAttributesAPI getViewAtts() {
		return viewAtts;
	}

	@Override
	public void setViewAtts(MetaEntryViewAttributesAPI viewAtts) {
		this.viewAtts = viewAtts;
	}
	
	

}
