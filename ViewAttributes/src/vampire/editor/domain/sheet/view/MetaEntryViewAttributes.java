package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public class MetaEntryViewAttributes implements MetaEntryViewAttributesAPI, FontSettable{
	
	private int lineCount;
	
	private Font titleFont;
	
	private Font contentFont;

	@Override
	public int getLineCount() {
		return lineCount;
	}
	
	@Override
	public MetaEntryViewAttributes clone(){
		MetaEntryViewAttributes clone = new MetaEntryViewAttributes();
		clone.lineCount = lineCount;
		clone.titleFont = titleFont;
		clone.contentFont = contentFont;
		return clone;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	@Override
	public Font getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}

	@Override
	public Font getContentFont() {
		return contentFont;
	}

	public void setContentFont(Font contentFont) {
		this.contentFont = contentFont;
	}
	
	@Override
	public void setFont(Font font){
		this.contentFont = font;
		this.titleFont = font;
	}
	
	

}
