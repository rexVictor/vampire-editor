package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public class MetaEntryViewAttributes implements MetaEntryViewAttributesAPI{
	
	private Font contentFont;
	
	private int lineCount;
	
	private Font titleFont;

	@Override
	public MetaEntryViewAttributes clone(){
		MetaEntryViewAttributes clone = new MetaEntryViewAttributes();
		clone.lineCount = lineCount;
		clone.titleFont = titleFont;
		clone.contentFont = contentFont;
		return clone;
	}
	
	@Override
	public boolean equals(Object object){
		if (object == null)
			return false;
		if (object instanceof MetaEntryViewAttributes){
			MetaEntryViewAttributes other = (MetaEntryViewAttributes) object;
			return contentFont.equals(other.contentFont) && titleFont.equals(other.contentFont)
					&& lineCount == other.lineCount; 
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return lineCount*11 + 13 * titleFont.hashCode() + 17 * contentFont.hashCode();
	}
	
	@Override
	public Font getContentFont() {
		return contentFont;
	}

	@Override
	public int getLineCount() {
		return lineCount;
	}

	@Override
	public Font getTitleFont() {
		return titleFont;
	}

	public void setContentFont(Font contentFont) {
		this.contentFont = contentFont;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}
	
	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}
	
	

}
