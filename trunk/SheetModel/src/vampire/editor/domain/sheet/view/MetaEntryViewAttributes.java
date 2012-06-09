package vampire.editor.domain.sheet.view;

import java.awt.Font;

import vampire.editor.plugin.fullapi.sheet.view.IMetaEntryViewAttributes;

public class MetaEntryViewAttributes implements IMetaEntryViewAttributes{
	
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

	@Override
	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	@Override
	public Font getTitleFont() {
		return titleFont;
	}

	@Override
	public void setTitleFont(Font titleFont) {
		this.titleFont = titleFont;
	}

	@Override
	public Font getContentFont() {
		return contentFont;
	}

	@Override
	public void setContentFont(Font contentFont) {
		this.contentFont = contentFont;
	}
	
	public void setFont(Font font){
		this.contentFont = font;
		this.titleFont = font;
	}
	
	

}
