package vampire.editor.sheetloader.application.exporter;

import java.awt.Font;
import java.util.Map;

import vampire.editor.domain.sheet.view.FontSettable;
import vampire.editor.domain.sheet.view.MetaEntryViewAttributes;

public class FontSetter {
	
	private final IdCalculator<Font> fonts;

	public FontSetter(IdCalculator<Font> fonts) {
		super();
		this.fonts = fonts;
	}
	
	public void addFont(Object object, Map<String, Object> map){
		if (object instanceof MetaEntryViewAttributes){
			MetaEntryViewAttributes viewAtts = (MetaEntryViewAttributes) object;
			Font titleFont = viewAtts.getTitleFont();
			Font contentFont = viewAtts.getContentFont();
			int titleId = fonts.getId(titleFont);
			int contentId = fonts.getId(contentFont);
			map.put("titleFont", titleId);
			map.put("contentFont", contentId);
		}
		else if (object instanceof FontSettable){
			Font font = ((FontSettable) object).getFont();
			int id = fonts.getId(font);
			map.put("font", id);
		}
	}
	
}
